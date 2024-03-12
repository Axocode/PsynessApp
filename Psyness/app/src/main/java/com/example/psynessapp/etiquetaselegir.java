package com.example.psynessapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.slider.Slider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class etiquetaselegir extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etiquetaselegir);
        configureSlider((Slider) findViewById(R.id.slider1));
        configureSlider((Slider) findViewById(R.id.slider2));
        configureSlider((Slider) findViewById(R.id.slider3));
        configureSlider((Slider) findViewById(R.id.slider4));
        configureSlider((Slider) findViewById(R.id.slider5));
        configureSlider((Slider) findViewById(R.id.slider6));
        configureSlider((Slider) findViewById(R.id.slider7));
        configureSlider((Slider) findViewById(R.id.slider8));
        configureSlider((Slider) findViewById(R.id.slider9));
        configureSlider((Slider) findViewById(R.id.slider10));
        configureSlider((Slider) findViewById(R.id.slider11));
        Button buttonconfirmareti = findViewById(R.id.btnetiquetasconfirmar);
        buttonconfirmareti.setOnClickListener(this);
    }

    private void configureSlider(final Slider slider) {
        slider.setValue(0);

        // Configurar los colores iniciales para el valor 0
        slider.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.azulcasiblanco)));
        slider.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.azulcasiblanco)));
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if (value == 0) {
                    slider.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.azulcasiblanco)));
                    slider.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.azulcasiblanco)));
                } else if (value == 1 ) {
                    slider.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.azulmasclaro)));
                    slider.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.azulmasclaro)));
                } else if (value == 2) {
                    slider.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.azulcielo)));
                    slider.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.azulcielo)));

                } else if (value == 3 ) {
                    slider.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.moradocasiblanco)));
                    slider.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.moradocasiblanco)));

                } else if (value == 4) {
                    slider.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.moradomasclaro)));
                    slider.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.moradomasclaro)));


                } else if (value == 5) {
                    slider.setTrackTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.morado)));
                    slider.setThumbTintList(ColorStateList.valueOf(ContextCompat.getColor(etiquetaselegir.this, R.color.morado)));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button)v).getText().toString();
        if (cadenita.equals("Siguiente")){
            new FetchUserTask().execute();
        }
    }


    private class FetchUserTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String status = null;
            String apipass = "wawawawaAxolloros";
            try {
                int valorSlider1 = (int) ((Slider) findViewById(R.id.slider1)).getValue();
                int valorSlider2 = (int) ((Slider) findViewById(R.id.slider2)).getValue();
                int valorSlider3 = (int) ((Slider) findViewById(R.id.slider3)).getValue();
                int valorSlider4 = (int) ((Slider) findViewById(R.id.slider4)).getValue();
                int valorSlider5 = (int)((Slider) findViewById(R.id.slider5)).getValue();
                int valorSlider6 = (int) ((Slider) findViewById(R.id.slider6)).getValue();
                int valorSlider7 = (int) ((Slider) findViewById(R.id.slider7)).getValue();
                int valorSlider8 = (int) ((Slider) findViewById(R.id.slider8)).getValue();
                int valorSlider9 = (int) ((Slider) findViewById(R.id.slider9)).getValue();
                int valorSlider10 = (int)((Slider) findViewById(R.id.slider10)).getValue();
                int valorSlider11 = (int) ((Slider) findViewById(R.id.slider11)).getValue();

                Context context = etiquetaselegir.this;
                InterUsers users = new InterUsers();
                AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
                InterUsersDAO interUserDao = db.interUserDao();
                users =  interUserDao.getUserConnected();
                int taguser = users.getIUserNum();
                Switch switchContnid = findViewById(R.id.switchcontnid);
                boolean switchState = switchContnid.isChecked();
                int datowawa = 0;
                if (switchState){
                    datowawa = 1;
                }

                String baseUrl = "http://axocode.gerdoc.com/Psyness/recomendation?";
                String parameters = String.format("iuser=%d&Sensible=%d&Autoestima=%d&Relaciones=%d&Ansiedad=%d&Depresion=%d&Conflictos=%d&Bienestar=%d&Crecimiento=%d&Salud=%d&Transtornos=%d&Recaidas=%d&Sueno=%d",
                        taguser,datowawa, valorSlider1, valorSlider2, valorSlider3, valorSlider4, valorSlider5, valorSlider6, valorSlider7, valorSlider8, valorSlider9, valorSlider10, valorSlider11);

                String finalUrl = baseUrl + parameters + "&apipassword="+apipass;


                URL url = new URL(finalUrl);
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
                status = jsonObject.getString("error");
                return status;
            } catch (IOException | JSONException e) {
                Log.e(TAG, "Error al obtener datos de la API", e);
            }
            return status;
        }
        @Override
        protected void onPostExecute(String status) {
            if (status != null) {

                if (status.equals("exitoso")) {
                    Intent intento = new Intent(etiquetaselegir.this, feed.class);
                    startActivity(intento);
                    finish();
                }
            }
        }
    }
}

