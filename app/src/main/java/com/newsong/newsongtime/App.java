package com.newsong.newsongtime;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
        put("01", 0);
        put("02", 0);
        put("03", 0);
        put("04", 0);
        put("05", 0);
        put("06", 0);
        put("07", 0);
        put("08", 0);
        put("09", 0);
        put("10", 0);
        put("11", 0);
        put("12", 0);
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
    Map<String, String> saveAnnouncement = new HashMap<String, String>();
    private static final String url = "http://www.newsongdallas.org/tong/s_board/read.asp?board_seq=28&board_sub_seq=1&view_sub_seq=0&seq=2562&lef=&sublef=&page=1&search_select=&search_text=";
    String getUrl;
    String htmlContentInStringFormat;
    StringBuilder builder;
    String tempURL = "";
    String[] splitYoutube;
    String tempID;

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();

        androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        rootRef = FirebaseDatabase.getInstance().getReference().child("Track").child(androidId);

        createListenerFor12Months();

        builder = new StringBuilder();
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
    }


    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(url).get();
                Elements title = doc.select("div.sboard_cont_details > p"); //parent > child: child elements that descend directly from parent, e.g.
                Elements imagefile = doc.select("div.sboard_cont_details img[src]"); //parent > child: child elements that descend directly from parent, e.g.
                for (Element e : title) {
                    if (e.text().contains("http")) {
                        tempURL = e.text();
                        splitYoutube = tempURL.split("=", 2);
                        tempID = splitYoutube[1];
                    } else {
                        builder.append(e.text()).append("\n");
                    }
                }
                for (Element e1 : imagefile) {
                    getUrl = e1.absUrl("src");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            htmlContentInStringFormat = builder.toString();
            saveAnnouncement.put("Announce", htmlContentInStringFormat);
            saveAnnouncement.put("Picture", getUrl);
            saveAnnouncement(saveAnnouncement);
        }
    }

    public void saveAnnouncement(Map<String, String> inputMap) {
        SharedPreferences pSharedPref = getSharedPreferences("announcement", Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("announce_home").commit();
            editor.putString("announce_home", jsonString);
            editor.commit();
        }
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
        playersRef1 = rootRef.child("01");
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
                progressBar.put("01", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef2 = rootRef.child("02");
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
                progressBar.put("02", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef3 = rootRef.child("03");
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
                progressBar.put("03", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef4 = rootRef.child("04");
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
                progressBar.put("04", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef5 = rootRef.child("05");
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
                progressBar.put("05", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef6 = rootRef.child("06");
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
                progressBar.put("06", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef7 = rootRef.child("07");
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
                progressBar.put("07", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef8 = rootRef.child("08");
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
                progressBar.put("08", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef9 = rootRef.child("09");
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
                progressBar.put("09", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef10 = rootRef.child("10");
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
                progressBar.put("10", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef11 = rootRef.child("11");
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
                progressBar.put("11", progress);
                saveMap(progressBar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        playersRef12 = rootRef.child("12");
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
                progressBar.put("12", progress);
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