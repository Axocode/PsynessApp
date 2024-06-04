package com.example.psynessapp.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.psynessapp.Ajustes.ajustesperfil;
import com.example.psynessapp.MainActivity2;
import com.example.psynessapp.R;
import com.example.psynessapp.login;
import com.example.psynessapp.variables.AppDatabase;
import com.example.psynessapp.variables.DatabaseClient;
import com.example.psynessapp.variables.InterUsers;
import com.example.psynessapp.variables.InterUsersDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;


public class feed extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawer;
    ImageView perfil, perfil2;
    TextView nombre_Nav, email_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer = findViewById(R.id.drawer_layout);
        perfil = findViewById(R.id.toolbar_logo);
        ImageView imageView = findViewById(R.id.toolbar_logo);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        imageView.setOnClickListener(this);
        Button button = findViewById(R.id.btncrearpubli);
        button.setOnClickListener(this);

        View headerView = navigationView.getHeaderView(0);

        perfil2 = headerView.findViewById(R.id.nav_imagen);
        nombre_Nav= headerView.findViewById(R.id.name);
        email_nav = headerView.findViewById(R.id.email);
        new CheckUserConnectedTaskusuarios().execute();


    }





    private boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navigation_salir) {
            new CheckUserConnectedTask().execute();
        } else if (id == R.id.navigation_ajustes) {

            Intent intent = new Intent(feed.this, ajustesperfil.class);
            startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar_logo) {
            drawer.openDrawer(GravityCompat.START);
        } else if (v.getId() == R.id.btncrearpubli) {
            Intent intent = new Intent(feed.this, crearPubli.class);
            startActivity(intent);
        }
    }

    private class CheckUserConnectedTask extends AsyncTask<Void, Void, InterUsers> {

        @Override
        protected InterUsers doInBackground(Void... voids) {
            Context context = feed.this;
            AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
            InterUsersDAO interUserDao = db.interUserDao();
            interUserDao.setDesconectForAllUsers();
            return interUserDao.getUserConnected();
        }

        @Override
        protected void onPostExecute(InterUsers user) {
            super.onPostExecute(user);
            if (user == null) {
                Intent intent = new Intent(feed.this, login.class);
                startActivity(intent);
                finish();
            }
        }
    }
    private class CheckUserConnectedTaskusuarios extends AsyncTask<Void, Void, InterUsers> {

        @Override
        protected InterUsers doInBackground(Void... voids) {
            Context context = feed.this;
            AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
            InterUsersDAO interUserDao = db.interUserDao();
            return interUserDao.getUserConnected();
        }

        @Override
        protected void onPostExecute(InterUsers user) {
            super.onPostExecute(user);
            String newUserName = user.getIUser();
            String newUserEmail = user.getIEmail();
            String resourceName = user.getIImgNum().replace(".png", "");
            int imageResId = getResources().getIdentifier(resourceName, "drawable", getPackageName());


            nombre_Nav.setText(newUserName);
            email_nav.setText(newUserEmail);
            perfil.setImageResource(imageResId);
            perfil2.setImageResource(imageResId);

        }
    }
}
