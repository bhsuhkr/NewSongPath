package com.newsong.newsongtime.ui.serving;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.newsong.newsongtime.R;

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

public class servingFragement extends Fragment {

    private servingViewModel notificationsViewModel;
    private TextView servingTextView;
    private TextView servingQT_kor;
    private String currentDate;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView column;
    private BottomNavigationView navigation;
    private HorizontalScrollView horizontalScrollView;
    private StringBuilder builder;
    private static final String url_kor_QT = "http://www.newsongdallas.org/tong/s_board/read.asp?board_seq=28&board_sub_seq=1&view_sub_seq=0&seq=2603&lef=&sublef=&page=1&search_select=&search_text=";
    private static final String url_eng_QT = "http://www.newsongdallas.org/tong/s_board/read.asp?board_seq=28&board_sub_seq=1&view_sub_seq=0&seq=2603&lef=&sublef=&page=1&search_select=&search_text=";
    private String htmlContentInStringFormat;
    private static final Map<String, String> servingSchedule_kor = new HashMap<String, String>()
    {{
        // Date format should be MM_dd
        // 2019 년
        // 1 월
        put("01_01","마태복음 1-2장, 잠언 1장");put("01_02","마태복음 3-4장, 잠언 2장");put("01_03","마태복음 5-6장, 잠언 3장");put("01_04","마태복음 7-8장, 잠언 4장");
        put("01_05","마태복음 9-10장, 잠언 5장");put("01_06","마태복음 11-12장, 잠언 6장");put("01_07","마태복음 13-14장, 잠언 7장");put("01_08","마태복음 15-16장, 잠언 8장");
        put("01_09","마태복음 17-18장, 잠언 9장");put("01_10","마태복음 19-20장, 잠언 10장");put("01_11","마태복음 21-22장, 잠언 11장");put("01_12","마태복음 23-24장, 잠언 12장");
        put("01_13","마태복음 25-26장, 잠언 13장");put("01_14","마태복음 27-28장, 잠언 14장");put("01_15","마가복음 1-2장, 잠언 15장");put("01_16","마가복음 4-5장, 잠언 16장");
        put("01_17","마가복음 5-6장, 잠언 17장");put("01_18","마가복음 7-8장, 잠언 18장");put("01_19","마가복음 9-10장, 잠언 19장");put("01_20","마가복음 11-12장, 잠언 20장");
        put("01_21","마가복음 13-14장, 잠언 21장");put("01_22","마가복음 15-16장, 잠언 22장");put("01_23","누가복음 1-2장, 잠언 23장");put("01_24","누가복음 3-4장, 잠언 24장");
        put("01_25","누가복음 5-6장, 잠언 25장");put("01_26","누가복음 7-8장, 잠언 26장");put("01_27","누가복음 9-10장, 잠언 27장");put("01_28","누가복음 11-12장, 잠언 28장");
        put("01_29","누가복음 13-14장, 잠언 29장");put("01_30","누가복음 15-16장, 잠언 30장");put("01_31","누가복음 17-18장, 잠언 31장");

        // 2 월
        put("02_01","누가복음 19-20장, 시편 1장");put("02_02","누가복음 21-22장, 시편 2장");put("02_03","누가복음 23-24장, 시편 3장");
        put("02_04","요한복음 1-2장, 시편 4장");put("02_05","요한복음 3-4장, 시편 5장");put("02_06","요한복음 5-6장, 시편 6장");
        put("02_07","요한복음 7-8장, 시편 7장");put("02_08","요한복음 9-10장, 시편 8장");put("02_09","요한복음 11-12장, 시편 9장");
        put("02_10","요한복음 13-14장, 시편 10장");put("02_11","요한복음 15-16장, 시편 11장");put("02_12","요한복음 17-18장, 시편 12장");
        put("02_13","요한복음 19-20장, 시편 13장");put("02_14","요한복음 21장, 시편 14장");put("02_15","사도행전 1-2장, 시편 15장");
        put("02_16","사도행전 3-4장, 시편 16장");put("02_17","사도행전 5-6장, 시편 17장");put("02_18","사도행전 7-8장, 시편 18장");
        put("02_19","사도행전 9-10장, 시편 19장");put("02_20","사도행전 11-12장, 시편 20장");put("02_21","사도행전 13-14장, 시편 21장");
        put("02_22","사도행전 15-16장, 시편 22장");put("02_23","사도행전 17-18장, 시편 23장");put("02_24","사도행전 19-20장, 시편 24장");
        put("02_25","사도행전 21-22장, 시편 25장");put("02_26","사도행전 23-24장, 시편 26장");put("02_27","사도행전 25-26장, 시편 27장");
        put("02_28","사도행전 27-28장, 시편 28장");put("02_29","로마서 1-2장, 시편 29장");

        // 3 월
        put("03_01","로마서 3-4장, 시편 30장");put("03_02","로마서 5-6장, 시편 31장");put("03_03","로마서 7-8장, 시편 32장");put("03_04","로마서 9-10장, 시편 33장");
        put("03_05","로마서 11-12장, 시편 34장");put("03_06","로마서 13-14장, 시편 35장");put("03_07","로마서 15-16장, 시편 36장");put("03_08","고린도전서 1-2장, 시편 37장");
        put("03_09","고린도전서 3-4장, 시편 38장");put("03_10","고린도전서 5-6장, 시편 39장");put("03_11","고린도전서 7-8장, 시편 40장");put("03_12","고린도전서 9-10장, 시편 41장");
        put("03_13","고린도전서 11-12장, 시편 42장");put("03_14","고린도전서 13-14장, 시편 43장");put("03_15","고린도전서 15-16장, 시편 44장");put("03_16","고린도후서 1-2장, 시편 45-46장");
        put("03_17","고린도후서 3-4장, 시편 47장");put("03_18","고린도후서 5-6장, 시편 48장");put("03_19","고린도후서 7-8장, 시편 49장");put("03_20","고린도후서 9-10장, 시편 50장");
        put("03_21","고린도후서 11-12장, 시편 51장");put("03_22","고린도후서 13장, 시편 52장");put("03_23","갈라디아서 1-2장, 시편 53장");put("03_24","갈라디아서 3-4장, 시편 54장");
        put("03_25","갈라디아서 5-6장, 시편 55장");put("03_26","에베소서 1-2장, 시편 56장");put("03_27","에베소서 3-4장, 시편 57장");put("03_28","에베소서 5-6장, 시편 58장");
        put("03_29","빌립보서 1-2장, 시편 59장");put("03_30","빌립보서 3-4장, 시편 60장");put("03_31","골로새서 1-2장, 시편 61장");

        // 4 월
        put("04_01","골로새서 3-4장, 시편 62장");put("04_02","데살로니가전서 1-2장, 시편 63장");put("04_03","데살로니가전서 3-4장, 시편 64장");put("04_04","데살로니가전서 5장, 시편 65장");
        put("04_05","데살로니가후서 1-2장, 시편 66장");put("04_06","데살로니가후서 3장, 시편 67장");put("04_07","디모데전서 31장, 시편 68장");put("04_08","디모데전서 1-2장, 시편 69장");
        put("04_09","디모데전서 3-4장, 시편 70장");put("04_10","디모데후서 1-2장, 시편 71장");put("04_11","디모데후서 3장, 시편 72장");put("04_12","디도서 1-2장, 시편 73장");
        put("04_13","디도서 3장, 시편 74-75장");put("04_14","빌레몬서 1장, 시편 76-77장");put("04_15","히브리서 1-2장, 시편 78장");put("04_16","히브리서 3-4장, 시편 79장");
        put("04_17","히브리서 5-6장, 시편 80장");put("04_18","히브리서 7-8장, 시편 81장");put("04_19","히브리서 9-10장, 시편 82장");put("04_20","히브리서 11-12장, 시편 83장");
        put("04_21","히브리서 13장, 시편 84장");put("04_22","야고보서 1-2장, 시편 84장");put("04_23","야고보서 3-5장, 시편 85장");put("04_24","베드로전서 1-2장, 시편 86장");
        put("04_25","베드로전서 3-5장, 시편 87장");put("04_26","베드로후서 1-3장, 시편 88장");put("04_27","요한일서 1-2장, 시편 89장");put("04_28","요한일서 3-5장, 90장");
        put("04_29","요한이서 1장, 시편 91-92장");put("04_30","요한삼서 1장, 시편 93-94장");

        // 5 월
        put("05_01","유다서 1장, 잠언 1장");put("05_02","요한계시록 1-2장, 잠언 2장");put("05_03","요한계시록 3-4장, 잠언 3장");put("05_04","요한계시록 5-6장, 잠언 4장");
        put("05_05","요한계시록 7-8장, 잠언 5장");put("05_06","요한계시록 9-10장, 잠언 6장");put("05_07","요한계시록 11-12장, 잠언 7장");put("05_08","요한계시록 13-14장, 잠언 8장");
        put("05_09","요한계시록 15-16장, 잠언 9장");put("05_10","요한계시록 17-18장, 잠언 10장");put("05_11","요한계시록 19-20장, 잠언 11장");put("05_12","요한계시록 21-22장, 잠언 12장");
        put("05_13","창세기 1-2장, 잠언 13장");put("05_14","창세기 3-4장, 잠언 14장");put("05_15","창세기 5-6장, 잠언 15장");put("05_16","창세기 7-8장, 잠언 16장");
        put("05_17","창세기 9-10장, 잠언 17장");put("05_18","창세기 11-12장, 잠언 18장");put("05_19","창세기 13-14장, 잠언 19장");put("05_20","창세기 15-16장, 잠언 20장");
        put("05_21","창세기 17-18장, 잠언 21장");put("05_22","창세기 19-20장, 잠언 22장");put("05_23","창세기 21-22장, 잠언 23장");put("05_24","창세기 23-24장, 잠언 24장");
        put("05_25","창세기 25-26장, 잠언 25장");put("05_26","창세기 27-28장, 잠언 26장");put("05_27","창세기 29-30장, 잠언 27장");put("05_28","창세기 31-32장, 잠언 28장");
        put("05_29","창세기 33-34장, 잠언 29장");put("05_30","창세기 35-36장, 잠언 30장");put("05_31","창세기 37-38장, 잠언 31장");

        // 6 월
        put("06_01","창세기 39-40장, 시편 95장");put("06_02","창세기 41-42장, 시편 96장");put("06_03","창세기 43-44장, 시편 97장");put("06_04","창세기 45-46장, 시편 98장");
        put("06_05","창세기 47-48장, 시편 99장");put("06_06","창세기 49-50장, 시편 100장");put("06_07","출애굽기 1-2장, 시편 101장");put("06_08","출애굽기 3-4장, 시편 102장");
        put("06_09","출애굽기 5-6장, 시편 103장");put("06_10","출애굽기 7-8장, 시편 104장");put("06_11","출애굽기 9-10장, 시편 105장");put("06_12","출애굽기 11-12장, 시편 106장");
        put("06_13","출애굽기 13-14장, 시편 107장");put("06_14","출애굽기 15-16장, 시편 108장");put("06_15","출애굽기 17-18장, 시편 109장");put("06_16","출애굽기 19-20장, 시편 110장");
        put("06_17","출애굽기 21-22장, 시편 111장");put("06_18","출애굽기 23-24장, 시편 112장");put("06_19","출애굽기 25-26장, 시편 113장");put("06_20","출애굽기 27-28장, 시편 114장");
        put("06_21","출애굽기 29-30장, 시편 115장");put("06_22","출애굽기 31-32장, 시편 116장");put("06_23","출애굽기 33-34장, 시편 117장");put("06_24","출애굽기 35-36장, 시편 118장");
        put("06_25","출애굽기 37-38장, 시편 119장");put("06_26","출애굽기 39-40장, 시편 120장");put("06_27","레위기 1-2장, 시편 121장");put("06_28","레위기 3-4장, 시편 122장");
        put("06_29","레위기 5-6장, 시편 123장");put("06_30","레위기 7-8장, 시편 124장");

        // 7 월
        put("07_01","레위기 9-10장, 시편 125장");put("07_02","레위기 11-12장, 시편 126장");put("07_03","레위기 13-14장, 시편 127장");put("07_04","레위기 15-16장, 시편 128장");
        put("07_05","레위기 17-18장, 시편 129장");put("07_06","레위기 19-20장, 시편 130장");put("07_07","레위기 21-22장, 시편 131장");put("07_08","레위기 23-24장, 시편 132장");
        put("07_09","레위기 25-26장, 시편 133장");put("07_10","레위기 27장, 시편 134-135장");put("07_11","민수기 1-2장, 시편 136장");put("07_12","민수기 3-4장, 시편 137장");
        put("07_13","민수기 5-6장, 시편 138장");put("07_14","민수기 7-8장, 시편 139장");put("07_15","민수기 9-10장, 시편 140장");put("07_16","민수기 11-12장, 시편 141장");
        put("07_17","민수기 13-14장, 시편 142장");put("07_18","민수기 15-16장, 시편 143장");put("07_19","민수기 17-18장, 시편 144장");put("07_20","민수기 19-20장, 시편 145장");
        put("07_21","민수기 21-22장, 시편 146장");put("07_22","민수기 23-24장, 시편 147장");put("07_23","민수기 25-26장, 시편 148");put("07_24","민수기 27-28장, 시편 149장");
        put("07_25","민수기 29-30장, 시편 150장");put("07_26","민수기 31-32장, 잠언 26장");put("07_27","민수기 33-34장, 잠언 27장");put("07_28","민수기 35-36장, 잠언 28장");
        put("07_29","신명기 1-2장, 잠언 29장");put("07_30","신명기 3-4장, 잠언 30장");put("07_31","신명기 5-6장, 잠언 31장");

        // 8 월
        put("08_01","신명기 7-8장, 잠언 1장");put("08_02","신명기 9-10장, 잠언 2장");put("08_03","신명기 11-12장, 잠언 3장");put("08_04","신명기 13-14장, 잠언 4장");
        put("08_05","신명기 15-16장, 잠언 5장");put("08_06","신명기 17-18장, 잠언 6장");put("08_07","신명기 19-20장, 잠언 7장");put("08_08","신명기 21-22장, 잠언 8장");
        put("08_09","신명기 23-24장, 잠언 9장");put("08_10","신명기 25-26장, 잠언 10장");put("08_11","신명기 27-28장, 잠언 11장");put("08_12","신명기 29-30장, 잠언 12장");
        put("08_13","신명기 31-32장, 잠언 13장");put("08_14","신명기 33-34장, 잠언 14장");put("08_15","여호수아 1-2장, 잠언 15장");put("08_16","여호수아 3-4장, 잠언 16장");
        put("08_17","여호수아 5-6장, 잠언 17장");put("08_18","여호수아 7-8장, 잠언 18장");put("08_19","여호수아 9-10장, 잠언 19장");put("08_20","여호수아 11-12장, 잠언 20장");
        put("08_21","여호수아 13-14장, 잠언 21장");put("08_22","여호수아 15-16장, 잠언 22장");put("08_23","여호수아 17-18장, 잠언 23장");put("08_24","여호수아 19-20장, 잠언 24장");
        put("08_25","여호수아 21-22장, 잠언 25장");put("08_26","여호수아 23-24장, 잠언 26장");put("08_27","사사기 1-2장, 잠언 27장");put("08_28","사사기 3-4장, 잠언 28장");
        put("08_29","사사기 5-6장, 잠언 29장");put("08_30","사사기 7-8장, 잠언 30장");put("08_31","사사기 9-10장, 잠언 31장");

        // 9 월
        put("09_01","사사기 11-12장, 시편 1장");put("09_02","사사기 13-14장, 시편 2장");put("09_03","사사기 15-16장, 시편 3장");put("09_04","사사기 17-18장, 시편 4장");
        put("09_05","사사기 19-20장, 시편 5장");put("09_06","사사기 21장, 시편 6-7장");put("09_07","룻기 1-2장, 시편 8-9장");put("09_08","룻기 3-4장, 시편 10-11장");
        put("09_09","사무엘상 1-2장, 시편 12장");put("09_10","사무엘상 3-4장, 시편 13장");put("09_11","사무엘상 5-6장, 시편 14장");put("09_12","사무엘상 7-8장, 시편 15장");
        put("09_13","사무엘상 9-10장, 시편 16장");put("09_14","사무엘상 11-12장, 시편 17장");put("09_15","사무엘상 13-14장, 시편 18장");put("09_16","사무엘상 15-16장, 시편 19장");
        put("09_17","사무엘상 17-18장, 시편 20장");put("09_18","사무엘상 19-20장, 시편 21장");put("09_19","사무엘상 21-22장, 시편 22장");put("09_20","사무엘상 23-24장, 시편 23장");
        put("09_21","사무엘상 25-26장, 시편 24장");put("09_22","사무엘상 27-28장, 시편 25장");put("09_23","사무엘상 29-30장, 시편 26장");put("09_24","사무엘상 31장, 시편 27-28장");
        put("09_25","사무엘하 1-2장, 시편 29장");put("09_26","사무엘하 3-4장, 시편 30장");put("09_27","사무엘하 5-6장, 시편 31장");put("09_28","사무엘하 7-8장, 시편 32장");
        put("09_29","사무엘하 9-10장, 시편 33장");put("09_30","사무엘하 11-12장, 시편 34장");

        // 10 월
        put("10_01","사무엘하 13-14장, 시편 35장");put("10_02","사무엘하 15-16장, 시편 36장");put("10_03","사무엘하 17-18장, 시편 37장");put("10_04","사무엘하 19-20장, 시편 38장");
        put("10_05","사무엘하 21-22장, 시편 39장");put("10_06","사무엘하 23-24장, 시편 40장");put("10_07","열왕기상 1-2장, 시편 41장");put("10_08","열왕기상 3-4장, 시편 42장");
        put("10_09","열왕기상 5-6장, 시편 43장");put("10_10","열왕기상 7-8장, 시편 44장");put("10_11","열왕기상 9-10장, 시편 45장");put("10_12","열왕기상 11-12장, 시편 46장");
        put("10_13","열왕기상 13-14장, 시편 47장");put("10_14","열왕기상 15-16장, 시편 48장");put("10_15","열왕기상 17-18장, 시편 49장");put("10_16","열왕기상 19-20장, 시편 50장");
        put("10_17","열왕기상 21-22장, 시편 51장");put("10_18","열왕기하 1-2장, 시편 52장");put("10_19","열왕기하 3-4장, 시편 53장");put("10_20","열왕기하 5-6장, 시편 54장");
        put("10_21","열왕기하 7-8장, 시편 55장");put("10_22","열왕기하 9-10장, 시편 56장");put("10_23","열왕기하 11-12장, 시편 57장");put("10_24","열왕기하 13-14장, 시편 58장");
        put("10_25","열왕기하 15-16장, 시편 59장");put("10_26","열왕기하 17-18장, 시편 60장");put("10_27","열왕기하 19-20장, 시편 61장");put("10_28","열왕기하 21-22장, 시편 62장");
        put("10_29","열왕기하 23-24장, 시편 63장");put("10_30","열왕기하 25장, 시편 64장");put("10_31","역대상 1-2장, 시편 65장");

        // 11 월
        put("11_01","역대상 3-4장, 시편 66장");put("11_02","역대상 5-6장, 시편 67장");put("11_03","역대상 7-8장, 시편 68장");put("11_04","역대상 9-10장, 시편 69장");
        put("11_05","역대상 11-12장, 시편 70장");put("11_06","역대상 13-14장, 시편 71장");put("11_07","역대상 15-16장, 시편 72장");put("11_08","역대상 17-18장, 시편 73장");
        put("11_09","역대상 19-20장, 시편 74장");put("11_10","역대상 21-22장, 시편 75장");put("11_11","역대상 23-24장, 시편 76장");put("11_12","역대상 25-26장, 시편 77장");
        put("11_13","역대상 27-28장, 시편 78장");put("11_14","역대상 29장, 시편 79-80장");put("11_15","역대하 1-2장, 시편 81장");put("11_16","역대하 3-4장, 시편 82장");
        put("11_17","역대하 5-6장, 시편 83장");put("11_18","역대하 7-8장, 시편 84장");put("11_19","역대하 9-10장, 시편 85장");put("11_20","역대하 11-12장, 시편 86장");
        put("11_21","역대하 13-14장, 시편 87장");put("11_22","역대하 15-16장, 시편 88장");put("11_23","역대하 17-18장, 시편 89장");put("11_24","역대하 19-20장, 시편 90장");
        put("11_25","역대하 21-22장, 시편 91장");put("11_26","역대하 23-24장, 시편 92장");put("11_27","역대하 25-26장, 시편 93장");put("11_28","역대하 27-28장, 시편 94장");
        put("11_29","역대하 29-30장, 시편 95장");put("11_30","역대하 31-32장, 시편 96장");

        // 12 월
        put("12_01", "역대하 33-34장, 시편 97장");put("12_02", "역대하 35-36장, 시편 98장");put("12_03", "에스라 1-2장, 시편 99장");put("12_04", "에스라 3-4장, 시편 100장");
        put("12_05", "에스라 5-6장, 시편 101장");put("12_06", "에스라 7-8장, 시편 102장");put("12_07", "에스라 9-10장, 시편 103장");put("12_08", "느헤미야 1-2장, 시편 104장");
        put("12_09", "느헤미야 3-4장, 시편 105장");put("12_10", "느헤미야 5-6장, 시편 106장");put("12_11", "느헤미야 7-8장, 시편 107장");put("12_12", "느헤미야 9-10장, 시편 108장");
        put("12_13", "느헤미야 11-12장, 시편 109장");put("12_14", "느헤미야 13장, 시편 110장");put("12_15", "에스더 1-2장, 시편 111장");put("12_16", "에스더 3-4장, 시편 112장");
        put("12_17", "에스더 5-6장, 시편 113장");put("12_18", "에스더 7-8장, 시편 114장");put("12_19", "에스더 9-10장, 시편 115장");put("12_20", "욥기 1-2장, 시편 116장");
        put("12_21", "욥기 3-4장, 시편 117장");put("12_22", "욥기 5-6장, 시편 118장");put("12_23", "욥기 7-8장, 시편 119장");put("12_24", "욥기 9-10장, 시편 120장");
        put("12_25", "욥기 11-12장, 시편 121장");put("12_26", "욥기 13-14장, 시편 122장");put("12_27", "욥기 15-16장, 시편 123장");put("12_28", "욥기 17-18장, 시편 124장");
        put("12_29", "욥기 19-20장, 시편 125장");put("12_30", "욥기 21-22장, 시편 126장");put("12_31", "욥기 23-24장, 시편 127장");
    }};

