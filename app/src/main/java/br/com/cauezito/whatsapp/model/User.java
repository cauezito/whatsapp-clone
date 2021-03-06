package br.com.cauezito.whatsapp.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.cauezito.whatsapp.FirebaseEnum;
import br.com.cauezito.whatsapp.config.FirebaseConfig;
import br.com.cauezito.whatsapp.helper.UserFirebase;

public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;
    private String photo;

    public void save() {
        DatabaseReference firebaseRef = FirebaseConfig.getDatabaseReference();
        DatabaseReference user = firebaseRef.child(FirebaseEnum.USERS.getName()).child(getId());

        user.setValue(this);
    }

    public void update() {
        String userId = UserFirebase.getUserId();
        DatabaseReference databaseReference = FirebaseConfig.getDatabaseReference();
        DatabaseReference userRef = databaseReference.child("users")
                .child(userId);

        userRef.updateChildren(userToMap());
    }

    @Exclude
    private Map<String, Object> userToMap() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("email", getEmail());
        map.put("name", getName());
        map.put("photo", getPhoto());

        return map;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
