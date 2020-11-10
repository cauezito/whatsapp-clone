package br.com.cauezito.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.cauezito.whatsapp.R;

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

        auth = FirebaseAuth.getInstance();

      /*  btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tvName.toString();
                String email = tvEmail.toString();
                String password = tvPassword.toString();

               if(validateData(name, email, password)) {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
                            new OnCompleteListener(AuthResult) task) {}
                }
            }
        });*/

    }

    public void signIn(View view){
        finish();
    }

    private boolean validateData(String tvName, String tvEmail, String tvPassword){
        boolean validate = false;

        if(!tvName.isEmpty() && !tvEmail.isEmpty() && !tvPassword.isEmpty()){
            validate = true;
        } else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }

        return validate;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        //updateUI(currentUser);
    }
}