package com.example.psynessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.psynessapp.feed.feed;
import com.example.psynessapp.variables.AppDatabase;
import com.example.psynessapp.variables.DatabaseClient;
import com.example.psynessapp.variables.InterUsers;
import com.example.psynessapp.variables.InterUsersDAO;
import com.google.firebase.FirebaseApp;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main2);
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                new MainActivity2.CheckUserConnectedTask().execute();
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea,0);
    }

    private class CheckUserConnectedTask extends AsyncTask<Void, Void, InterUsers> {

        @Override
        protected InterUsers doInBackground(Void... voids) {
            Context context = MainActivity2.this;
            AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
            InterUsersDAO interUserDao = db.interUserDao();
            return interUserDao.getUserConnected();
        }

        @Override
        protected void onPostExecute(InterUsers user) {
            super.onPostExecute(user);
            if (user != null ) {
                Intent intento = new Intent(MainActivity2.this, feed.class);
                startActivity(intento);
            } else {
                Intent intento = new Intent(MainActivity2.this, login.class);
                startActivity(intento);
            }
        }
    }
}