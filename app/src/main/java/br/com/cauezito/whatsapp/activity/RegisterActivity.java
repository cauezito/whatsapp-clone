package br.com.cauezito.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.com.cauezito.whatsapp.R;
import br.com.cauezito.whatsapp.config.FirebaseConfig;
import br.com.cauezito.whatsapp.model.User;

public class RegisterActivity extends AppCompatActivity {
    private TextView tvSignIn, tvEmail, tvPassword, tvName;
    private Button btRegister;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvSignIn = findViewById(R.id.tvSignIn);
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);
        tvName = findViewById(R.id.tvName);
        btRegister = findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(tvName.getText().toString());
                user.setEmail(tvEmail.getText().toString());
                user.setPassword(tvPassword.getText().toString());

                validateData(user);
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addUser(User user) {
        auth = FirebaseConfig.getAuth();
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User successfully registered!", Toast.LENGTH_SHORT).show();
                            clearFields();
                        }else{
                            Toast.makeText(RegisterActivity.this, showError(task), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void clearFields() {
        tvName.setText("");
        tvEmail.setText("");
        tvPassword.setText("");
    }

    private String showError(Task<AuthResult> task){
        String exception = "";

        try{
            throw task.getException();
        } catch (FirebaseAuthWeakPasswordException e) {
            exception = "Enter a stronger password!";
        } catch (FirebaseAuthInvalidCredentialsException e) {
            exception = "Enter a valid email address";
        } catch (FirebaseAuthUserCollisionException e){
            exception = "This account already exists";
        } catch (Exception e) {
            exception = "Oh! Could not register user";
            e.printStackTrace();
        }

        return exception;
    }
    
    private void validateData(User user){
        if(!user.getName().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty()){
           //validate
            addUser(user);
        } else {
            Toast.makeText(this, "Pls, fill in all fields!", Toast.LENGTH_LONG).show();
        }
    }
}