package com.newsong.newsongtime.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.newsong.newsongtime.JooBoActivity;
import com.newsong.newsongtime.OutreachActivity;
import com.newsong.newsongtime.R;
import com.newsong.newsongtime.SermonActivity;
import com.newsong.newsongtime.TrackActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HomeFragment extends Fragment {

    private static final String url = "http://www.newsongdallas.org/tong/s_board/read.asp?board_seq=28&board_sub_seq=1&view_sub_seq=0&seq=2562&lef=&sublef=&page=1&search_select=&search_text=";
    private static final String homepageURL = "http://www.newsongdallas.org/";
    private static final String familyURL = "https://url.kr/NlTV9c";
    StringBuilder builder;
    private HomeViewModel homeViewModel;
    private TextView textViewHtmlDocument;
    private ImageView imageViewHtmlDocument;
    private Button sermonBtn, homepageBtn, outreachBtn, jooboBtn, trackBtn, familyBtn;
    Map<String, String> saveAnnouncement = new HashMap<String, String>();
    String tempURL = "";
    String[] splitYoutube;
    String tempID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();

        loadAnnouncement();
        textViewHtmlDocument = root.findViewById(R.id.text_home);
        textViewHtmlDocument.setText(saveAnnouncement.get("Announce"));
        imageViewHtmlDocument = root.findViewById(R.id.image_view);
        Picasso.get().load(saveAnnouncement.get("Picture")).into(imageViewHtmlDocument);


        sermonBtn = root.findViewById(R.id.sermonBtn);
        sermonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SermonActivity.class);
                intent.putExtra("YoutubeLink", tempID);
                startActivity(intent);
            }
        });

        familyBtn = root.findViewById(R.id.familyBtn);
        familyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(familyURL));
                startActivity(intent);
            }
        });

        homepageBtn = root.findViewById(R.id.homepageBtn);
        homepageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(homepageURL));
                startActivity(intent);
            }
        });

        outreachBtn = root.findViewById(R.id.outreachBtn);
        outreachBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OutreachActivity.class));
            }
        });

        jooboBtn = root.findViewById(R.id.jooboBtn);
        jooboBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), JooBoActivity.class));
            }
        });

        trackBtn = root.findViewById(R.id.track_btn);
        trackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TrackActivity.class));
            }
        });

        return root;
    }

    public void loadAnnouncement() {
        SharedPreferences pSharedPref = getContext().getSharedPreferences("announcement", Context.MODE_PRIVATE);
        builder = new StringBuilder();
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("announce_home", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    String value = (String) jsonObject.get(key);
                    saveAnnouncement.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

                for (Element e : title) {
                    if (e.text().contains("http")) {
                        tempURL = e.text();
                        splitYoutube = tempURL.split("=", 2);
                        tempID = splitYoutube[1];
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}