package br.com.cauezito.whatsapp.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.cauezito.whatsapp.R;

public class RegisterActivity extends AppCompatActivity {
    TextView tvRegister, tvEmail, tvPassword, tvName;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvRegister = findViewById(R.id.tvRegister);
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);
        tvName = findViewById(R.id.tvName);

        btLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
}