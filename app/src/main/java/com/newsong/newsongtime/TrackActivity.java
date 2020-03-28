package com.newsong.newsongtime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.newsong.newsongtime.track.Track01;
import com.newsong.newsongtime.track.Track02;
import com.newsong.newsongtime.track.Track03;
import com.newsong.newsongtime.track.Track04;
import com.newsong.newsongtime.track.Track05;
import com.newsong.newsongtime.track.Track06;
import com.newsong.newsongtime.track.Track07;
import com.newsong.newsongtime.track.Track08;
import com.newsong.newsongtime.track.Track09;
import com.newsong.newsongtime.track.Track10;
import com.newsong.newsongtime.track.Track11;
import com.newsong.newsongtime.track.Track12;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TrackActivity extends AppCompatActivity {

    Map<String, Boolean> check_1 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_2 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_3 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_4 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_5 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_6 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_7 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_8 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_9 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_10 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_11 = new HashMap<String, Boolean>();
    Map<String, Boolean> check_12 = new HashMap<String, Boolean>();

    private TextView tract1;
    private TextView tract2;
    private TextView tract3;
    private TextView tract4;
    private TextView tract5;
    private TextView tract6;
    private TextView tract7;
    private TextView tract8;
    private TextView tract9;
    private TextView tract10;
    private TextView tract11;
    private TextView tract12;

    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;
    private ProgressBar progressBar4;
    private ProgressBar progressBar5;
    private ProgressBar progressBar6;
    private ProgressBar progressBar7;
    private ProgressBar progressBar8;
    private ProgressBar progressBar9;
    private ProgressBar progressBar10;
    private ProgressBar progressBar11;
    private ProgressBar progressBar12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_track);

        tract1 = findViewById(R.id.track_1);
        tract1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track01.class));
            }
        });

        tract2 = findViewById(R.id.track_2);
        tract2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track02.class));
            }
        });

        tract3 = findViewById(R.id.track_3);
        tract3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track03.class));
            }
        });

        tract4 = findViewById(R.id.track_4);
        tract4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track04.class));
            }
        });

        tract5 = findViewById(R.id.track_5);
        tract5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track05.class));
            }
        });

        tract6 = findViewById(R.id.track_6);
        tract6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track06.class));
            }
        });

        tract7 = findViewById(R.id.track_7);
        tract7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track07.class));
            }
        });

        tract8 = findViewById(R.id.track_8);
        tract8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track08.class));
            }
        });

        tract9 = findViewById(R.id.track_9);
        tract9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track09.class));
            }
        });

        tract10 = findViewById(R.id.track_10);
        tract10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track10.class));
            }
        });

        tract11 = findViewById(R.id.track_11);
        tract11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track11.class));
            }
        });

        tract12 = findViewById(R.id.track_12);
        tract12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Track12.class));
            }
        });


        loadMap(check_1, "01");
        int count1 = 0;
        for (int i = 1; i <= check_1.size(); i++) {
            try {
                if (check_1.get(Integer.toString(i))) count1++;
            } catch (Exception e) {

            }
        }

        progressBar1 = findViewById(R.id.progressBar1);
        progressBar1.setMax(31);
        progressBar1.setProgress(count1);
        progressBar1.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_2, "02");
        int count2 = 0;
        for (int i = 1; i <= check_2.size(); i++) {
            try {
                if (check_2.get(Integer.toString(i))) count2++;
            } catch (Exception e) {

            }
        }

        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setMax(29);
        progressBar2.setProgress(count2);
        progressBar2.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_3, "03");
        int count3 = 0;
        for (int i = 1; i <= check_3.size(); i++) {
            try {
                if (check_3.get(Integer.toString(i))) count3++;
            } catch (Exception e) {

            }
        }

        progressBar3 = findViewById(R.id.progressBar3);
        progressBar3.setMax(31);
        progressBar3.setProgress(count3);
        progressBar3.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_4, "04");
        int count4 = 0;
        for (int i = 1; i <= check_4.size(); i++) {
            try {
                if (check_4.get(Integer.toString(i))) count4++;
            } catch (Exception e) {

            }
        }

        progressBar4 = findViewById(R.id.progressBar4);
        progressBar4.setMax(30);
        progressBar4.setProgress(count4);
        progressBar4.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_5, "05");
        int count5 = 0;
        for (int i = 1; i <= check_5.size(); i++) {
            try {
                if (check_5.get(Integer.toString(i))) count5++;
            } catch (Exception e) {

            }
        }

        progressBar5 = findViewById(R.id.progressBar5);
        progressBar5.setMax(31);
        progressBar5.setProgress(count5);
        progressBar5.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_6, "06");
        int count6 = 0;
        for (int i = 1; i <= check_6.size(); i++) {
            try {
                if (check_6.get(Integer.toString(i))) count6++;
            } catch (Exception e) {

            }
        }

        progressBar6 = findViewById(R.id.progressBar6);
        progressBar6.setMax(30);
        progressBar6.setProgress(count6);
        progressBar6.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_7, "07");
        int count7 = 0;
        for (int i = 1; i <= check_7.size(); i++) {
            try {
                if (check_7.get(Integer.toString(i))) count7++;
            } catch (Exception e) {

            }
        }

        progressBar7 = findViewById(R.id.progressBar7);
        progressBar7.setMax(31);
        progressBar7.setProgress(count7);
        progressBar7.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_8, "08");
        int count8 = 0;
        for (int i = 1; i <= check_8.size(); i++) {
            try {
                if (check_8.get(Integer.toString(i))) count8++;
            } catch (Exception e) {

            }
        }

        progressBar8 = findViewById(R.id.progressBar8);
        progressBar8.setMax(31);
        progressBar8.setProgress(count8);
        progressBar8.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_9, "09");
        int count9 = 0;
        for (int i = 1; i <= check_9.size(); i++) {
            try {
                if (check_9.get(Integer.toString(i))) count9++;
            } catch (Exception e) {

            }
        }

        progressBar9 = findViewById(R.id.progressBar9);
        progressBar9.setMax(30);
        progressBar9.setProgress(count9);
        progressBar9.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_10, "10");
        int count10 = 0;
        for (int i = 1; i <= check_10.size(); i++) {
            try {
                if (check_10.get(Integer.toString(i))) count10++;
            } catch (Exception e) {

            }
        }

        progressBar10 = findViewById(R.id.progressBar10);
        progressBar10.setMax(31);
        progressBar10.setProgress(count10);
        progressBar10.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_11, "11");
        int count11 = 0;
        for (int i = 1; i <= check_11.size(); i++) {
            try {
                if (check_11.get(Integer.toString(i))) count11++;
            } catch (Exception e) {

            }
        }

        progressBar11 = findViewById(R.id.progressBar11);
        progressBar11.setMax(30);
        progressBar11.setProgress(count11);
        progressBar11.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_12, "12");
        int count12 = 0;
        for (int i = 1; i <= check_12.size(); i++) {
            try {
                if (check_12.get(Integer.toString(i))) count12++;
            } catch (Exception e) {

            }
        }

        progressBar12 = findViewById(R.id.progressBar12);
        progressBar12.setMax(31);
        progressBar12.setProgress(count12);
        progressBar12.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onResume() {
        super.onResume();

        loadMap(check_1, "01");
        int count1 = 0;
        for (int i = 1; i <= check_1.size(); i++) {
            try {
                if (check_1.get(Integer.toString(i))) count1++;
            } catch (Exception e) {

            }
        }

        progressBar1.setMax(31);
        progressBar1.setProgress(count1);
        progressBar1.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_2, "02");
        int count2 = 0;
        for (int i = 1; i <= check_2.size(); i++) {
            try {
                if (check_2.get(Integer.toString(i))) count2++;
            } catch (Exception e) {

            }
        }

        progressBar2.setMax(29);
        progressBar2.setProgress(count2);
        progressBar2.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_3, "03");
        int count3 = 0;
        for (int i = 1; i <= check_3.size(); i++) {
            try {
                if (check_3.get(Integer.toString(i))) count3++;
            } catch (Exception e) {

            }
        }

        progressBar3.setMax(31);
        progressBar3.setProgress(count3);
        progressBar3.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_4, "04");
        int count4 = 0;
        for (int i = 1; i <= check_4.size(); i++) {
            try {
                if (check_4.get(Integer.toString(i))) count4++;
            } catch (Exception e) {

            }
        }

        progressBar4.setMax(30);
        progressBar4.setProgress(count4);
        progressBar4.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_5, "05");
        int count5 = 0;
        for (int i = 1; i <= check_5.size(); i++) {
            try {
                if (check_5.get(Integer.toString(i))) count5++;
            } catch (Exception e) {

            }
        }

        progressBar5.setMax(31);
        progressBar5.setProgress(count5);
        progressBar5.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_6, "06");
        int count6 = 0;
        for (int i = 1; i <= check_6.size(); i++) {
            try {
                if (check_6.get(Integer.toString(i))) count6++;
            } catch (Exception e) {

            }
        }

        progressBar6.setMax(30);
        progressBar6.setProgress(count6);
        progressBar6.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_7, "07");
        int count7 = 0;
        for (int i = 1; i <= check_7.size(); i++) {
            try {
                if (check_7.get(Integer.toString(i))) count7++;
            } catch (Exception e) {

            }
        }

        progressBar7.setMax(31);
        progressBar7.setProgress(count7);
        progressBar7.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_8, "08");
        int count8 = 0;
        for (int i = 1; i <= check_8.size(); i++) {
            try {
                if (check_8.get(Integer.toString(i))) count8++;
            } catch (Exception e) {

            }
        }

        progressBar8.setMax(31);
        progressBar8.setProgress(count8);
        progressBar8.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_9, "09");
        int count9 = 0;
        for (int i = 1; i <= check_9.size(); i++) {
            try {
                if (check_9.get(Integer.toString(i))) count9++;
            } catch (Exception e) {

            }
        }

        progressBar9.setMax(30);
        progressBar9.setProgress(count9);
        progressBar9.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_10, "10");
        int count10 = 0;
        for (int i = 1; i <= check_10.size(); i++) {
            try {
                if (check_10.get(Integer.toString(i))) count10++;
            } catch (Exception e) {

            }
        }

        progressBar10.setMax(31);
        progressBar10.setProgress(count10);
        progressBar10.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_11, "11");
        int count11 = 0;
        for (int i = 1; i <= check_11.size(); i++) {
            try {
                if (check_11.get(Integer.toString(i))) count11++;
            } catch (Exception e) {

            }
        }

        progressBar11.setMax(30);
        progressBar11.setProgress(count11);
        progressBar11.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        loadMap(check_12, "12");
        int count12 = 0;
        for (int i = 1; i <= check_12.size(); i++) {
            try {
                if (check_12.get(Integer.toString(i))) count12++;
            } catch (Exception e) {

            }
        }

        progressBar12.setMax(31);
        progressBar12.setProgress(count12);
        progressBar12.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public Map<String, Boolean> loadMap(Map<String, Boolean> check, String month) {
        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("progress_" + month, Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("Track_" + month, (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    Boolean value = (Boolean) jsonObject.get(key);
                    check.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
