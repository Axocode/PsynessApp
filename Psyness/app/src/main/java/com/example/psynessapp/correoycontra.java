package com.example.psynessapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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

public class correoycontra extends Fragment implements View.OnClickListener {
    Button btncrearcuenta;
    EditText correo , passs;
    TextView respuesta;
    public correoycontra() {
        // Required empty public constructor
    }


    public static correoycontra newInstance(String param1, String param2) {
        correoycontra fragment = new correoycontra();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_correoycontra, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btncrearcuenta = view.findViewById(R.id.btnconfirmar);
        btncrearcuenta.setOnClickListener(this);
        correo = view.findViewById(R.id.edtxtcorreo);
        passs = view.findViewById(R.id.password12);
        respuesta = view.findViewById(R.id.textView7);
    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button)v).getText().toString();
        if (cadenita.equals("Siguiente")){
            new FetchUserTask().execute();
        }
    }

    private class FetchUserTask extends AsyncTask<Void, Void, InterUsers> {

        @Override
        protected InterUsers doInBackground(Void... voids) {
            InterUsers user = new InterUsers();
            String apipass = "wawawawaAxolloros";
            String nombresito = getArguments().getString("nombresito", "");
            String edad = getArguments().getString("edad1", "");
            String contrase = passs.getText().toString();
            String corre = correo.getText().toString();
            try {
                URL url = new URL("http://axocode.gerdoc.com/Psyness/signin?iuser="+nombresito+
                        "&ipassword="+contrase+"&iage="+edad+"&iemail="+corre+"&apipassword="+apipass);
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
                    respuesta.setText(error);
                } else {
                    user.setIUserNum(jsonObject.getInt("IUserNum"));
                    user.setIUser(jsonObject.getString("IUser"));
                    user.setIAge(jsonObject.getInt("IAge"));
                    user.setIEmail(jsonObject.getString("IEmail"));
                    user.setIUserDescription(jsonObject.getString("IUserDescription"));
                    user.setIPassword(jsonObject.getString("IPassword"));
                    user.setIUserDate(jsonObject.getString("IUserDate"));
                    user.setIRol(jsonObject.getString("IRol"));
                    user.setIUserSeguidores(jsonObject.getInt("IUserSeguidores"));
                    user.setIUserSeguidos(jsonObject.getInt("IUserSeguidos"));
                    user.setIImgNum(jsonObject.getString("IImgNum"));
                    user.setIUserActive(true);

                    respuesta.setText(user.getIUser());
                    Context context = getContext();
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
                Context context = getContext();
                AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
                InterUsersDAO interUserDao = db.interUserDao();
                return interUserDao.getUserConnected();
            }

            @Override
            protected void onPostExecute(InterUsers user) {
                super.onPostExecute(user);
                if (user != null && user.getIUser().equals(respuesta.getText().toString())) {
                    Intent intento = new Intent(getActivity() , feed.class);
                    startActivity(intento);
                    getActivity().finish();
                }
            }
        }
    }
}