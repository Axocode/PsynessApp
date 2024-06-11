package com.example.psynessapp.feed;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psynessapp.databinding.FragmentPublicacionesBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class publicaciones extends Fragment {
    private int ciclo = 0;
    private final int TAMANO_PAGINA = 5;
    private FragmentPublicacionesBinding binding;
    private publiAdapter adapter;
    private ArrayList<publis> publisList = new ArrayList<>();
    private boolean estaCargando = false;

    public static publicaciones newInstance() {
        return new publicaciones();
    }

    public publicaciones() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPublicacionesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configurarRecyclerView();
        configurarSwipeRefreshLayout();
        cargarMasPublicaciones();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Recargar los datos cuando el fragmento se vuelve visible
        ciclo = 0;  // Reinicia el ciclo si es necesario
        publisList.clear();  // Limpia la lista de publicaciones
        cargarMasPublicaciones();  // Vuelve a cargar las publicaciones
    }

    private void configurarRecyclerView() {
        binding.publisrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new publiAdapter();
        binding.publisrecycler.setAdapter(adapter);
        binding.publisrecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (!recyclerView.isComputingLayout() && totalItemCount <= lastVisibleItem + TAMANO_PAGINA) {
                    cargarMasPublicaciones();
                }
            }
        });
    }

    private void configurarSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            ciclo = 0;  // Reinicia el ciclo si es necesario
            publisList.clear();  // Limpia la lista de publicaciones
            cargarMasPublicaciones();  // Vuelve a cargar las publicaciones
        });
    }

    private void cargarMasPublicaciones() {
        if (estaCargando) return;

        estaCargando = true;
        OkHttpClient client = new OkHttpClient();
        String url = "http://axocode.gerdoc.com/Psyness/feed?apipassword=Gael&ciclo=" + ciclo;
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
                    List<Map<String, Object>> responseList = gson.fromJson(responseBody, listType);

                    List<publis> nuevasPublicaciones = new ArrayList<>();
                    for (Map<String, Object> item : responseList) {
                        String nombre = Objects.toString(item.get("IUser"),"");
                        String hora = Objects.toString(item.get("PubHour"), "").substring(0,5);
                        String fecha = Objects.toString(item.get("PubDate"), "");
                        String publicacion = Objects.toString(item.get("PubCont"), "");
                        String resourceName = Objects.toString(item.get("IImgNum"), "").replace(".png", "");
                        int imageResId = getResources().getIdentifier(resourceName, "drawable", getActivity().getPackageName());
                        boolean isLiked = false;

                        publis nuevaPublicacion = new publis(nombre, hora, fecha, publicacion, imageResId);
                        nuevaPublicacion.setLiked(isLiked);
                        nuevasPublicaciones.add(nuevaPublicacion);
                    }

                    if (!nuevasPublicaciones.isEmpty()) {
                        publisList.addAll(nuevasPublicaciones);
                        getActivity().runOnUiThread(() -> {
                            adapter.submitList(new ArrayList<>(publisList));
                            if (binding.swipeRefreshLayout.isRefreshing()) {
                                binding.swipeRefreshLayout.setRefreshing(false);
                            }
                            estaCargando = false;
                        });

                        ciclo++;
                    } else {
                        getActivity().runOnUiThread(() -> {
                            if (binding.swipeRefreshLayout.isRefreshing()) {
                                binding.swipeRefreshLayout.setRefreshing(false);
                            }
                            estaCargando = false;
                        });
                    }
                } else {
                    getActivity().runOnUiThread(() -> {
                        if (binding.swipeRefreshLayout.isRefreshing()) {
                            binding.swipeRefreshLayout.setRefreshing(false);
                        }
                        estaCargando = false;
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(() -> {
                    if (binding.swipeRefreshLayout.isRefreshing()) {
                        binding.swipeRefreshLayout.setRefreshing(false);
                    }
                    estaCargando = false;
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

