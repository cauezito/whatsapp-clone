package br.com.cauezito.whatsapp.helper;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import br.com.cauezito.whatsapp.config.FirebaseConfig;

public class UserFirebase {

    public static FirebaseUser getUser() {
        return FirebaseConfig.getAuth().getCurrentUser();
    }

    public static String getUserId() {
        return Base64Custom.encode(getUser().getEmail());
    }

    public static boolean changePicture(Uri image) {
        try {
            FirebaseUser user = getUser();

            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(image).build();

            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful())
                        Log.d("PROFILE_PICTURE", "ERROR TO CHANGE PROFILE PIC");
                }
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean changeName(String name) {
        try {
            FirebaseUser user = getUser();

            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build();

            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) Log.d("PROFILE_NAME", "ERROR TO CHANGE NAME");
                }
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
