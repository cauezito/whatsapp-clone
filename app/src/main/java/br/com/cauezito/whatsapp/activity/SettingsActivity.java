package br.com.cauezito.whatsapp.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import br.com.cauezito.whatsapp.ActionsEnum;
import br.com.cauezito.whatsapp.PermissionsEnum;
import br.com.cauezito.whatsapp.R;
import br.com.cauezito.whatsapp.helper.Permission;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton ibCamera, ibStorage;
    private ImageView ivPhoto;

    private String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Permission.validatePermissions(permissions, this, PermissionsEnum.ORIGIN_SETTINGS);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ibCamera = findViewById(R.id.ibCamera);
        ibStorage = findViewById(R.id.ibStorage);
        ivPhoto = findViewById(R.id.ivPhoto);

        setOnClickButtons();
    }

    private void setOnClickButtons() {
        ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(i, ActionsEnum.NEW_PROFILE_PHOTO_BY_CAM);
                }

            }
        });

        ibStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(i, ActionsEnum.NEW_PROFILE_PHOTO_BY_STORAGE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bitmap image = null;

            try {
                switch (requestCode) {
                    case ActionsEnum.NEW_PROFILE_PHOTO_BY_CAM:
                        image = (Bitmap) data.getExtras().get("data");

                        break;
                    case ActionsEnum.NEW_PROFILE_PHOTO_BY_STORAGE:
                        Uri imageSd = data.getData();
                        image = MediaStore.Images.Media.getBitmap(getContentResolver(), imageSd);
                        break;
                }

                if (image != null) {
                    ivPhoto.setImageBitmap(image);
                }

            } catch (Exception e) {
                Toast.makeText(this, "Couldn't retrieve image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int resultPermissions : grantResults) {
            if (resultPermissions == PackageManager.PERMISSION_DENIED) alertPermissionFailure();
        }
    }

    private void alertPermissionFailure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissions have been denied");
        builder.setMessage("To change your information, you must accept the permissions!");
        builder.setCancelable(false);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}