package com.example.psynessapp.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.psynessapp.R;
import com.example.psynessapp.login;
import com.example.psynessapp.variables.AppDatabase;
import com.example.psynessapp.variables.DatabaseClient;
import com.example.psynessapp.variables.InterUsers;
import com.example.psynessapp.variables.InterUsersDAO;

public class crearPubli extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_publi);


        Toolbar toolbar = findViewById(R.id.toolbarpubli);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");

        }
        imageViewCrear = findViewById(R.id.imageCrearpubli);
        new crearPubli.CheckUserConnectedTaskusuarios().execute();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private class CheckUserConnectedTask extends AsyncTask<Void, Void, InterUsers> {

        @Override
        protected InterUsers doInBackground(Void... voids) {
            Context context = crearPubli.this;
            AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
            InterUsersDAO interUserDao = db.interUserDao();
            interUserDao.setDesconectForAllUsers();
            return interUserDao.getUserConnected();
        }

        @Override
        protected void onPostExecute(InterUsers user) {
            super.onPostExecute(user);

        }
    }
    private class CheckUserConnectedTaskusuarios extends AsyncTask<Void, Void, InterUsers> {

        @Override
        protected InterUsers doInBackground(Void... voids) {
            Context context = crearPubli.this;
            AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
            InterUsersDAO interUserDao = db.interUserDao();
            return interUserDao.getUserConnected();
        }

        @Override
        protected void onPostExecute(InterUsers user) {
            super.onPostExecute(user);

            String resourceName = user.getIImgNum().replace(".png", "");
            int imageResId = getResources().getIdentifier(resourceName, "drawable", getPackageName());


            imageViewCrear.setImageResource(imageResId);

        }
    }
}
