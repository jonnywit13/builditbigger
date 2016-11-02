package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.jokesactivity.JokesActivity;


public class MainActivity extends ActionBarActivity {

    public Context mContext;
    public ProgressBar mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mLoading = (ProgressBar)findViewById(R.id.progressBar);
        mLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getJokeButton(View view) {

        mLoading.setVisibility(View.VISIBLE);

        new FetchJokeTask(new OnJokeTaskCompleted(){
            @Override
            public void onJokeTaskCompleted(String s) {
                Intent intent = new Intent(mContext, JokesActivity.class);
                intent.putExtra("Joke", s);

                mContext.startActivity(intent);
            }

        }).execute();



    }


}

