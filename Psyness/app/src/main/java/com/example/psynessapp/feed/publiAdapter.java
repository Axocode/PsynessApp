package com.example.psynessapp.feed

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.psynessapp.R
import com.example.psynessapp.feed.publiAdapter.publiViewHolder

internal class publiAdapter : ListAdapter<publis?, publiViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): publiViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.publis_list_item, parent, false)
        return publiViewHolder(view)
    }

    override fun onBindViewHolder(holder: publiViewHolder, position: Int) {
        val publisItem = getItem(position)
        holder.bind(publisItem)
    }

    internal inner class publiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val likeAnimationView: LottieAnimationView
        private val nombredeperfil: TextView
        private val fecha: TextView
        private val hora: TextView
        private val publi: TextView
        private var isLiked = false // Estado local para controlar el toggle de la animación

        init {
            nombredeperfil = itemView.findViewById(R.id.txtnombredeperfir)
            fecha = itemView.findViewById(R.id.txtdia)
            hora = itemView.findViewById(R.id.txthora)
            publi = itemView.findViewById(R.id.txtpubli)
            likeAnimationView = itemView.findViewById(R.id.loveanimaation)
            likeAnimationView.setOnClickListener { toggleLikeAnimation() }
        }

        private fun toggleLikeAnimation() {
            likeAnimationView.removeAllAnimatorListeners() // Limpia previos listeners
            if (!isLiked) {
                likeAnimationView.setAnimation(R.raw.love2) // Asegúrate de que esto sea el recurso correcto
                likeAnimationView.playAnimation()
                likeAnimationView.addAnimatorListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        likeAnimationView.progress =
                            0f // Restablece el progreso al final de la animación
                        likeAnimationView.cancelAnimation() // Para asegurar que se detenga la animación
                    }
                })
                isLiked = true
            } else {
                likeAnimationView.progress = 0f // Restablece para el próximo clic
                likeAnimationView.cancelAnimation()
                isLiked = false
            }
        }

        fun bind(publisItem: publis?) {
            nombredeperfil.text = publisItem!!.nombre
            fecha.text = publisItem.fecha
            hora.text = publisItem.hora
            publi.text = publisItem.publicaccion
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<publis> =
            object : DiffUtil.ItemCallback<publis>() {
                override fun areItemsTheSame(oldpubli: publis, newItem: publis): Boolean {
                    return oldpubli.nombre == newItem.nombre
                }

                override fun areContentsTheSame(oldpubli: publis, newItem: publis): Boolean {
                    return oldpubli == newItem
                }
            }
    }
}
