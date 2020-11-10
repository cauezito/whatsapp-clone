package br.com.cauezito.whatsapp.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {
    private static DatabaseReference databaseReference;
    private static FirebaseAuth auth;

    public static DatabaseReference getDatabaseReference() {
        if(databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return databaseReference;
    }

    public static FirebaseAuth getAuth() {
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }

        return auth;
    }
}
