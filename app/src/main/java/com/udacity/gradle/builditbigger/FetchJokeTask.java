package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.Jokes;
import com.example.jonathanw.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

class FetchJokeTask extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    private OnJokeTaskCompleted mTaskListener;
    public Map<Integer, String> mJokeList;

    public FetchJokeTask(OnJokeTaskCompleted taskListener) {

        mTaskListener = taskListener;

        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver


            myApiService = builder.build();
        }


    }

    @Override
    protected String doInBackground(String[] params) {

        Jokes joke = new Jokes();
        mJokeList = joke.setUpJokes();
        Random random = new Random();
        int range = (mJokeList.size() - 1) + 1;
        int jokeNumber = random.nextInt(range) + 1;

        String jokeString = mJokeList.get(jokeNumber);


        try {
            return myApiService.displayJoke(jokeString).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        mTaskListener.onJokeTaskCompleted(result);
    }
}