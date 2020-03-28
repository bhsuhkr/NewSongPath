package com.newsong.newsongtime.track;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.newsong.newsongtime.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Track03 extends AppCompatActivity {

    private static final Map<String, Integer> id_list = new HashMap<String, Integer>() {{
        put("1", R.id.day_1);
        put("2", R.id.day_2);
        put("3", R.id.day_3);
        put("4", R.id.day_4);
        put("5", R.id.day_5);
        put("6", R.id.day_6);
        put("7", R.id.day_7);
        put("8", R.id.day_8);
        put("9", R.id.day_9);
        put("10", R.id.day_10);
        put("11", R.id.day_11);
        put("12", R.id.day_12);
        put("13", R.id.day_13);
        put("14", R.id.day_14);
        put("15", R.id.day_15);
        put("16", R.id.day_16);
        put("17", R.id.day_17);
        put("18", R.id.day_18);
        put("19", R.id.day_19);
        put("20", R.id.day_20);
        put("21", R.id.day_21);
        put("22", R.id.day_22);
        put("23", R.id.day_23);
        put("24", R.id.day_24);
        put("25", R.id.day_25);
        put("26", R.id.day_26);
        put("27", R.id.day_27);
        put("28", R.id.day_28);
        put("29", R.id.day_29);
        put("30", R.id.day_30);
        put("31", R.id.day_31);
    }};
    public static Map<String, Boolean> check = new HashMap<String, Boolean>() {{
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.track_3);

        loadMap();

        for (int i = 1; i <= 31; i++) {
            TextView current = findViewById(id_list.get(Integer.toString(i)));

            if (check.get(Integer.toString(i))) {
                current.setBackgroundResource(R.drawable.checkmark);
            } else {
                current.setBackgroundResource(R.color.darker_gray);
            }
        }
    }

    public void isClicked(View v) {
//        Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
        v.setBackgroundDrawable(getResources().getDrawable(R.drawable.checkmark));
        toggleCheckBox(v.getTag().toString(), v);
    }

    public void toggleCheckBox(String date, View v) {
        if (check.get(date)) {
            check.put(date, false);
            v.setBackgroundResource(R.color.darker_gray);
        } else {
            check.put(date, true);
            v.setBackgroundResource(R.drawable.checkmark);
        }
        saveMap(check);
    }

    public void saveMap(Map<String, Boolean> inputMap) {
        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("progress_03", Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("Track_03").commit();
            editor.putString("Track_03", jsonString);
            editor.commit();
        }
    }

    public Map<String, Boolean> loadMap() {
        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("progress_03", Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("Track_03", (new JSONObject()).toString());
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

    @Override
    protected void onPause(){
        super.onPause();
        saveMap(check);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        saveMap(check);
    }

}
