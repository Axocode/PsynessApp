package com.example.psynessapp.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.psynessapp.Ajustes.ajustesperfil;
import com.example.psynessapp.MainActivity2;
import com.example.psynessapp.R;
import com.example.psynessapp.login;
import com.example.psynessapp.variables.AppDatabase;
import com.example.psynessapp.variables.DatabaseClient;
import com.example.psynessapp.variables.InterUsers;
import com.example.psynessapp.variables.InterUsersDAO;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;


public class feed extends AppCompatActivity implements View.OnClickListener {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private DrawerLayout drawer;
    ImageView perfil, perfil2;
    TextView nombre_Nav, email_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        FirebaseApp.initializeApp(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); // 10 segundos
        locationRequest.setFastestInterval(5000); // 5 segundos
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Actualiza la ubicación en el servidor
                    updateLocationOnServer(location);
                }
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startLocationUpdates();
        }

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("SecondActivity", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Obtener el token de FCM
                        String token = task.getResult();

                        // Guardar el token en SharedPreferences para reutilizarlo
                        getSharedPreferences("appPrefs", MODE_PRIVATE)
                                .edit()
                                .putString("fcmToken", token)
                                .apply();
                    }
                });

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

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        }
    }

    private void updateLocationOnServer(Location location) {
        // Obtener el token de FCM
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("SecondActivity", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Obtener el token de FCM
                        String token = task.getResult();
                        String url = "http://192.168.0.3:8080/Psyness/notificacion";

                        // Obtener el número de usuario de SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("appPrefs", MODE_PRIVATE);
                        int userId = sharedPreferences.getInt("userId", -1);

                        if (userId == -1) {
                            Log.w("SecondActivity", "User ID not found in SharedPreferences");
                            return;
                        }

                        JSONObject locationData = new JSONObject();
                        try {
                            locationData.put("userId", userId); // Usar el ID de usuario obtenido de SharedPreferences
                            locationData.put("latitude", location.getLatitude());
                            locationData.put("longitude", location.getLongitude());
                            locationData.put("mensaje", "Hola, ¡lindo día!"); // Mensaje de ejemplo, puedes actualizarlo según sea necesario
                            locationData.put("userToken", token);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.POST, url, locationData, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Manejar la respuesta del servidor
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // Manejar errores
                                    }
                                });

                        RequestQueue requestQueue = Volley.newRequestQueue(feed.this);
                        requestQueue.add(jsonObjectRequest);
                    }
                });
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
