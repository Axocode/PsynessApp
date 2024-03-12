package com.example.psynessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.psynessapp.R;
import com.google.android.material.slider.Slider;

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
            Intent intento = new Intent(this , feed.class);
            startActivity(intento);
            finish();
        }
    }
}

