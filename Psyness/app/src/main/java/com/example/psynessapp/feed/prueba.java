package com.example.psynessapp.feed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.psynessapp.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link prueba#newInstance} factory method to
 * create an instance of this fragment.
 */
public class prueba extends Fragment {

    public prueba() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prueba, container, false);

        Button enviarNotiButton = view.findViewById(R.id.enviarnoti);
        enviarNotiButton.setOnClickListener(v -> enviarNotificacionATodos());

        return view;
    }

    private void enviarNotificacionATodos() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://axocode.gerdoc.com/Psyness/enviarNotificacionATodos";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Manejar la respuesta si es necesario
                }
            }
        });
    }
}