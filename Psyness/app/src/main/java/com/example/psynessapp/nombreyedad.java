package com.example.psynessapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.psynessapp.variables.AppDatabase;
import com.example.psynessapp.variables.DatabaseClient;
import com.example.psynessapp.variables.InterUsers;
import com.example.psynessapp.variables.InterUsersDAO;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class nombreyedad extends Fragment implements View.OnClickListener {
    EditText nombresito, edad1;
    Button seguir;
    TextView textView44;
    public nombreyedad() {
        // Required empty public constructor
    }


    public static nombreyedad newInstance(String param1, String param2) {
        nombreyedad fragment = new nombreyedad();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nombreyedad, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seguir = view.findViewById(R.id.btnsigconf);
        seguir.setOnClickListener(this);

        nombresito = view.findViewById(R.id.crearnom);
        edad1 = view.findViewById(R.id.edadUsuario);
        textView44 = view.findViewById(R.id.textView4);

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
                URL url = new URL("http://axocode.gerdoc.com/Psyness/presignin?iuser="+nombresito.getText().toString()+
                        "&iage="+edad1.getText().toString()+"&apipassword="+apipass);
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
                    textView44.setText(status);
                    if (textView44.getText().equals("Correcto.")) {
                        Bundle args = new Bundle();
                        args.putString("nombresito", nombresito.getText().toString());
                        args.putString("edad1", edad1.getText().toString());

                        // Asegúrate de que estás usando navController para navegar y pasar el Bundle
                        NavController navController = NavHostFragment.findNavController(nombreyedad.this);
                        navController.navigate(R.id.action_nombreyedad_to_correoycontra2, args);
                    }
                }
            }
    }

}