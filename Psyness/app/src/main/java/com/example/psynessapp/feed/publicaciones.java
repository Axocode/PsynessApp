package com.example.psynessapp.feed;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.psynessapp.R;


import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


public class publicaciones extends Fragment {
    private com.example.psynessapp.databinding.FragmentPublicacionesBinding binding;

    public static publicaciones newInstance(String param1, String param2) {
        publicaciones fragment = new publicaciones();

        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public publicaciones() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = com.example.psynessapp.databinding.FragmentPublicacionesBinding.inflate(inflater, container, false);
        binding.publisrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<publis> publisList = new ArrayList<>();

        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));

        publisList.add(new publis("joagan","20:10","25 de abrl","Putos todos menos yo JAJAJAJA"));
        publisList.add(new publis("axek","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("a","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("g","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("v","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("1","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("45","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Esto es un easteregg" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publisList.add(new publis("Yael","20:10","25 de abrl","Putos todos menos yo JAJAJAJA" +
                "lolololo" +
                "a" +
                "a" +
                "a"));
        publiAdapter publiAdapter = new publiAdapter();
        binding.publisrecycler.setAdapter(publiAdapter);
        publiAdapter.submitList(publisList);
        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Es importante anular la referencia del binding cuando la vista se destruye
        binding = null;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
