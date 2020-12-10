package br.com.cauezito.whatsapp.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

import br.com.cauezito.whatsapp.FirebaseEnums;
import br.com.cauezito.whatsapp.config.FirebaseConfig;

public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;

    public void save() {
        DatabaseReference firebaseRef = FirebaseConfig.getDatabaseReference();
        DatabaseReference user = firebaseRef.child(FirebaseEnums.USERS.getName()).child(getId());

        user.setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
