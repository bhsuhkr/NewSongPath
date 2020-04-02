package com.newsong.newsongtime.ui.saving;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.newsong.newsongtime.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class savingFragment extends Fragment {

    private static final Map<String, String> savingSchedule_kor = new HashMap<String, String>() {{
        // Date format should be MM_dd
        // 2020 년
        // 1 월
        put("01_01", "잠언 1장");
        put("01_02", "잠언 2장");
        put("01_03", "잠언 3장");
        put("01_04", "잠언 4장");
        put("01_05", "잠언 5장");
        put("01_06", "잠언 6장");
        put("01_07", "잠언 7장");
        put("01_08", "잠언 8장");
        put("01_09", "잠언 9장");
        put("01_10", "잠언 10장");
        put("01_11", "잠언 11장");
        put("01_12", "잠언 12장");
        put("01_13", "잠언 13장");
        put("01_14", "잠언 14장");
        put("01_15", "잠언 15장");
        put("01_16", "잠언 16장");
        put("01_17", "잠언 17장");
        put("01_18", "잠언 18장");
        put("01_19", "잠언 19장");
        put("01_20", "잠언 20장");
        put("01_21", "잠언 21장");
        put("01_22", "잠언 22장");
        put("01_23", "잠언 23장");
        put("01_24", "잠언 24장");
        put("01_25", "잠언 25장");
        put("01_26", "잠언 26장");
        put("01_27", "잠언 27장");
        put("01_28", "잠언 28장");
        put("01_29", "잠언 29장");
        put("01_30", "잠언 30장");
        put("01_31", "잠언 31장");

        // 2 월
        put("02_01", "사도행전 1장");
        put("02_02", "사도행전 2장");
        put("02_03", "사도행전 3장");
        put("02_04", "사도행전 4장");
        put("02_05", "사도행전 5장");
        put("02_06", "사도행전 6장");
        put("02_07", "사도행전 7장");
        put("02_08", "사도행전 8장");
        put("02_09", "사도행전 9장");
        put("02_10", "사도행전 10장");
        put("02_11", "사도행전 11장");
        put("02_12", "사도행전 12장");
        put("02_13", "사도행전 13장");
        put("02_14", "사도행전 14장");
        put("02_15", "사도행전 15장");
        put("02_16", "사도행전 16장");
        put("02_17", "사도행전 17장");
        put("02_18", "사도행전 18장");
        put("02_19", "사도행전 19장");
        put("02_20", "사도행전 20장");
        put("02_21", "사도행전 21장");
        put("02_22", "사도행전 22장");
        put("02_23", "사도행전 23장");
        put("02_24", "사도행전 24장");
        put("02_25", "사도행전 25장");
        put("02_26", "사도행전 26장");
        put("02_27", "사도행전 27장");
        put("02_28", "사도행전 28장");
        put("02_29", "로마서 1장");

        // 3 월
        put("03_01", "로마서 2장");
        put("03_02", "로마서 3장");
        put("03_03", "로마서 4장");
        put("03_04", "로마서 5장");
        put("03_05", "로마서 6장");
        put("03_06", "로마서 7장");
        put("03_07", "로마서 8장");
        put("03_08", "로마서 9장");
        put("03_09", "로마서 10장");
        put("03_10", "로마서 11장");
        put("03_11", "로마서 12장");
        put("03_12", "로마서 13장");
        put("03_13", "로마서 14장");
        put("03_14", "로마서 15장");
        put("03_15", "로마서 16장");
        put("03_16", "고린도전서 1장");
        put("03_17", "고린도전서 2장");
        put("03_18", "고린도전서 3장");
        put("03_19", "고린도전서 4장");
        put("03_20", "고린도전서 5장");
        put("03_21", "고린도전서 6장");
        put("03_22", "고린도전서 7장");
        put("03_23", "고린도전서 8장");
        put("03_24", "고린도전서 9장");
        put("03_25", "고린도전서 10장");
        put("03_26", "고린도전서 11장");
        put("03_27", "고린도전서 12장");
        put("03_28", "고린도전서 13장");
        put("03_29", "고린도전서 14장");
        put("03_30", "고린도전서 15장");
        put("03_31", "고린도전서 16장");

        // 4 월
        put("04_01", "고린도후서 1장");
        put("04_02", "고린도후서 2장");
        put("04_03", "고린도후서 3장");
        put("04_04", "고린도후서 4장");
        put("04_05", "고린도후서 5장");
        put("04_06", "고린도후서 6장");
        put("04_07", "고린도후서 7장");
        put("04_08", "고린도후서 8장");
        put("04_09", "고린도후서 9장");
        put("04_10", "고린도후서 10장");
        put("04_11", "고린도후서 11장");
        put("04_12", "고린도후서 12장");
        put("04_13", "고린도후서 13장");
        put("04_14", "갈라디아서 1장");
        put("04_15", "갈라디아서 2장");
        put("04_16", "갈라디아서 3장");
        put("04_17", "갈라디아서 4장");
        put("04_18", "갈라디아서 5장");
        put("04_19", "갈라디아서 6장");
        put("04_20", "에베소서 1장");
        put("04_21", "에베소서 2장");
        put("04_22", "에베소서 3장");
        put("04_23", "에베소서 4장");
        put("04_24", "에베소서 5장");
        put("04_25", "에베소서 6장");
        put("04_26", "빌립보서 1장");
        put("04_27", "빌립보서 2장");
        put("04_28", "빌립보서 3장");
        put("04_29", "빌립보서 4장");
        put("04_30", "골로새서 1장");

        // 5 월
        put("05_01", "골로새서 2장");
        put("05_02", "골로새서 3장");
        put("05_03", "골로새서 4장");
        put("05_04", "데살로니가전서 1장");
        put("05_05", "데살로니가전서 2장");
        put("05_06", "데살로니가전서 3장");
        put("05_07", "데살로니가전서 4장");
        put("05_08", "데살로니가전서 5장");
        put("05_09", "데살로니가후서 1장");
        put("05_10", "데살로니가후서 2장");
        put("05_11", "데살로니가후서 3장");
        put("05_12", "디모데전서 1장");
        put("05_13", "디모데전서 2장");
        put("05_14", "디모데전서 3장");
        put("05_15", "디모데전서 4장");
        put("05_16", "디모데전서 5장");
        put("05_17", "디모데전서 6장");
        put("05_18", "디모데후서 1장");
        put("05_19", "디모데후서 2장");
        put("05_20", "디모데후서 3장");
        put("05_21", "디모데후서 4장");
        put("05_22", "디도서 1장");
        put("05_23", "디도서 2장");
        put("05_24", "디도서 3장");
        put("05_25", "빌레몬서 1장");
        put("05_26", "히브리서 1장");
        put("05_27", "히브리서 2장");
        put("05_28", "히브리서 3장");
        put("05_29", "히브리서 4장");
        put("05_30", "히브리서 5장");
        put("05_31", "히브리서 6장");

        // 6 월
        put("06_01", "히브리서 7장");
        put("06_02", "히브리서 8장");
        put("06_03", "히브리서 9장");
        put("06_04", "히브리서 10장");
        put("06_05", "히브리서 11장");
        put("06_06", "히브리서 12장");
        put("06_07", "히브리서 13장");
        put("06_08", "야고보서 1장");
        put("06_09", "야고보서 2장");
        put("06_10", "야고보서 3장");
        put("06_11", "야고보서 4장");
        put("06_12", "야고보서 5장");
        put("06_13", "베드로전서 1장");
        put("06_14", "베드로전서 2장");
        put("06_15", "베드로전서 3장");
        put("06_16", "베드로전서 4장");
        put("06_17", "베드로전서 5장");
        put("06_18", "베드로후서 1장");
        put("06_19", "베드로후서 2장");
        put("06_20", "베드로후서 3장");
        put("06_21", "요한일서 1장");
        put("06_22", "요한일서 2장");
        put("06_23", "요한일서 3장");
        put("06_24", "요한일서 4장");
        put("06_25", "요한일서 5장");
        put("06_26", "요한이서 1장");
        put("06_27", "요한삼서 1장");
        put("06_28", "유다서 1장");
        put("06_29", "요한계시록 1장");
        put("06_30", "요한계시록 2장");

        // 7 월
        put("07_01", "요한계시록 3장");
        put("07_02", "요한계시록 4장");
        put("07_03", "요한계시록 5장");
        put("07_04", "요한계시록 6장");
        put("07_05", "요한계시록 7장");
        put("07_06", "요한계시록 8장");
        put("07_07", "요한계시록 9장");
        put("07_08", "요한계시록 10장");
        put("07_09", "요한계시록 11장");
        put("07_10", "요한계시록 12장");
        put("07_11", "요한계시록 13장");
        put("07_12", "요한계시록 14장");
        put("07_13", "요한계시록 15장");
        put("07_14", "요한계시록 16장");
        put("07_15", "요한계시록 17장");
        put("07_16", "요한계시록 18장");
        put("07_17", "요한계시록 19장");
        put("07_18", "요한계시록 20장");
        put("07_19", "요한계시록 21");
        put("07_20", "요한계시록 22장");
        put("07_21", "마태복음 1장");
        put("07_22", "마태복음 2장");
        put("07_23", "마태복음 3장");
        put("07_24", "마태복음 4장");
        put("07_25", "마태복음 5장");
        put("07_26", "마태복음 6장");
        put("07_27", "마태복음 7장");
        put("07_28", "마태복음 8장");
        put("07_29", "마태복음 9장");
        put("07_30", "마태복음 10장");
        put("07_31", "마태복음 11장");

        // 8 월
        put("08_01", "마태복음 12장");
        put("08_02", "마태복음 13장");
        put("08_03", "마태복음 14장");
        put("08_04", "마태복음 15장");
        put("08_05", "마태복음 16장");
        put("08_06", "마태복음 17장");
        put("08_07", "마태복음 18장");
        put("08_08", "마태복음 19장");
        put("08_09", "마태복음 20장");
        put("08_10", "마태복음 21장");
        put("08_11", "마태복음 22장");
        put("08_12", "마태복음 23장");
        put("08_13", "마태복음 24장");
        put("08_14", "마태복음 25장");
        put("08_15", "마태복음 26장");
        put("08_16", "마태복음 27장");
        put("08_17", "마태복음 28장");
        put("08_18", "마가복음 1장");
        put("08_19", "마가복음 2장");
        put("08_20", "마가복음 3장");
        put("08_21", "마가복음 4장");
        put("08_22", "마가복음 5장");
        put("08_23", "마가복음 6장");
        put("08_24", "마가복음 7장");
        put("08_25", "마가복음 8장");
        put("08_26", "마가복음 9장");
        put("08_27", "마가복음 10장");
        put("08_28", "마가복음 11장");
        put("08_29", "마가복음 12장");
        put("08_30", "마가복음 13장");
        put("08_31", "마가복음 14장");

        // 9 월
        put("09_01", "마가복음 15장");
        put("09_02", "마가복음 16장");
        put("09_03", "누가복음 1장");
        put("09_04", "누가복음 2장");
        put("09_05", "누가복음 3장");
        put("09_06", "누가복음 4장");
        put("09_07", "누가복음 5장");
        put("09_08", "누가복음 6장");
        put("09_09", "누가복음 7장");
        put("09_10", "누가복음 8장");
        put("09_11", "누가복음 9장");
        put("09_12", "누가복음 10장");
        put("09_13", "누가복음 11장");
        put("09_14", "누가복음 12장");
        put("09_15", "누가복음 13장");
        put("09_16", "누가복음 14장");
        put("09_17", "누가복음 15장");
        put("09_18", "누가복음 16장");
        put("09_19", "누가복음 17장");
        put("09_20", "누가복음 18장");
        put("09_21", "누가복음 19장");
        put("09_22", "누가복음 20장");
        put("09_23", "누가복음 21장");
        put("09_24", "누가복음 22장");
        put("09_25", "누가복음 23장");
        put("09_26", "누가복음 24장");
        put("09_27", "요한복음 1장");
        put("09_28", "요한복음 2장");
        put("09_29", "요한복음 3장");
        put("09_30", "요한복음 4장");

        // 10 월
        put("10_01", "요한복음 5장");
        put("10_02", "요한복음 6장");
        put("10_03", "요한복음 7장");
        put("10_04", "요한복음 8장");
        put("10_05", "요한복음 9장");
        put("10_06", "요한복음 10장");
        put("10_07", "요한복음 11장");
        put("10_08", "요한복음 12장");
        put("10_09", "요한복음 13장");
        put("10_10", "요한복음 14장");
        put("10_11", "요한복음 15장");
        put("10_12", "요한복음 16장");
        put("10_13", "요한복음 17장");
        put("10_14", "요한복음 18장");
        put("10_15", "요한복음 19장");
        put("10_16", "요한복음 20장");
        put("10_17", "요한복음 21장");
        put("10_18", "창세기 1장");
        put("10_19", "창세기 2장");
        put("10_20", "창세기 3장");
        put("10_21", "창세기 4장");
        put("10_22", "창세기 5장");
        put("10_23", "창세기 6장");
        put("10_24", "창세기 7장");
        put("10_25", "창세기 8장");
        put("10_26", "창세기 9장");
        put("10_27", "창세기 10장");
        put("10_28", "창세기 11장");
        put("10_29", "창세기 12장");
        put("10_30", "창세기 13장");
        put("10_31", "창세기 14장");

        // 11 월
        put("11_01", "창세기 15장");
        put("11_02", "창세기 16장");
        put("11_03", "창세기 17장");
        put("11_04", "창세기 18장");
        put("11_05", "창세기 19장");
        put("11_06", "창세기 20장");
        put("11_07", "창세기 21장");
        put("11_08", "창세기 22장");
        put("11_09", "창세기 23장");
        put("11_10", "창세기 24장");
        put("11_11", "창세기 25장");
        put("11_12", "창세기 26장");
        put("11_13", "창세기 27장");
        put("11_14", "창세기 28장");
        put("11_15", "창세기 29장");
        put("11_16", "창세기 30장");
        put("11_17", "창세기 31장");
        put("11_18", "창세기 32장");
        put("11_19", "창세기 33장");
        put("11_20", "창세기 34장");
        put("11_21", "창세기 35장");
        put("11_22", "창세기 36장");
        put("11_23", "창세기 37장");
        put("11_24", "창세기 38장");
        put("11_25", "창세기 39장");
        put("11_26", "창세기 40장");
        put("11_27", "창세기 41장");
        put("11_28", "창세기 42장");
        put("11_29", "창세기 43장");
        put("11_30", "창세기 44장");

        // 12 월
        put("12_01", "창세기 45장");
        put("12_02", "창세기 46장");
        put("12_03", "창세기 47장");
        put("12_04", "창세기 48장");
        put("12_05", "창세기 49장");
        put("12_06", "창세기 50장");
        put("12_07", "출애굽기 1장");
        put("12_08", "출애굽기 2장");
        put("12_09", "출애굽기 3장");
        put("12_10", "출애굽기 4장");
        put("12_11", "출애굽기 5장");
        put("12_12", "출애굽기 6장");
        put("12_13", "출애굽기 7장");
        put("12_14", "출애굽기 8장");
        put("12_15", "출애굽기 9장");
        put("12_16", "출애굽기 10장");
        put("12_17", "출애굽기 11장");
        put("12_18", "출애굽기 12장");
        put("12_19", "출애굽기 13장");
        put("12_20", "출애굽기 14장");
        put("12_21", "출애굽기 15장");
        put("12_22", "출애굽기 16장");
        put("12_23", "출애굽기 17장");
        put("12_24", "출애굽기 18장");
        put("12_25", "출애굽기 19장");
        put("12_26", "출애굽기 20장");
        put("12_27", "출애굽기 21장");
        put("12_28", "출애굽기 22장");
        put("12_29", "출애굽기 23장");
        put("12_30", "출애굽기 24장");
        put("12_31", "출애굽기 25장");
    }};
    private static final Map<String, String> savingSchedule_eng = new HashMap<String, String>() {{
        // Date format should be MM_dd
        // 2020 년
        // 1 월
        put("01_01", "Proverbs 1");
        put("01_02", "Proverbs 2");
        put("01_03", "Proverbs 3");
        put("01_04", "Proverbs 4");
        put("01_05", "Proverbs 5");
        put("01_06", "Proverbs 6");
        put("01_07", "Proverbs 7");
        put("01_08", "Proverbs 8");
        put("01_09", "Proverbs 9");
        put("01_10", "Proverbs 10");
        put("01_11", "Proverbs 11");
        put("01_12", "Proverbs 12");
        put("01_13", "Proverbs 13");
        put("01_14", "Proverbs 14");
        put("01_15", "Proverbs 15");
        put("01_16", "Proverbs 16");
        put("01_17", "Proverbs 17");
        put("01_18", "Proverbs 18");
        put("01_19", "Proverbs 19");
        put("01_20", "Proverbs 20");
        put("01_21", "Proverbs 21");
        put("01_22", "Proverbs 22");
        put("01_23", "Proverbs 23");
        put("01_24", "Proverbs 24");
        put("01_25", "Proverbs 25");
        put("01_26", "Proverbs 26");
        put("01_27", "Proverbs 27");
        put("01_28", "Proverbs 28");
        put("01_29", "Proverbs 29");
        put("01_30", "Proverbs 30");
        put("01_31", "Proverbs 31");

        // 2 월
        put("02_01", "Acts 1");
        put("02_02", "Acts 2");
        put("02_03", "Acts 3");
        put("02_04", "Acts 4");
        put("02_05", "Acts 5");
        put("02_06", "Acts 6");
        put("02_07", "Acts 7");
        put("02_08", "Acts 8");
        put("02_09", "Acts 9");
        put("02_10", "Acts 10");
        put("02_11", "Acts 11");
        put("02_12", "Acts 12");
        put("02_13", "Acts 13");
        put("02_14", "Acts 14");
        put("02_15", "Acts 15");
        put("02_16", "Acts 16");
        put("02_17", "Acts 17");
        put("02_18", "Acts 18");
        put("02_19", "Acts 19");
        put("02_20", "Acts 20");
        put("02_21", "Acts 21");
        put("02_22", "Acts 22");
        put("02_23", "Acts 23");
        put("02_24", "Acts 24");
        put("02_25", "Acts 25");
        put("02_26", "Acts 26");
        put("02_27", "Acts 27");
        put("02_28", "Acts 28");
        put("02_29", "Romans 1");

        // 3 월
        put("03_01", "Romans 2");
        put("03_02", "Romans 3");
        put("03_03", "Romans 4");
        put("03_04", "Romans 5");
        put("03_05", "Romans 6");
        put("03_06", "Romans 7");
        put("03_07", "Romans 8");
        put("03_08", "Romans 9");
        put("03_09", "Romans 10");
        put("03_10", "Romans 11");
        put("03_11", "Romans 12");
        put("03_12", "Romans 13");
        put("03_13", "Romans 14");
        put("03_14", "Romans 15");
        put("03_15", "Romans 16");
        put("03_16", "1Corinthians 1");
        put("03_17", "1Corinthians 2");
        put("03_18", "1Corinthians 3");
        put("03_19", "1Corinthians 4");
        put("03_20", "1Corinthians 5");
        put("03_21", "1Corinthians 6");
        put("03_22", "1Corinthians 7");
        put("03_23", "1Corinthians 8");
        put("03_24", "1Corinthians 9");
        put("03_25", "1Corinthians 10");
        put("03_26", "1Corinthians 11");
        put("03_27", "1Corinthians 12");
        put("03_28", "1Corinthians 13");
        put("03_29", "1Corinthians 14");
        put("03_30", "1Corinthians 15");
        put("03_31", "1Corinthians 16");

        // 4 월
        put("04_01", "2Corinthians 1");
        put("04_02", "2Corinthians 2");
        put("04_03", "2Corinthians 3");
        put("04_04", "2Corinthians 4");
        put("04_05", "2Corinthians 5");
        put("04_06", "2Corinthians 6");
        put("04_07", "2Corinthians 7");
        put("04_08", "2Corinthians 8");
        put("04_09", "2Corinthians 9");
        put("04_10", "2Corinthians 10");
        put("04_11", "2Corinthians 11");
        put("04_12", "2Corinthians 12");
        put("04_13", "2Corinthians 13장");
        put("04_14", "Galatians 1");
        put("04_15", "Galatians 2");
        put("04_16", "Galatians 3");
        put("04_17", "Galatians 4");
        put("04_18", "Galatians 5");
        put("04_19", "Galatians 6");
        put("04_20", "Ephesians 1");
        put("04_21", "Ephesians 2");
        put("04_22", "Ephesians 3");
        put("04_23", "Ephesians 4");
        put("04_24", "Ephesians 5");
        put("04_25", "Ephesians 6");
        put("04_26", "Philippians 1");
        put("04_27", "Philippians 2");
        put("04_28", "Philippians 3");
        put("04_29", "Philippians 4");
        put("04_30", "Colossians 1");

        // 5 월
        put("05_01", "Colossians 2");
        put("05_02", "Colossians 3");
        put("05_03", "Colossians 4");
        put("05_04", "1Thessalonians 1");
        put("05_05", "1Thessalonians 2");
        put("05_06", "1Thessalonians 3");
        put("05_07", "1Thessalonians 4");
        put("05_08", "1Thessalonians 5");
        put("05_09", "2Thessalonians 1");
        put("05_10", "2Thessalonians 2");
        put("05_11", "2Thessalonians 3");
        put("05_12", "1Timothy 1");
        put("05_13", "1Timothy 2");
        put("05_14", "1Timothy 3");
        put("05_15", "1Timothy 4");
        put("05_16", "1Timothy 5");
        put("05_17", "1Timothy 6");
        put("05_18", "2Timothy 1");
        put("05_19", "2Timothy 2");
        put("05_20", "2Timothy 3");
        put("05_21", "2Timothy 4");
        put("05_22", "Titus 1");
        put("05_23", "Titus 2");
        put("05_24", "Titus 3");
        put("05_25", "Philemon 1");
        put("05_26", "Hebrews 1");
        put("05_27", "Hebrews 2");
        put("05_28", "Hebrews 3");
        put("05_29", "Hebrews 4");
        put("05_30", "Hebrews 5");
        put("05_31", "Hebrews 6");

        // 6 월
        put("06_01", "Hebrews 7");
        put("06_02", "Hebrews 8");
        put("06_03", "Hebrews 9");
        put("06_04", "Hebrews 10");
        put("06_05", "Hebrews 11");
        put("06_06", "Hebrews 12");
        put("06_07", "Hebrews 13");
        put("06_08", "James 1");
        put("06_09", "James 2");
        put("06_10", "James 3");
        put("06_11", "James 4");
        put("06_12", "James 5");
        put("06_13", "1Peter 1");
        put("06_14", "1Peter 2");
        put("06_15", "1Peter 3");
        put("06_16", "1Peter 4");
        put("06_17", "1Peter 5");
        put("06_18", "2Peter 1");
        put("06_19", "2Peter 2");
        put("06_20", "2Peter 3");
        put("06_21", "1John 1");
        put("06_22", "1John 2");
        put("06_23", "1John 3");
        put("06_24", "1John 4");
        put("06_25", "1John 5");
        put("06_26", "2John 1");
        put("06_27", "3John 1");
        put("06_28", "Jude 1");
        put("06_29", "Revelation 1");
        put("06_30", "Revelation 2");

        // 7 월
        put("07_01", "Revelation 3");
        put("07_02", "Revelation 4");
        put("07_03", "Revelation 5");
        put("07_04", "Revelation 6");
        put("07_05", "Revelation 7");
        put("07_06", "Revelation 8");
        put("07_07", "Revelation 9");
        put("07_08", "Revelation 10");
        put("07_09", "Revelation 11");
        put("07_10", "Revelation 12");
        put("07_11", "Revelation 13");
        put("07_12", "Revelation 14");
        put("07_13", "Revelation 15");
        put("07_14", "Revelation 16");
        put("07_15", "Revelation 17");
        put("07_16", "Revelation 18");
        put("07_17", "Revelation 19");
        put("07_18", "Revelation 20");
        put("07_19", "Revelation 21");
        put("07_20", "Revelation 22");
        put("07_21", "Matthew 1");
        put("07_22", "Matthew 2");
        put("07_23", "Matthew 3");
        put("07_24", "Matthew 4");
        put("07_25", "Matthew 5");
        put("07_26", "Matthew 6");
        put("07_27", "Matthew 7");
        put("07_28", "Matthew 8");
        put("07_29", "Matthew 9");
        put("07_30", "Matthew 10");
        put("07_31", "Matthew 11");

        // 8 월
        put("08_01", "Matthew 12");
        put("08_02", "Matthew 13");
        put("08_03", "Matthew 14");
        put("08_04", "Matthew 15");
        put("08_05", "Matthew 16");
        put("08_06", "Matthew 17");
        put("08_07", "Matthew 18");
        put("08_08", "Matthew 19");
        put("08_09", "Matthew 20");
        put("08_10", "Matthew 21");
        put("08_11", "Matthew 22");
        put("08_12", "Matthew 23");
        put("08_13", "Matthew 24");
        put("08_14", "Matthew 25");
        put("08_15", "Matthew 26");
        put("08_16", "Matthew 27");
        put("08_17", "Matthew 28");
        put("08_18", "Mark 1");
        put("08_19", "Mark 2");
        put("08_20", "Mark 3");
        put("08_21", "Mark 4");
        put("08_22", "Mark 5");
        put("08_23", "Mark 6");
        put("08_24", "Mark 7");
        put("08_25", "Mark 8");
        put("08_26", "Mark 9");
        put("08_27", "Mark 10");
        put("08_28", "Mark 11");
        put("08_29", "Mark 12");
        put("08_30", "Mark 13");
        put("08_31", "Mark 14");

        // 9 월
        put("09_01", "Mark 15");
        put("09_02", "Mark 16");
        put("09_03", "Luke 1");
        put("09_04", "Luke 2");
        put("09_05", "Luke 3");
        put("09_06", "Luke 4");
        put("09_07", "Luke 5");
        put("09_08", "Luke 6");
        put("09_09", "Luke 7");
        put("09_10", "Luke 8");
        put("09_11", "Luke 9");
        put("09_12", "Luke 10");
        put("09_13", "Luke 11");
        put("09_14", "Luke 12");
        put("09_15", "Luke 13");
        put("09_16", "Luke 14");
        put("09_17", "Luke 15");
        put("09_18", "Luke 16");
        put("09_19", "Luke 17");
        put("09_20", "Luke 18");
        put("09_21", "Luke 19");
        put("09_22", "Luke 20");
        put("09_23", "Luke 21");
        put("09_24", "Luke 22");
        put("09_25", "Luke 23");
        put("09_26", "Luke 24");
        put("09_27", "John 1");
        put("09_28", "John 2");
        put("09_29", "John 3");
        put("09_30", "John 4");

        // 10 월
        put("10_01", "John 5");
        put("10_02", "John 6");
        put("10_03", "John 7");
        put("10_04", "John 8");
        put("10_05", "John 9");
        put("10_06", "John 10");
        put("10_07", "John 11");
        put("10_08", "John 12");
        put("10_09", "John 13");
        put("10_10", "John 14");
        put("10_11", "John 15");
        put("10_12", "John 16");
        put("10_13", "John 17");
        put("10_14", "John 18");
        put("10_15", "John 19");
        put("10_16", "John 20");
        put("10_17", "John 21");
        put("10_18", "Genesis 1");
        put("10_19", "Genesis 2");
        put("10_20", "Genesis 3");
        put("10_21", "Genesis 4");
        put("10_22", "Genesis 5");
        put("10_23", "Genesis 6");
        put("10_24", "Genesis 7");
        put("10_25", "Genesis 8");
        put("10_26", "Genesis 9");
        put("10_27", "Genesis 10");
        put("10_28", "Genesis 11");
        put("10_29", "Genesis 12");
        put("10_30", "Genesis 13");
        put("10_31", "Genesis 14");

        // 11 월
        put("11_01", "Genesis 15");
        put("11_02", "Genesis 16");
        put("11_03", "Genesis 17");
        put("11_04", "Genesis 18");
        put("11_05", "Genesis 19");
        put("11_06", "Genesis 20");
        put("11_07", "Genesis 21");
        put("11_08", "Genesis 22");
        put("11_09", "Genesis 23");
        put("11_10", "Genesis 24");
        put("11_11", "Genesis 25");
        put("11_12", "Genesis 26");
        put("11_13", "Genesis 27");
        put("11_14", "Genesis 28");
        put("11_15", "Genesis 29");
        put("11_16", "Genesis 30");
        put("11_17", "Genesis 31");
        put("11_18", "Genesis 32");
        put("11_19", "Genesis 33");
        put("11_20", "Genesis 34");
        put("11_21", "Genesis 35");
        put("11_22", "Genesis 36");
        put("11_23", "Genesis 37");
        put("11_24", "Genesis 38");
        put("11_25", "Genesis 39");
        put("11_26", "Genesis 40");
        put("11_27", "Genesis 41");
        put("11_28", "Genesis 42");
        put("11_29", "Genesis 43");
        put("11_30", "Genesis 44");

        // 12 월
        put("12_01", "Genesis 45");
        put("12_02", "Genesis 46");
        put("12_03", "Genesis 47");
        put("12_04", "Genesis 48");
        put("12_05", "Genesis 49");
        put("12_06", "Genesis 50");
        put("12_07", "Exodus 1");
        put("12_08", "Exodus 2");
        put("12_09", "Exodus 3");
        put("12_10", "Exodus 4");
        put("12_11", "Exodus 5");
        put("12_12", "Exodus 6");
        put("12_13", "Exodus 7");
        put("12_14", "Exodus 8");
        put("12_15", "Exodus 9");
        put("12_16", "Exodus 10");
        put("12_17", "Exodus 11");
        put("12_18", "Exodus 12");
        put("12_19", "Exodus 13");
        put("12_20", "Exodus 14");
        put("12_21", "Exodus 15");
        put("12_22", "Exodus 16");
        put("12_23", "Exodus 17");
        put("12_24", "Exodus 18");
        put("12_25", "Exodus 19");
        put("12_26", "Exodus 20");
        put("12_27", "Exodus 21");
        put("12_28", "Exodus 22");
        put("12_29", "Exodus 23");
        put("12_30", "Exodus 24");
        put("12_31", "Exodus 25");
    }};
    Map<String, Boolean> check = new HashMap<String, Boolean>();
    Map<String, String> uriList = new HashMap<String, String>();
    private savingViewModel savingViewModel;
    private TextView savingTextView;
    private TextView savingQT_kor;
    private TextView handWrite;
    private TextView saving_kor;
    private TextView saving_qt;
    private TextView saving_eng;
    private String currentDate;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private HorizontalScrollView horizontalScrollView;
    private String androidId;
    private DatabaseReference rootRef;
    private DatabaseReference playersRef;
    static String returnUri = "로딩중...\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        savingViewModel = ViewModelProviders.of(this).get(savingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_saving, container, false);

        currentDate = new SimpleDateFormat("MM_dd", Locale.getDefault()).format(new Date());

        savingQT_kor = root.findViewById(R.id.text_saving);
        loadUri();

        androidId = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
        rootRef = FirebaseDatabase.getInstance().getReference().child("Track").child(androidId);

        handWrite = root.findViewById(R.id.saveBtn);
        handWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] current = currentDate.split("_");
                loadMap(check, current[0]);
                check.put(current[1], true);
                saveMap(check, current[0]);
                playersRef = rootRef.child(current[0]);
                int removeZero = Integer.parseInt(current[1]);
                playersRef.child(Integer.toString(removeZero)).setValue(true);

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("기록 완료");
                builder.setMessage("저장되었습니다!");
                builder.setIcon(R.drawable.checkmark);
                builder.setCancelable(true);

                final AlertDialog dlg = builder.create();
                dlg.show();

                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss();
                        t.cancel();
                    }
                }, 2000);
            }
        });

        savingTextView = root.findViewById(R.id.txtView_saving);
        savingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] current = currentDate.split("_");
                loadMap(check, current[0]);
                check.put(current[1], true);
                saveMap(check, current[0]);
                playersRef = rootRef.child(current[0]);
                int removeZero = Integer.parseInt(current[1]);
                playersRef.child(Integer.toString(removeZero)).setValue(true);

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("기록 완료");
                builder.setMessage("저장되었습니다!");
                builder.setIcon(R.drawable.checkmark);
                builder.setCancelable(true);

                final AlertDialog dlg = builder.create();
                dlg.show();

                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss();
                        t.cancel();
                    }
                }, 2000);
            }
        });

        try {
            savingTextView.setText(savingSchedule_kor.get(currentDate));
        } catch (Exception e) {

        }

        image1 = root.findViewById(R.id.imageView_saving1);
        image2 = root.findViewById(R.id.imageView_saving2);
        image3 = root.findViewById(R.id.imageView_saving3);
        horizontalScrollView = root.findViewById(R.id.horizontalScroll_saving);


        saving_kor = root.findViewById(R.id.saving_korean);
        saving_kor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saving_kor.setEnabled(false);
                saving_qt.setEnabled(true);
                saving_eng.setEnabled(true);
                savingQT_kor.setVisibility(View.GONE);
                image1.setImageResource(R.drawable.saving1_kor);
                image2.setImageResource(R.drawable.saving2_kor);
                image3.setImageResource(R.drawable.saving3_kor);
                horizontalScrollView.setVisibility(View.VISIBLE);
                try {
                    savingTextView.setText(savingSchedule_kor.get(currentDate));
                } catch (Exception e) {
                    savingTextView.setText("관리자에게 문의해주세요.");
                }
            }
        });

        saving_qt = root.findViewById(R.id.saving_qt);
        saving_qt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saving_kor.setEnabled(true);
                saving_qt.setEnabled(false);
                saving_eng.setEnabled(true);
                horizontalScrollView.setVisibility(View.GONE);
                savingQT_kor.setVisibility(View.VISIBLE);

                try {
                    savingTextView.setText(savingSchedule_kor.get(currentDate));
                } catch (Exception e) {
                    savingTextView.setText("관리자에게 문의해주세요.");
                }
            }
        });

        saving_eng = root.findViewById(R.id.saving_english);
        saving_eng.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saving_kor.setEnabled(true);
                saving_qt.setEnabled(true);
                saving_eng.setEnabled(false);
                savingQT_kor.setVisibility(View.GONE);
                image1.setImageResource(R.drawable.saving1_eng);
                image2.setImageResource(R.drawable.saving2_eng);
                image3.setImageResource(R.drawable.saving3_eng);
                horizontalScrollView.setVisibility(View.VISIBLE);
                try {
                    savingTextView.setText(savingSchedule_eng.get(currentDate));
                } catch (Exception e) {
                    savingTextView.setText("관리자에게 문의해주세요.");
                }
            }
        });

        return root;
    }


    public Map<String, Boolean> loadMap(Map<String, Boolean> check, String month) {
        SharedPreferences pSharedPref = getContext().getSharedPreferences("progress_" + month, Context.MODE_PRIVATE);
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


    public void loadUri() {
        SharedPreferences pSharedPref = getContext().getSharedPreferences("uriList", Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("Track_Uri", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    String value = (String) jsonObject.get(key);
                    uriList.put(key, value);
                }

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(uriList.get("saving"));
                            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

                            String inputLine;
                            returnUri = "";
                            while ((inputLine = in.readLine()) != null)
                                returnUri += inputLine + "\n";
                            in.close();
                            returnUri += "\n\n\n\n";
                            savingQT_kor.setText(returnUri);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                Thread.currentThread().getPriority();
                thread.start();
            }
        } catch (Exception e) {
        }
    }


    public void saveMap(Map<String, Boolean> inputMap, String month) {
        SharedPreferences pSharedPref = getContext().getSharedPreferences("progress_" + month, Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("Track_" + month).commit();
            editor.putString("Track_" + month, jsonString);
            editor.commit();
        }
    }

}