package com.example.roomhunter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;



public class RoomsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Room.class);
        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("tGTzYDHzUoPEKoSc5tXr0wadS2KRtLeUhU9narzj")
                .clientKey("2YzayS7eGyUh5hiFWp26KaN45RwpXThtoduahC26")
                .server("https://parseapi.back4app.com/")
                .build());
    }


}

