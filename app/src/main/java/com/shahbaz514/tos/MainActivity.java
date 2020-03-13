package com.shahbaz514.tos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    EditText EnterText;
    Button SpeakNowBtn;
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterText = findViewById(R.id.enter_text);
        SpeakNowBtn = findViewById(R.id.speak);


        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status)
            {
                // if no error taken
                if (status != TextToSpeech.ERROR)
                {
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        SpeakNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // get enter text

                String GetText = EnterText.getText().toString();

                // converting text into speach

                textToSpeech.speak(GetText, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    protected void onPause()
    {
        if (textToSpeech!=null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }
}
