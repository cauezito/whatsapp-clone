package br.com.cauezito.whatsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import br.com.cauezito.whatsapp.R;
import br.com.cauezito.whatsapp.config.FirebaseConfig;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private TextView tvRegister;
    private Button btLogin;
    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validateData(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

    private void validateData(String email, String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            //TODO validate
            login(email, password);
        } else {
            Toast.makeText(this, "Pls, fill in all fields!", Toast.LENGTH_LONG).show();
        }
    }

    private void login(String email, String password) {
        auth = FirebaseConfig.getAuth();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Logging in...", Toast.LENGTH_SHORT).show();
                    goToHome();
                } else {
                    Toast.makeText(LoginActivity.this, showError(task), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String showError(Task<AuthResult> task) {
        String exception = "";

        try {
            throw task.getException();
        } catch (FirebaseAuthInvalidUserException e) {
            exception = "User not registered!";
        } catch (FirebaseAuthInvalidCredentialsException e) {
            exception = "Email and password do not match";
        } catch (Exception e) {
            exception = "Oh, sorry! Unable to log in at the moment";
            e.printStackTrace();
        }
        return exception;
    }

    private void goToHome() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }
}