    private static final Map<String, String> servingSchedule_eng = new HashMap<String, String>()
    {{
        // Date format should be MM_dd
        // 2019 년
        // 1 월
        put("01_01","Matthew 1-2, Proverbs 1");put("01_02","Matthew 3-4, Proverbs 2");put("01_03","Matthew 5-6, Proverbs 3");put("01_04","Matthew 7-8, Proverbs 4");
        put("01_05","Matthew 9-10, Proverbs 5");put("01_06","Matthew 11-12, Proverbs 6");put("01_07","Matthew 13-14, Proverbs 7");put("01_08","Matthew 15-16, Proverbs 8");
        put("01_09","Matthew 17-18, Proverbs 9");put("01_10","Matthew 19-20, Proverbs 10");put("01_11","Matthew 21-22, Proverbs 11");put("01_12","Matthew 23-24, Proverbs 12");
        put("01_13","Matthew 25-26, Proverbs 13");put("01_14","Matthew 27-28, Proverbs 14");put("01_15","Mark 1-2, Proverbs 15");put("01_16","Mark 4-5, Proverbs 16");
        put("01_17","Mark 5-6, Proverbs 17");put("01_18","Mark 7-8, Proverbs 18");put("01_19","Mark 9-10, Proverbs 19");put("01_20","Mark 11-12, Proverbs 20");
        put("01_21","Mark 13-14, Proverbs 21");put("01_22","Mark 15-16, Proverbs 22");put("01_23","Luke 1-2, Proverbs 23");put("01_24","Luke 3-4, Proverbs 24");
        put("01_25","Luke 5-6, Proverbs 25");put("01_26","Luke 7-8, Proverbs 26");put("01_27","Luke 9-10, Proverbs 27");put("01_28","Luke 11-12, Proverbs 28");
        put("01_29","Luke 13-14, Proverbs 29");put("01_30","Luke 15-16, Proverbs 30");put("01_31","Luke 17-18, Proverbs 31");

        // 2 월
        put("02_01","Luke 19-20, Psalms 1");put("02_02","Luke 21-22, Psalms 2");put("02_03","Luke 23-24, Psalms 3");
        put("02_04","John 1-2, Psalms 4");put("02_05","John 3-4, Psalms 5");put("02_06","John 5-6, Psalms 6");
        put("02_07","John 7-8, Psalms 7");put("02_08","John 9-10, Psalms 8");put("02_09","John 11-12, Psalms 9");
        put("02_10","John 13-14, Psalms 10");put("02_11","John 15-16, Psalms 11");put("02_12","John 17-18, Psalms 12");
        put("02_13","John 19-20, Psalms 13");put("02_14","John 21, Psalms 14");put("02_15","Acts 1-2, Psalms 15");
        put("02_16","Acts 3-4, Psalms 16");put("02_17","Acts 5-6, Psalms 17");put("02_18","Acts 7-8, Psalms 18");
        put("02_19","Acts 9-10, Psalms 19");put("02_20","Acts 11-12, Psalms 20");put("02_21","Acts 13-14, Psalms 21");
        put("02_22","Acts 15-16, Psalms 22");put("02_23","Acts 17-18, Psalms 23");put("02_24","Acts 19-20, Psalms 24");
        put("02_25","Acts 21-22, Psalms 25");put("02_26","Acts 23-24, Psalms 26");put("02_27","Acts 25-26, Psalms 27");
        put("02_28","Acts 27-28, Psalms 28");put("02_29","Romans 1-2, Psalms 29");

        // 3 월
        put("03_01","Romans 3-4, Psalms 30");put("03_02","Romans 5-6, Psalms 31");put("03_03","Romans 7-8, Psalms 32");put("03_04","Romans 9-10, Psalms 33");
        put("03_05","Romans 11-12, Psalms 34");put("03_06","Romans 13-14, Psalms 35");put("03_07","Romans 15-16, Psalms 36");put("03_08","1Corinthians 1-2, Psalms 37");
        put("03_09","1Corinthians 3-4, Psalms 38");put("03_10","1Corinthians 5-6, Psalms 39");put("03_11","1Corinthians 7-8, Psalms 40");put("03_12","1Corinthians 9-10, Psalms 41");
        put("03_13","1Corinthians 11-12, Psalms 42");put("03_14","1Corinthians 13-14, Psalms 43");put("03_15","1Corinthians 15-16, Psalms 44");put("03_16","2Corinthians 1-2, Psalms 45-46");
        put("03_17","2Corinthians 3-4, Psalms 47");put("03_18","2Corinthians 5-6, Psalms 48");put("03_19","2Corinthians 7-8, Psalms 49");put("03_20","2Corinthians 9-10, Psalms 50");
        put("03_21","2Corinthians 11-12, Psalms 51");put("03_22","2Corinthians 13, Psalms 52");put("03_23","Galatians 1-2, Psalms 53");put("03_24","Galatians 3-4, Psalms 54");
        put("03_25","Galatians 5-6, Psalms 55");put("03_26","Ephesians 1-2, Psalms 56");put("03_27","Ephesians 3-4, Psalms 57");put("03_28","Ephesians 5-6, Psalms 58");
        put("03_29","Philippians 1-2, Psalms 59");put("03_30","Philippians 3-4, Psalms 60");put("03_31","Colossians 1-2, Psalms 61");

        // 4 월
        put("04_01","Colossians 3-4, Psalms 62");put("04_02","1Thessalonians 1-2, Psalms 63");put("04_03","1Thessalonians 3-4, Psalms 64");put("04_04","1Thessalonians 5, Psalms 65");
        put("04_05","2Thessalonians 1-2, Psalms 66");put("04_06","2Thessalonians 3, Psalms 67");put("04_07","1Timothy 31, Psalms 68");put("04_08","1Timothy 1-2, Psalms 69");
        put("04_09","1Timothy 3-4, Psalms 70");put("04_10","2Timothy 1-2, Psalms 71");put("04_11","2Timothy 3, Psalms 72");put("04_12","Titus 1-2, Psalms 73");
        put("04_13","Titus 3, Psalms 74-75");put("04_14","Philemon 1, Psalms 76-77");put("04_15","Hebrews 1-2, Psalms 78");put("04_16","Hebrews 3-4, Psalms 79");
        put("04_17","Hebrews 5-6, Psalms 80");put("04_18","Hebrews 7-8, Psalms 81");put("04_19","Hebrews 9-10, Psalms 82");put("04_20","Hebrews 11-12, Psalms 83");
        put("04_21","Hebrews 13, Psalms 84");put("04_22","James 1-2, Psalms 84");put("04_23","James 3-5, Psalms 85");put("04_24","1Peter 1-2, Psalms 86");
        put("04_25","1Peter 3-5, Psalms 87");put("04_26","2Peter 1-3, Psalms 88");put("04_27","1John 1-2, Psalms 89");put("04_28","1John 3-5, 90");
        put("04_29","2John 1, Psalms 91-92");put("04_30","3John 1, Psalms 93-94");

        // 5 월
        put("05_01","Jude 1, Proverbs 1");put("05_02","Revelation 1-2, Proverbs 2");put("05_03","Revelation 3-4, Proverbs 3");put("05_04","Revelation 5-6, Proverbs 4");
        put("05_05","Revelation 7-8, Proverbs 5");put("05_06","Revelation 9-10, Proverbs 6");put("05_07","Revelation 11-12, Proverbs 7");put("05_08","Revelation 13-14, Proverbs 8");
        put("05_09","Revelation 15-16, Proverbs 9");put("05_10","Revelation 17-18, Proverbs 10");put("05_11","Revelation 19-20, Proverbs 11");put("05_12","Revelation 21-22, Proverbs 12");
        put("05_13","Genesis 1-2, Proverbs 13");put("05_14","Genesis 3-4, Proverbs 14");put("05_15","Genesis 5-6, Proverbs 15");put("05_16","Genesis 7-8, Proverbs 16");
        put("05_17","Genesis 9-10, Proverbs 17");put("05_18","Genesis 11-12, Proverbs 18");put("05_19","Genesis 13-14, Proverbs 19");put("05_20","Genesis 15-16, Proverbs 20");
        put("05_21","Genesis 17-18, Proverbs 21");put("05_22","Genesis 19-20, Proverbs 22");put("05_23","Genesis 21-22, Proverbs 23");put("05_24","Genesis 23-24, Proverbs 24");
        put("05_25","Genesis 25-26, Proverbs 25");put("05_26","Genesis 27-28, Proverbs 26");put("05_27","Genesis 29-30, Proverbs 27");put("05_28","Genesis 31-32, Proverbs 28");
        put("05_29","Genesis 33-34, Proverbs 29");put("05_30","Genesis 35-36, Proverbs 30");put("05_31","Genesis 37-38, Proverbs 31");

        // 6 월
        put("06_01","Genesis 39-40, Psalms 95");put("06_02","Genesis 41-42, Psalms 96");put("06_03","Genesis 43-44, Psalms 97");put("06_04","Genesis 45-46, Psalms 98");
        put("06_05","Genesis 47-48, Psalms 99");put("06_06","Genesis 49-50, Psalms 100");put("06_07","Exodus 1-2, Psalms 101");put("06_08","Exodus 3-4, Psalms 102");
        put("06_09","Exodus 5-6, Psalms 103");put("06_10","Exodus 7-8, Psalms 104");put("06_11","Exodus 9-10, Psalms 105");put("06_12","Exodus 11-12, Psalms 106");
        put("06_13","Exodus 13-14, Psalms 107");put("06_14","Exodus 15-16, Psalms 108");put("06_15","Exodus 17-18, Psalms 109");put("06_16","Exodus 19-20, Psalms 110");
        put("06_17","Exodus 21-22, Psalms 111");put("06_18","Exodus 23-24, Psalms 112");put("06_19","Exodus 25-26, Psalms 113");put("06_20","Exodus 27-28, Psalms 114");
        put("06_21","Exodus 29-30, Psalms 115");put("06_22","Exodus 31-32, Psalms 116");put("06_23","Exodus 33-34, Psalms 117");put("06_24","Exodus 35-36, Psalms 118");
        put("06_25","Exodus 37-38, Psalms 119");put("06_26","Exodus 39-40, Psalms 120");put("06_27","Leviticus 1-2, Psalms 121");put("06_28","Leviticus 3-4, Psalms 122");
        put("06_29","Leviticus 5-6, Psalms 123");put("06_30","Leviticus 7-8, Psalms 124");

        // 7 월
        put("07_01","Leviticus 9-10, Psalms 125");put("07_02","Leviticus 11-12, Psalms 126");put("07_03","Leviticus 13-14, Psalms 127");put("07_04","Leviticus 15-16, Psalms 128");
        put("07_05","Leviticus 17-18, Psalms 129");put("07_06","Leviticus 19-20, Psalms 130");put("07_07","Leviticus 21-22, Psalms 131");put("07_08","Leviticus 23-24, Psalms 132");
        put("07_09","Leviticus 25-26, Psalms 133");put("07_10","Leviticus 27, Psalms 134-135");put("07_11","Numbers 1-2, Psalms 136");put("07_12","Numbers 3-4, Psalms 137");
        put("07_13","Numbers 5-6, Psalms 138");put("07_14","Numbers 7-8, Psalms 139");put("07_15","Numbers 9-10, Psalms 140");put("07_16","Numbers 11-12, Psalms 141");
        put("07_17","Numbers 13-14, Psalms 142");put("07_18","Numbers 15-16, Psalms 143");put("07_19","Numbers 17-18, Psalms 144");put("07_20","Numbers 19-20, Psalms 145");
        put("07_21","Numbers 21-22, Psalms 146");put("07_22","Numbers 23-24, Psalms 147");put("07_23","Numbers 25-26, Psalms 148");put("07_24","Numbers 27-28, Psalms 149");
        put("07_25","Numbers 29-30, Psalms 150");put("07_26","Numbers 31-32, Proverbs 26");put("07_27","Numbers 33-34, Proverbs 27");put("07_28","Numbers 35-36, Proverbs 28");
        put("07_29","Deuteronomy 1-2, Proverbs 29");put("07_30","Deuteronomy 3-4, Proverbs 30");put("07_31","Deuteronomy 5-6, Proverbs 31");

        // 8 월
        put("08_01","Deuteronomy 7-8, Proverbs 1");put("08_02","Deuteronomy 9-10, Proverbs 2");put("08_03","Deuteronomy 11-12, Proverbs 3");put("08_04","Deuteronomy 13-14, Proverbs 4");
        put("08_05","Deuteronomy 15-16, Proverbs 5");put("08_06","Deuteronomy 17-18, Proverbs 6");put("08_07","Deuteronomy 19-20, Proverbs 7");put("08_08","Deuteronomy 21-22, Proverbs 8");
        put("08_09","Deuteronomy 23-24, Proverbs 9");put("08_10","Deuteronomy 25-26, Proverbs 10");put("08_11","Deuteronomy 27-28, Proverbs 11");put("08_12","Deuteronomy 29-30, Proverbs 12");
        put("08_13","Deuteronomy 31-32, Proverbs 13");put("08_14","Deuteronomy 33-34, Proverbs 14");put("08_15","Joshua 1-2, Proverbs 15");put("08_16","Joshua 3-4, Proverbs 16");
        put("08_17","Joshua 5-6, Proverbs 17");put("08_18","Joshua 7-8, Proverbs 18");put("08_19","Joshua 9-10, Proverbs 19");put("08_20","Joshua 11-12, Proverbs 20");
        put("08_21","Joshua 13-14, Proverbs 21");put("08_22","Joshua 15-16, Proverbs 22");put("08_23","Joshua 17-18, Proverbs 23");put("08_24","Joshua 19-20, Proverbs 24");
        put("08_25","Joshua 21-22, Proverbs 25");put("08_26","Joshua 23-24, Proverbs 26");put("08_27","Judges 1-2, Proverbs 27");put("08_28","Judges 3-4, Proverbs 28");
        put("08_29","Judges 5-6, Proverbs 29");put("08_30","Judges 7-8, Proverbs 30");put("08_31","Judges 9-10, Proverbs 31");

        // 9 월
        put("09_01","Judges 11-12, Psalms 1");put("09_02","Judges 13-14, Psalms 2");put("09_03","Judges 15-16, Psalms 3");put("09_04","Judges 17-18, Psalms 4");
        put("09_05","Judges 19-20, Psalms 5");put("09_06","Judges 21, Psalms 6-7");put("09_07","Ruth 1-2, Psalms 8-9");put("09_08","Ruth 3-4, Psalms 10-11");
        put("09_09","1Samuel 1-2, Psalms 12");put("09_10","1Samuel 3-4, Psalms 13");put("09_11","1Samuel 5-6, Psalms 14");put("09_12","1Samuel 7-8, Psalms 15");
        put("09_13","1Samuel 9-10, Psalms 16");put("09_14","1Samuel 11-12, Psalms 17");put("09_15","1Samuel 13-14, Psalms 18");put("09_16","1Samuel 15-16, Psalms 19");
        put("09_17","1Samuel 17-18, Psalms 20");put("09_18","1Samuel 19-20, Psalms 21");put("09_19","1Samuel 21-22, Psalms 22");put("09_20","1Samuel 23-24, Psalms 23");
        put("09_21","1Samuel 25-26, Psalms 24");put("09_22","1Samuel 27-28, Psalms 25");put("09_23","1Samuel 29-30, Psalms 26");put("09_24","1Samuel 31, Psalms 27-28");
        put("09_25","2Samuel 1-2, Psalms 29");put("09_26","2Samuel 3-4, Psalms 30");put("09_27","2Samuel 5-6, Psalms 31");put("09_28","2Samuel 7-8, Psalms 32");
        put("09_29","2Samuel 9-10, Psalms 33");put("09_30","2Samuel 11-12, Psalms 34");

        // 10 월
        put("10_01","2Samuel 13-14, Psalms 35");put("10_02","2Samuel 15-16, Psalms 36");put("10_03","2Samuel 17-18, Psalms 37");put("10_04","2Samuel 19-20, Psalms 38");
        put("10_05","2Samuel 21-22, Psalms 39");put("10_06","2Samuel 23-24, Psalms 40");put("10_07","1Kings 1-2, Psalms 41");put("10_08","1Kings 3-4, Psalms 42");
        put("10_09","1Kings 5-6, Psalms 43");put("10_10","1Kings 7-8, Psalms 44");put("10_11","1Kings 9-10, Psalms 45");put("10_12","1Kings 11-12, Psalms 46");
        put("10_13","1Kings 13-14, Psalms 47");put("10_14","1Kings 15-16, Psalms 48");put("10_15","1Kings 17-18, Psalms 49");put("10_16","1Kings 19-20, Psalms 50");
        put("10_17","1Kings 21-22, Psalms 51");put("10_18","2Kings 1-2, Psalms 52");put("10_19","2Kings 3-4, Psalms 53");put("10_20","2Kings 5-6, Psalms 54");
        put("10_21","2Kings 7-8, Psalms 55");put("10_22","2Kings 9-10, Psalms 56");put("10_23","2Kings 11-12, Psalms 57");put("10_24","2Kings 13-14, Psalms 58");
        put("10_25","2Kings 15-16, Psalms 59");put("10_26","2Kings 17-18, Psalms 60");put("10_27","2Kings 19-20, Psalms 61");put("10_28","2Kings 21-22, Psalms 62");
        put("10_29","2Kings 23-24, Psalms 63");put("10_30","2Kings 25, Psalms 64");put("10_31","1Chronicles 1-2, Psalms 65");

        // 11 월
        put("11_01","1Chronicles 3-4, Psalms 66");put("11_02","1Chronicles 5-6, Psalms 67");put("11_03","1Chronicles 7-8, Psalms 68");put("11_04","1Chronicles 9-10, Psalms 69");
        put("11_05","1Chronicles 11-12, Psalms 70");put("11_06","1Chronicles 13-14, Psalms 71");put("11_07","1Chronicles 15-16, Psalms 72");put("11_08","1Chronicles 17-18, Psalms 73");
        put("11_09","1Chronicles 19-20, Psalms 74");put("11_10","1Chronicles 21-22, Psalms 75");put("11_11","1Chronicles 23-24, Psalms 76");put("11_12","1Chronicles 25-26, Psalms 77");
        put("11_13","1Chronicles 27-28, Psalms 78");put("11_14","1Chronicles 29, Psalms 79-80");put("11_15","2Chronicles 1-2, Psalms 81");put("11_16","2Chronicles 3-4, Psalms 82");
        put("11_17","2Chronicles 5-6, Psalms 83");put("11_18","2Chronicles 7-8, Psalms 84");put("11_19","2Chronicles 9-10, Psalms 85");put("11_20","2Chronicles 11-12, Psalms 86");
        put("11_21","2Chronicles 13-14, Psalms 87");put("11_22","2Chronicles 15-16, Psalms 88");put("11_23","2Chronicles 17-18, Psalms 89");put("11_24","2Chronicles 19-20, Psalms 90");
        put("11_25","2Chronicles 21-22, Psalms 91");put("11_26","2Chronicles 23-24, Psalms 92");put("11_27","2Chronicles 25-26, Psalms 93");put("11_28","2Chronicles 27-28, Psalms 94");
        put("11_29","2Chronicles 29-30, Psalms 95");put("11_30","2Chronicles 31-32, Psalms 96");

        // 12 월
        put("12_01", "2Chronicles 33-34, Psalms 97");put("12_02", "2Chronicles 35-36, Psalms 98");put("12_03", "Ezra 1-2, Psalms 99");put("12_04", "Ezra 3-4, Psalms 100");
        put("12_05", "Ezra 5-6, Psalms 101");put("12_06", "Ezra 7-8, Psalms 102");put("12_07", "Ezra 9-10, Psalms 103");put("12_08", "Nehemiah 1-2, Psalms 104");
        put("12_09", "Nehemiah 3-4, Psalms 105");put("12_10", "Nehemiah 5-6, Psalms 106");put("12_11", "Nehemiah 7-8, Psalms 107");put("12_12", "Nehemiah 9-10, Psalms 108");
        put("12_13", "Nehemiah 11-12, Psalms 109");put("12_14", "Nehemiah 13, Psalms 110");put("12_15", "Esther 1-2, Psalms 111");put("12_16", "Esther 3-4, Psalms 112");
        put("12_17", "Esther 5-6, Psalms 113");put("12_18", "Esther 7-8, Psalms 114");put("12_19", "Esther 9-10, Psalms 115");put("12_20", "Job 1-2, Psalms 116");
        put("12_21", "Job 3-4, Psalms 117");put("12_22", "Job 5-6, Psalms 118");put("12_23", "Job 7-8, Psalms 119");put("12_24", "Job 9-10, Psalms 120");
        put("12_25", "Job 11-12, Psalms 121");put("12_26", "Job 13-14, Psalms 122");put("12_27", "Job 15-16, Psalms 123");put("12_28", "Job 17-18, Psalms 124");
        put("12_29", "Job 19-20, Psalms 125");put("12_30", "Job 21-22, Psalms 126");put("12_31", "Job 23-24, Psalms 127");
    }};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(servingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_serving, container, false);

