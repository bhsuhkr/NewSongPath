package com.newsong.newsongtime.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.newsong.newsongtime.JooBoActivity;
import com.newsong.newsongtime.OutreachActivity;
import com.newsong.newsongtime.R;
import com.newsong.newsongtime.SermonActivity;
import com.newsong.newsongtime.TrackActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HomeFragment extends Fragment {

    private static final String homepageURL = "http://www.newsongdallas.org/";
    private static final String familyURL = "https://url.kr/NlTV9c";
    private HomeViewModel homeViewModel;
    private TextView textViewHtmlDocument;
    private ImageView imageViewHtmlDocument;
    private Button sermonBtn, homepageBtn, outreachBtn, jooboBtn, trackBtn, familyBtn;
    Map<String, String> uriList = new HashMap<String, String>();
    String tempID;
    String returnUri;
    String returnUri2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);


        textViewHtmlDocument = root.findViewById(R.id.text_home);
        imageViewHtmlDocument = root.findViewById(R.id.image_view);
        loadUri();


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

    public void loadUri() {
        SharedPreferences pSharedPref = getContext().getSharedPreferences("mainPage", Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("Track_mainPage", (new JSONObject()).toString());
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
                            StorageReference islandRef = FirebaseStorage.getInstance().getReference().child("main_pic.jpg");
                            final long ONE_MEGABYTE = 1024 * 1024;
                            islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    // Data for "images/island.jpg" is returns, use this as needed
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                                    imageViewHtmlDocument.setImageBitmap(Bitmap.createScaledBitmap(bmp, imageViewHtmlDocument.getWidth(),
                                            imageViewHtmlDocument.getHeight(), false));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle any errors
                                }
                            });

                            URL url = new URL(uriList.get("homepage"));
                            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                            String inputLine;
                            returnUri = "";
                            while ((inputLine = in.readLine()) != null)
                                returnUri += inputLine + "\n";
                            in.close();
                            textViewHtmlDocument.setText(returnUri);

                            URL url2 = new URL(uriList.get("youtube"));
                            BufferedReader in2 = new BufferedReader(new InputStreamReader(url2.openStream()));
                            String inputLine2;
                            returnUri2 = "";
                            while ((inputLine2 = in2.readLine()) != null)
                                returnUri2 += inputLine2;
                            in2.close();

                            tempID = returnUri2.split("=", 2)[1];
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

}