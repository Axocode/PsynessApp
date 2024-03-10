package com.example.psynessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.psynessapp.variables.AppDatabase;
import com.example.psynessapp.variables.DatabaseClient;
import com.example.psynessapp.variables.InterUsers;
import com.example.psynessapp.variables.InterUsersDAO;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                new CheckUserConnectedTask().execute();
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea,2000);
    }

    private class CheckUserConnectedTask extends AsyncTask<Void, Void, InterUsers> {

        @Override
        protected InterUsers doInBackground(Void... voids) {
            Context context = MainActivity.this;
            AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
            InterUsersDAO interUserDao = db.interUserDao();
            return interUserDao.getUserConnected();
        }

        @Override
        protected void onPostExecute(InterUsers user) {
            super.onPostExecute(user);
            if (user != null ) {
                Intent intento = new Intent(MainActivity.this, feed.class);
                startActivity(intento);
            } else {
                Intent intento = new Intent(MainActivity.this, login.class);
                startActivity(intento);
            }
        }
    }
}
