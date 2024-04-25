package com.example.psynessapp.feed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.psynessapp.R;

import org.jetbrains.annotations.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link publicaciones#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class publicaciones extends Fragment {

    public static publicaciones newInstance(String param1, String param2) {
        publicaciones fragment = new publicaciones();
        // Aquí puedes pasar argumentos al fragmento si es necesario.
        Bundle args = new Bundle();
        // args.putString("key", "value");
        fragment.setArguments(args);
        return fragment;
    }

    public publicaciones() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Aquí puedes manejar argumentos pasados al fragmento si es necesario.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publicaciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
