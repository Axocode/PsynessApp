package com.example.psynessapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jetbrains.annotations.Nullable;

public class correoycontra extends Fragment implements View.OnClickListener {
    Button btncrearcuenta;

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
    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button)v).getText().toString();
        if (cadenita.equals("Siguiente")){
            Intent intento = new Intent(getActivity() , feed.class);
            startActivity(intento);

        }
    }
}