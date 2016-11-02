package com.example.android.jokesactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity);

        Intent intent = getIntent();

        Bundle extraData = intent.getExtras();

        String joke = extraData.getString("Joke");

        TextView textView = (TextView) findViewById(R.id.jokeDisplay);
        textView.setText(joke);
    }
}
