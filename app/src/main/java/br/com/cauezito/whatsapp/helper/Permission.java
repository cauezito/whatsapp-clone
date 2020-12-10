package br.com.cauezito.whatsapp.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    public static boolean validatePermissions(String[] permissions, Activity origin, int requestCode) {
        boolean hasPermission = false;
        List<String> permissionsList = new ArrayList<>();

        //>= MARSHMELLOW
        if (Build.VERSION.SDK_INT >= 23) {

            for (String permission : permissions) {
                hasPermission = ContextCompat.checkSelfPermission(origin, permission) == PackageManager.PERMISSION_GRANTED;

                if (!hasPermission) permissionsList.add(permission);
            }

            if (permissionsList.isEmpty()) return true;

            ActivityCompat.requestPermissions(origin, permissionsList.toArray(new String[permissionsList.size()]), requestCode);

        }

        return hasPermission;
    }
}
