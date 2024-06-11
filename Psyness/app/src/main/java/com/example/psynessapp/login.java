package com.example.psynessapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class login extends AppCompatActivity implements View.OnClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    Button buttoncrcuenta, buttoniniciar;
    EditText nombre, contra;
    TextView resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);

        // Inicializar FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Configurar LocationRequest
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Inicializar LocationCallback
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Actualiza la ubicación en tu servidor
                }
            }
        };

        // Verificar y solicitar permisos de ubicación
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startLocationUpdates();
        }

        nombre = findViewById(R.id.nombrelogin);
        contra = findViewById(R.id.password);
        resp = findViewById(R.id.textoRespuesta);

        buttoncrcuenta = findViewById(R.id.btncrearcun);
        buttoncrcuenta.setOnClickListener(this);
        buttoniniciar = findViewById(R.id.btnlogin);
        buttoniniciar.setOnClickListener(this);
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

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    private void updateLocationOnServer(Location location) {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Obtener el token de FCM
                        String token = task.getResult();
                        String url = "http://axocode.gerdoc.com/Psyness/notificacion"; // Cambia esto por tu URL del servidor

                        JSONObject locationData = new JSONObject();
                        try {
                            SharedPreferences sharedPreferences = getSharedPreferences("appPrefs", MODE_PRIVATE);
                            int userId = sharedPreferences.getInt("userId", -1);
                            locationData.put("userId", userId);
                            locationData.put("latitude", location.getLatitude());
                            locationData.put("longitude", location.getLongitude());
                            locationData.put("message", "Lindo día"); // Mensaje de ejemplo, puedes actualizarlo según sea necesario
                            locationData.put("token", token);
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

                        RequestQueue requestQueue = Volley.newRequestQueue(login.this);
                        requestQueue.add(jsonObjectRequest);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button)v).getText().toString();
        if (cadenita.equals("Crear cuenta")){
            Intent intento = new Intent(this , crearcuenta.class);
            startActivity(intento);
        } else if (cadenita.equals("Iniciar sesion")) {
            new FetchUserTask().execute();
        }
    }

    private class FetchUserTask extends AsyncTask<Void, Void, InterUsers> {
        @Override
        protected InterUsers doInBackground(Void... voids) {
            InterUsers user = new InterUsers();
            String apipass = "wawawawaAxolloros";
            try {
                URL url = new URL("http://axocode.gerdoc.com/Psyness/login?iuser="+nombre.getText().toString()+
                        "&ipassword="+contra.getText().toString()+"&apipassword="+apipass);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String jsonResponse = stringBuilder.toString();

                JSONObject jsonObject = new JSONObject(jsonResponse);

                if (jsonObject.length()<5){
                    String error = jsonObject.getString("error");
                    resp.setText(error);
                } else {
                    user.setIUserNum(jsonObject.getInt("IUserNum"));
                    user.setIUser(jsonObject.getString("IUser"));
                    user.setIAge(jsonObject.getInt("IAge"));
                    user.setIEmail(jsonObject.getString("IEmail"));
                    user.setIUserDescription(jsonObject.getString("IUserDescription"));
                    user.setIPassword(jsonObject.getString("IPassword"));
                    user.setIUserDate(jsonObject.getString("IUserDate"));
                    user.setIUserHour(jsonObject.getString("IUserHour"));
                    user.setIRol(jsonObject.getString("IRol"));
                    user.setIUserSeguidores(jsonObject.getInt("IUserSeguidores"));
                    user.setIUserSeguidos(jsonObject.getInt("IUserSeguidos"));
                    user.setIImgNum(jsonObject.getString("IImgNum"));
                    user.setIUserActive(true);



                    resp.setText(user.getIUser());
                    Context context = login.this;
                    AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
                    InterUsersDAO interUserDao = db.interUserDao();
                    interUserDao.setDesconectForAllUsers();
                    interUserDao.insert(user);

                    return user;
                }
            } catch (IOException | JSONException e) {
                Log.e(TAG, "Error al obtener datos de la API", e);
            }
            return user;
        }

        @Override
        protected void onPostExecute(InterUsers user) {
            if (user != null) {
                new CheckUserConnectedTask().execute();
            }
        }

        private class CheckUserConnectedTask extends AsyncTask<Void, Void, InterUsers> {

            @Override
            protected InterUsers doInBackground(Void... voids) {
                Context context = login.this;
                AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
                InterUsersDAO interUserDao = db.interUserDao();
                return interUserDao.getUserConnected();
            }

            @Override
            protected void onPostExecute(InterUsers user) {
                super.onPostExecute(user);
                if (user != null && user.getIUser().equals(resp.getText().toString())) {
                    SharedPreferences sharedPreferences = getSharedPreferences("appPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("userId", user.getIUserNum());
                    editor.apply();
                    Intent intento = new Intent(login.this, etiquetaselegir.class);
                    startActivity(intento);
                    finish();
                }
            }
        }
    }
}
