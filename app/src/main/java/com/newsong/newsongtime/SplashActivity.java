package com.newsong.newsongtime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    private String currentDateWithYear;
    static String uriConvert = "";
    StorageReference savingReference;
    StorageReference servingReference;
    StorageReference sendingReference;
    StorageReference storageReference;
    FirebaseStorage storage;
    Map<String, String> uriList = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentDateWithYear = new SimpleDateFormat("MM_dd", Locale.getDefault()).format(new Date());

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        
        savingReference = storageReference.child(currentDateWithYear + "_2020_saving.txt");
        savingReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                uriConvert = uri.toString();
                uriList.put("saving", uriConvert);
                saveUri(uriList);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });

//        servingReference = storageReference.child(currentDateWithYear + "_saving.txt");
        servingReference = storageReference.child("announcement.txt");
        servingReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                uriConvert = uri.toString();
                uriList.put("serving", uriConvert);
                saveUri(uriList);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });

//        sendingReference = storageReference.child(currentDateWithYear + "_sending.txt");
        sendingReference = storageReference.child("announcement.txt");
        sendingReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                uriConvert = uri.toString();
                uriList.put("sending", uriConvert);
                saveUri(uriList);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    public void saveUri(Map<String, String> inputUri) {
        SharedPreferences pSharedPref = getSharedPreferences("uriList", Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            JSONObject jsonObject = new JSONObject(inputUri);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("Track_Uri").commit();
            editor.putString("Track_Uri", jsonString);
            editor.commit();
        }
    }
}
