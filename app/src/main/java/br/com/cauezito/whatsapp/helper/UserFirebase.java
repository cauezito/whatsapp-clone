package br.com.cauezito.whatsapp.helper;

import com.google.firebase.auth.FirebaseAuth;

import br.com.cauezito.whatsapp.config.FirebaseConfig;

public class UserFirebase {

    public static String getUserId() {
        FirebaseAuth user = FirebaseConfig.getAuth();
        return Base64Custom.encode(user.getCurrentUser().getEmail());
    }
}
