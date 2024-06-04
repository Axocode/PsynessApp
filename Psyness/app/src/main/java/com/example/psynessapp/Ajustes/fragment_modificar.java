package com.example.psynessapp.Ajustes;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.psynessapp.R;
import com.example.psynessapp.feed.crearPubli;
import com.example.psynessapp.variables.AppDatabase;
import com.example.psynessapp.variables.DatabaseClient;
import com.example.psynessapp.variables.InterUsers;
import com.example.psynessapp.variables.InterUsersDAO;

import org.jetbrains.annotations.Nullable;


public class fragment_modificar extends Fragment {
    private ImageView modificarImagen;

    public fragment_modificar() {
        // Constructor vacío
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configurar listener para recibir el resultado de la selección de imagen
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // Obtén el ID del recurso de la imagen seleccionada
                int resId = bundle.getInt("selectedImageResId");
                modificarImagen.setImageResource(resId); // Actualiza la imagen
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modificar, container, false);

        modificarImagen = view.findViewById(R.id.modificarimagen);
        modificarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_fragment_modificar_to_fragment_foto);
            }
        });

        // Llama a la tarea para cargar la imagen
        new CheckUserConnectedTaskusuarios(modificarImagen).execute();

        return view;
    }

    private class CheckUserConnectedTask extends AsyncTask<Void, Void, InterUsers> {

        @Override
        protected InterUsers doInBackground(Void... voids) {
            Context context = getActivity();
            AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
            InterUsersDAO interUserDao = db.interUserDao();
            interUserDao.setDesconectForAllUsers();
            return interUserDao.getUserConnected();
        }

        @Override
        protected void onPostExecute(InterUsers user) {
            super.onPostExecute(user);
            // Puedes agregar algún código aquí si necesitas procesar al usuario obtenido
        }
    }

    private class CheckUserConnectedTaskusuarios extends AsyncTask<Void, Void, InterUsers> {
        private final ImageView imageViewCrear;

        public CheckUserConnectedTaskusuarios(ImageView imageViewCrear) {
            this.imageViewCrear = imageViewCrear;
        }

        @Override
        protected InterUsers doInBackground(Void... voids) {
            Context context = getActivity();
            AppDatabase db = DatabaseClient.getInstance(context).getAppDatabase();
            InterUsersDAO interUserDao = db.interUserDao();
            return interUserDao.getUserConnected();
        }

        @Override
        protected void onPostExecute(InterUsers user) {
            super.onPostExecute(user);

            String resourceName = user.getIImgNum().replace(".png", "");
            int imageResId = getResources().getIdentifier(resourceName, "drawable", getActivity().getPackageName());

            imageViewCrear.setImageResource(imageResId);
        }
    }


}