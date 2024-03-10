package com.example.psynessapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.psynessapp.variables.AppDatabase;
import com.example.psynessapp.variables.DatabaseClient;
import com.example.psynessapp.variables.InterUsers;
import com.example.psynessapp.variables.InterUsersDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity implements View.OnClickListener {
    Button buttoncrcuenta,buttoniniciar;
    EditText nombre,contra;
    TextView resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombre = findViewById(R.id.nombrelogin);
        contra = findViewById(R.id.password);
        resp = findViewById(R.id.textoRespuesta);

        buttoncrcuenta = findViewById(R.id.btncrearcun);
        buttoncrcuenta.setOnClickListener(this);
        buttoniniciar = findViewById(R.id.btnlogin);
        buttoniniciar.setOnClickListener(this);
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
                URL url = new URL("http://axocode.gerdoc/Psyness/login?iuser="+nombre.getText().toString()+
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
                    Intent intento = new Intent(login.this, feed.class);
                    startActivity(intento);
                    finish();
                }
            }
        }
    }
}