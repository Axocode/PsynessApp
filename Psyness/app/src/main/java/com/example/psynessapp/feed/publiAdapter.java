package com.example.psynessapp.feed;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.psynessapp.R;

class publiAdapter extends ListAdapter<publis, publiAdapter.publiViewHolder> {

    public static final DiffUtil.ItemCallback<publis> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<publis>() {
                @Override
                public boolean areItemsTheSame(@NonNull publis oldpubli, @NonNull publis newItem) {
                    return oldpubli.getNombre().equals(newItem.getNombre());
                }

                @Override
                public boolean areContentsTheSame(@NonNull publis oldpubli, @NonNull publis newItem) {
                    return oldpubli.equals(newItem);
                }
            };

    protected publiAdapter() {
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public publiAdapter.publiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publis_list_item, parent, false);
        return new publiViewHolder(view);
    }

    public void onBindViewHolder(@NonNull publiAdapter.publiViewHolder holder, int position) {
        publis publisItem = getItem(position);
        holder.bind(publisItem);


    }

    public class publiViewHolder extends RecyclerView.ViewHolder {
        private final LottieAnimationView likeAnimationView;
        private TextView nombredeperfil;
        private TextView fecha;
        private TextView hora;
        private TextView publi;
        private publis publisItem;

        private boolean isLiked = false;

        publiViewHolder(@NonNull View itemView) {
            super(itemView);
            nombredeperfil = itemView.findViewById(R.id.txtnombredeperfir);
            fecha = itemView.findViewById(R.id.txtdia);
            hora = itemView.findViewById(R.id.txthora);
            publi = itemView.findViewById(R.id.txtpubli);
            likeAnimationView = itemView.findViewById(R.id.loveanimaation);

            likeAnimationView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isLiked = !isLiked;
                    publisItem.setLiked(isLiked);
                    if (isLiked) {
                        likeAnimationView.setAnimation(R.raw.love3);
                        likeAnimationView.playAnimation();
                    } else {
                        likeAnimationView.setAnimation(R.raw.malo);
                        likeAnimationView.playAnimation();
                    }
                }
            });


        }

        private void toggleLikeAnimation(boolean isLiked) {
            likeAnimationView.removeAllAnimatorListeners();

            if (isLiked) {

                likeAnimationView.setAnimation(R.raw.love3);
                likeAnimationView.playAnimation();
            } else {

                likeAnimationView.setAnimation(R.raw.malo);
                likeAnimationView.playAnimation();
            }
        }

        public void bind(publis publisItem) {
            this.publisItem = publisItem;
            nombredeperfil.setText(publisItem.getNombre());
            fecha.setText(publisItem.getFecha());
            hora.setText(publisItem.getHora());
            publi.setText(publisItem.getPublicaccion());

            isLiked = publisItem.isLiked();
            if (isLiked) {
                likeAnimationView.setAnimation(R.raw.love3);
                likeAnimationView.setProgress(1.0f);
                likeAnimationView.pauseAnimation();  // Asegura que la animación está pausada
            } else {
                likeAnimationView.setAnimation(R.raw.malo);
                likeAnimationView.setProgress(0);
                likeAnimationView.pauseAnimation();  // Asegura que la animación está pausada
            }
        }

    }


}


