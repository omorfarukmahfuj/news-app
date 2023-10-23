package com.omorfaruk.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewsDetails extends AppCompatActivity {
    ImageView imgCover;
    TextView tvHeading, tvDetails;
    FloatingActionButton fabBtn;

    public static String TITLE = "";
    public static String DESCRIPTION = "";
    public static Bitmap MY_BITMAP = null;
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        imgCover = findViewById(R.id.imgCover);
        tvHeading = findViewById(R.id.tvHeading);
        tvDetails = findViewById(R.id.tvDetails);
        fabBtn = findViewById(R.id.fabBtn);

        textToSpeech = new TextToSpeech(NewsDetails.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });

        if (MY_BITMAP != null) imgCover.setImageBitmap(MY_BITMAP);
        tvHeading.setText(TITLE);
        tvDetails.setText(DESCRIPTION);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak("" + tvDetails.getText().toString(), textToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Stop TTS if it's currently speaking
        if (textToSpeech != null && textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }
    }

}