package com.newsong.newsongtime.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.newsong.newsongtime.JooBoActivity;
import com.newsong.newsongtime.SermonActivity;
import com.newsong.newsongtime.TrackActivity;
import com.squareup.picasso.Picasso;
import com.newsong.newsongtime.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class HomeFragment extends Fragment {

    private static final String url = "http://www.newsongdallas.org/tong/s_board/read.asp?board_seq=28&board_sub_seq=1&view_sub_seq=0&seq=2562&lef=&sublef=&page=1&search_select=&search_text=";
    private static final String homepageURL = "http://www.newsongdallas.org/";
    private static final String outreachURL = "http://www.newsongdallas.org/tong/uotc/uotc_view.asp?uotc_code=5152&uotc=24258&lef=05&sublef=undefined";

    private HomeViewModel homeViewModel;
    private TextView textViewHtmlDocument;
    private ImageView imageViewHtmlDocument;
    private Button sermonBtn, homepageBtn, outreachBtn, jooboBtn, trackBtn;
    private Dialog WebDialog1;
    private WebView URL1;

    String getUrl;
    String htmlContentInStringFormat;
    StringBuilder builder;
    String srcValue;
    String tempURL = "";
    String[] splitYoutube;
    String youtubeID = ""; //https://www.youtube.com/watch?v=awmGw6MLdHQ
    String tempID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        textViewHtmlDocument = root.findViewById(R.id.text_home);
        imageViewHtmlDocument = root.findViewById(R.id.image_view);
        textViewHtmlDocument.setMovementMethod(new ScrollingMovementMethod());
        builder = new StringBuilder();

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();


        sermonBtn  = root.findViewById(R.id.sermonBtn);
        sermonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SermonActivity.class));
            }
        });

        homepageBtn  = root.findViewById(R.id.homepageBtn);
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
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(outreachURL));
                startActivity(intent);
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
                Elements imagefile = doc.select("div.sboard_cont_details img[src]") ; //parent > child: child elements that descend directly from parent, e.g.
                for (Element e : title) {
                    if (e.text().contains("http")) {
                        tempURL = e.text();
                        splitYoutube = tempURL.split("=",2);
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
            textViewHtmlDocument.setText(htmlContentInStringFormat);
            Picasso.get().load(getUrl).into(imageViewHtmlDocument);
//            wb.loadUrl(tempURL); //"https://vimeo.com/381049225"
        }
    }
}