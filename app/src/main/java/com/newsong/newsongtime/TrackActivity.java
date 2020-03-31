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

    private TextView track1;
    private TextView track2;
    private TextView track3;
    private TextView track4;
    private TextView track5;
    private TextView track6;
    private TextView track7;
    private TextView track8;
    private TextView track9;
    private TextView track10;
    private TextView track11;
    private TextView track12;

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

    Map<String, Integer> progressList = new HashMap<String, Integer>() {{
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_track);

        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("progressBar", Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("Track_progressBar", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    int value = (Integer) jsonObject.get(key);
                    progressList.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        track1 = findViewById(R.id.track_1);
        track1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track1.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track01.class));
            }
        });

        track2 = findViewById(R.id.track_2);
        track2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track2.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track02.class));
            }
        });

        track3 = findViewById(R.id.track_3);
        track3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track3.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track03.class));
            }
        });

        track4 = findViewById(R.id.track_4);
        track4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track4.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track04.class));
            }
        });

        track5 = findViewById(R.id.track_5);
        track5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track5.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track05.class));
            }
        });

        track6 = findViewById(R.id.track_6);
        track6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track6.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track06.class));
            }
        });

        track7 = findViewById(R.id.track_7);
        track7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track7.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track07.class));
            }
        });

        track8 = findViewById(R.id.track_8);
        track8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track8.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track08.class));
            }
        });

        track9 = findViewById(R.id.track_9);
        track9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track9.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track09.class));
            }
        });

        track10 = findViewById(R.id.track_10);
        track10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track10.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track10.class));
            }
        });

        track11 = findViewById(R.id.track_11);
        track11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track11.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track11.class));
            }
        });

        track12 = findViewById(R.id.track_12);
        track12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                track12.setEnabled(false);
                startActivity(new Intent(getBaseContext(), Track12.class));
            }
        });


        progressBar1 = findViewById(R.id.progressBar1);
        progressBar1.setMax(31);
        progressBar1.setProgress(progressList.get("January"));
        progressBar1.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setMax(29);
        progressBar2.setProgress(progressList.get("February"));
        progressBar2.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar3 = findViewById(R.id.progressBar3);
        progressBar3.setMax(31);
        progressBar3.setProgress(progressList.get("March"));
        progressBar3.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar4 = findViewById(R.id.progressBar4);
        progressBar4.setMax(30);
        progressBar4.setProgress(progressList.get("April"));
        progressBar4.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar5 = findViewById(R.id.progressBar5);
        progressBar5.setMax(31);
        progressBar5.setProgress(progressList.get("May"));
        progressBar5.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar6 = findViewById(R.id.progressBar6);
        progressBar6.setMax(30);
        progressBar6.setProgress(progressList.get("June"));
        progressBar6.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar7 = findViewById(R.id.progressBar7);
        progressBar7.setMax(31);
        progressBar7.setProgress(progressList.get("July"));
        progressBar7.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar8 = findViewById(R.id.progressBar8);
        progressBar8.setMax(31);
        progressBar8.setProgress(progressList.get("August"));
        progressBar8.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar9 = findViewById(R.id.progressBar9);
        progressBar9.setMax(30);
        progressBar9.setProgress(progressList.get("September"));
        progressBar9.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar10 = findViewById(R.id.progressBar10);
        progressBar10.setMax(31);
        progressBar10.setProgress(progressList.get("October"));
        progressBar10.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar11 = findViewById(R.id.progressBar11);
        progressBar11.setMax(30);
        progressBar11.setProgress(progressList.get("November"));
        progressBar11.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar12 = findViewById(R.id.progressBar12);
        progressBar12.setMax(31);
        progressBar12.setProgress(progressList.get("December"));
        progressBar12.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onResume() {
        super.onResume();

        track1.setEnabled(true);
        track2.setEnabled(true);
        track3.setEnabled(true);
        track4.setEnabled(true);
        track5.setEnabled(true);
        track6.setEnabled(true);
        track7.setEnabled(true);
        track8.setEnabled(true);
        track9.setEnabled(true);
        track10.setEnabled(true);
        track11.setEnabled(true);
        track12.setEnabled(true);

        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("progressBar", Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("Track_progressBar", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    int value = (Integer) jsonObject.get(key);
                    progressList.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        progressBar1.setMax(31);
        progressBar1.setProgress(progressList.get("January"));
        progressBar1.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar2.setMax(29);
        progressBar2.setProgress(progressList.get("February"));
        progressBar2.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar3.setMax(31);
        progressBar3.setProgress(progressList.get("March"));
        progressBar3.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar4.setMax(30);
        progressBar4.setProgress(progressList.get("April"));
        progressBar4.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar5.setMax(31);
        progressBar5.setProgress(progressList.get("May"));
        progressBar5.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar6.setMax(30);
        progressBar6.setProgress(progressList.get("June"));
        progressBar6.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar7.setMax(31);
        progressBar7.setProgress(progressList.get("July"));
        progressBar7.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar8.setMax(31);
        progressBar8.setProgress(progressList.get("August"));
        progressBar8.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar9.setMax(30);
        progressBar9.setProgress(progressList.get("September"));
        progressBar9.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar10.setMax(31);
        progressBar10.setProgress(progressList.get("October"));
        progressBar10.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar11.setMax(30);
        progressBar11.setProgress(progressList.get("November"));
        progressBar11.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        progressBar12.setMax(31);
        progressBar12.setProgress(progressList.get("December"));
        progressBar12.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public Map<String, Integer> loadMap(Map<String, Integer> check) {
        int progress = 0;
        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("progressBar", Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("Track_progressBar", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    int value = (Integer) jsonObject.get(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
