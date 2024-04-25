package com.example.psynessapp.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.psynessapp.R;

public class crearPubli extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_publi);
        ImageButton closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
