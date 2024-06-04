package com.example.psynessapp.Ajustes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.psynessapp.R;

import org.jetbrains.annotations.Nullable;


public class fragment_foto extends Fragment {

    public fragment_foto() {
        // Constructor vacío
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foto, container, false);

        // Configurar listeners de clic para cada ImageButton
        ImageButton image1 = view.findViewById(R.id.image1);
        ImageButton image2 = view.findViewById(R.id.imageView2);
        ImageButton image3 = view.findViewById(R.id.image3);
        ImageButton image4 = view.findViewById(R.id.image4);
        ImageButton image5 = view.findViewById(R.id.image5);
        ImageButton image6 = view.findViewById(R.id.image6);
        ImageButton image7 = view.findViewById(R.id.image7);
        ImageButton image8 = view.findViewById(R.id.image8);

        // Establecer listeners para enviar el resultado y navegar hacia atrás
        image1.setOnClickListener(v -> selectImage(R.drawable.prof1));
        image2.setOnClickListener(v -> selectImage(R.drawable.prof2));
        image3.setOnClickListener(v -> selectImage(R.drawable.prof3));
        image4.setOnClickListener(v -> selectImage(R.drawable.prof4));
        image5.setOnClickListener(v -> selectImage(R.drawable.prof5));
        image6.setOnClickListener(v -> selectImage(R.drawable.prof6));
        image7.setOnClickListener(v -> selectImage(R.drawable.prof7));
        image8.setOnClickListener(v -> selectImage(R.drawable.prof8));

        return view;
    }

    private void selectImage(int resId) {
        Bundle result = new Bundle();
        result.putInt("selectedImageResId", resId);
        getParentFragmentManager().setFragmentResult("requestKey", result);
    
    }

}