package com.newsong.newsongtime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.newsong.newsongtime.track.Track1;

public class TrackActivity extends AppCompatActivity {

    private TextView tract1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_track);

        tract1 = findViewById(R.id.track_1);
        tract1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Track1.class);
                startActivity(intent);
            }
        });
    }
}