        // Get current time (format: MM_dd)
        currentDate = new SimpleDateFormat("MM_dd", Locale.getDefault()).format(new Date());

        try {
            servingTextView = root.findViewById(R.id.txtView_serving);
            servingTextView.setText(servingSchedule_kor.get(currentDate));
        } catch (Exception e){
//            Toast.makeText(this,"관리자에게 문의해주세요.", Toast.LENGTH_SHORT).show();
        }

        image1 = root.findViewById(R.id.imageView_serving1);
        image2 = root.findViewById(R.id.imageView_serving2);
        image3 = root.findViewById(R.id.imageView_serving3);
        column = root.findViewById(R.id.serving_column);

        navigation = root.findViewById(R.id.navigation);
        horizontalScrollView = root.findViewById(R.id.horizontalScroll_serving);

        servingQT_kor = root.findViewById(R.id.text_serving);
        servingQT_kor.setMovementMethod(new ScrollingMovementMethod());
        builder = new StringBuilder();

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_korean:
                        servingQT_kor.setVisibility(View.GONE);
                        column.setVisibility(View.VISIBLE);
                        column.setImageResource(R.drawable.column_kor);
                        image1.setImageResource(R.drawable.serving1_kor);
                        image2.setImageResource(R.drawable.serving2_kor);
                        image3.setImageResource(R.drawable.serving3_kor);
                        horizontalScrollView.setVisibility(View.VISIBLE);
                        try {
                            servingTextView.setText(servingSchedule_kor.get(currentDate));
                        } catch (Exception e){
                            servingTextView.setText("관리자에게 문의해주세요.");
                        }
                        return true;
                    case R.id.menu_korean_QT:
                        servingFragement.JsoupAsyncTask jsoupAsyncTask = new servingFragement.JsoupAsyncTask();
                        jsoupAsyncTask.execute();
                        horizontalScrollView.setVisibility(View.GONE);
                        column.setVisibility(View.GONE);
                        servingQT_kor.setVisibility(View.VISIBLE);
                        try {
                            servingTextView.setText(servingSchedule_kor.get(currentDate));
                        } catch (Exception e){
                            servingTextView.setText("관리자에게 문의해주세요.");
                        }
                        return true;
                    case R.id.menu_english:
                        servingQT_kor.setVisibility(View.GONE);
                        column.setVisibility(View.VISIBLE);
                        column.setImageResource(R.drawable.column_eng);
                        image1.setImageResource(R.drawable.serving1_eng);
                        image2.setImageResource(R.drawable.serving2_eng);
                        image3.setImageResource(R.drawable.serving3_eng);
                        horizontalScrollView.setVisibility(View.VISIBLE);
                        try {
                            servingTextView.setText(servingSchedule_eng.get(currentDate));
                        } catch (Exception e){
                            servingTextView.setText("관리자에게 문의해주세요.");
                        }
                        return true;
//                    case R.id.menu_english_QT:
//                        Serving.JsoupAsyncTask jsoupAsyncTask2 = new Serving.JsoupAsyncTask();
//                        jsoupAsyncTask2.execute();
//                        horizontalScrollView.setVisibility(View.GONE);
//                        column.setVisibility(View.GONE);
//                        servingQT_kor.setVisibility(View.VISIBLE);
//                        return true;
                }
                return false;
            }
        };
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return root;
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(url_kor_QT).get();
                Elements title = doc.select("div.sboard_cont_details > p"); //parent > child: child elements that descend directly from parent, e.g.

                for (Element e : title) {
                    builder.append(e.text()).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            htmlContentInStringFormat = builder.toString();
            servingQT_kor.setText(htmlContentInStringFormat);
        }
    }
}