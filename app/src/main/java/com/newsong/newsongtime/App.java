package com.newsong.newsongtime;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    DatabaseReference rootRef;
    DatabaseReference playersRef1;
    DatabaseReference playersRef2;
    DatabaseReference playersRef3;
    DatabaseReference playersRef4;
    DatabaseReference playersRef5;
    DatabaseReference playersRef6;
    DatabaseReference playersRef7;
    DatabaseReference playersRef8;
    DatabaseReference playersRef9;
    DatabaseReference playersRef10;
    DatabaseReference playersRef11;
    DatabaseReference playersRef12;

    String androidId;
    static Map<String, Integer> progressBar = new HashMap<String, Integer>() {{
        put("January", 0);
        put("February", 0);
        put("March", 0);
        put("April", 0);
        put("May", 0);
        put("June", 0);
        put("July", 0);
        put("August", 0);
        put("September", 0);
        put("October", 0);
        put("November", 0);
        put("December", 0);
    }};

    public static Map<String, Boolean> check31 = new HashMap<String, Boolean>() {{
        put("1", false);
        put("2", false);
        put("3", false);
        put("4", false);
        put("5", false);
        put("6", false);
        put("7", false);
        put("8", false);
        put("9", false);
        put("10", false);
        put("11", false);
        put("12", false);
        put("13", false);
        put("14", false);
        put("15", false);
        put("16", false);
        put("17", false);
        put("18", false);
        put("19", false);
        put("20", false);
        put("21", false);
        put("22", false);
        put("23", false);
        put("24", false);
        put("25", false);
        put("26", false);
        put("27", false);
        put("28", false);
        put("29", false);
        put("30", false);
        put("31", false);
    }};
    public static Map<String, Boolean> check30 = new HashMap<String, Boolean>() {{
        put("1", false);
        put("2", false);
        put("3", false);
        put("4", false);
        put("5", false);
        put("6", false);
        put("7", false);
        put("8", false);
        put("9", false);
        put("10", false);
        put("11", false);
        put("12", false);
        put("13", false);
        put("14", false);
        put("15", false);
        put("16", false);
        put("17", false);
        put("18", false);
        put("19", false);
        put("20", false);
        put("21", false);
        put("22", false);
        put("23", false);
        put("24", false);
        put("25", false);
        put("26", false);
        put("27", false);
        put("28", false);
        put("29", false);
        put("30", false);
    }};
    public static Map<String, Boolean> check29 = new HashMap<String, Boolean>() {{
        put("1", false);
        put("2", false);
        put("3", false);
        put("4", false);
        put("5", false);
        put("6", false);
        put("7", false);
        put("8", false);
        put("9", false);
        put("10", false);
        put("11", false);
        put("12", false);
        put("13", false);
        put("14", false);
        put("15", false);
        put("16", false);
        put("17", false);
        put("18", false);
        put("19", false);
        put("20", false);
        put("21", false);
        put("22", false);
        put("23", false);
        put("24", false);
        put("25", false);
        put("26", false);
        put("27", false);
        put("28", false);
        put("29", false);
    }};

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();

        androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        // initialize map
        rootRef = FirebaseDatabase.getInstance().getReference().child("Track").child(androidId);

        createListenerFor12Months();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }

    private void createListenerFor12Months() {
        playersRef1 = rootRef.child("January");
        playersRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef1.setValue(check31);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("January", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef2 = rootRef.child("February");
        playersRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef2.setValue(check29);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("February", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef3 = rootRef.child("March");
        playersRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef3.setValue(check31);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("March", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef4 = rootRef.child("April");
        playersRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef4.setValue(check30);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("April", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef5 = rootRef.child("May");
        playersRef5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef5.setValue(check31);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("May", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef6 = rootRef.child("June");
        playersRef6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef6.setValue(check30);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("June", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef7 = rootRef.child("July");
        playersRef7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef7.setValue(check31);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("July", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef8 = rootRef.child("August");
        playersRef8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef8.setValue(check31);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("August", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef9 = rootRef.child("September");
        playersRef9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef9.setValue(check30);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("September", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef10 = rootRef.child("October");
        playersRef10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef10.setValue(check31);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("October", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef11 = rootRef.child("November");
        playersRef11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef11.setValue(check30);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("November", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef12 = rootRef.child("December");
        playersRef12.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    playersRef12.setValue(check31);
                    return;
                }
                int progress = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.getValue(Boolean.class)) {
                        progress++;
                    }
                }
                progressBar.put("December", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void saveMap(Map<String, Integer> inputMap) {
        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("progressBar", Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("Track_progressBar").commit();
            editor.putString("Track_progressBar", jsonString);
            editor.commit();
        }
    }

}