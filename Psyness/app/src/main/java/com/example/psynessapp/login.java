package com.example.psynessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity implements View.OnClickListener {
    Button buttoncrcuenta;
    Button buttoniniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
            Intent intento = new Intent(this , feed.class);
            startActivity(intento);
        }
    }
}