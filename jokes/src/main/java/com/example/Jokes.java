package com.example;

import java.util.HashMap;
import java.util.Map;

public class Jokes {


    public Map<Integer, String> jokeList = new HashMap<>();

    public Map<Integer, String> setUpJokes() {

        jokeList.put(1,"What do you call a dinosaur with no eyes? Doyouthinkhesawrus");
        jokeList.put(2,"A horse walks into a bar and the bartender says \"Why the long face?\"");
        jokeList.put(3,"My wife told me to stop singing Wonderwall or we were getting a divorce. I said Maybe...");
        jokeList.put(4, "What do you call a man with a spade in his head? Doug");
        jokeList.put(5, "What do you call a man without a spade in his head? Douglas");
        jokeList.put(6, "What's the object-oriented way to become wealthy? Inheritance");
        jokeList.put(7, "There are 10 types of people in this world. Those that know binary and those that do not.");

        return jokeList;
    }

}

