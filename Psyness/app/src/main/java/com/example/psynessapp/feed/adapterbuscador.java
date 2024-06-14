package com.example.psynessapp.feed;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.psynessapp.R;

import java.util.ArrayList;
import java.util.List;

public class adapterbuscador extends RecyclerView.Adapter<adapterbuscador.ViewHolder> {
    private List<String> itemList;
    private List<String> itemListFull;

    public adapterbuscador(List<String> itemList) {
        this.itemList = itemList;
        itemListFull = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buscaditos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtnombredeperfir);
        }
    }

    public void filter(String text) {
        itemList.clear();
        if (text.isEmpty()) {
            itemList.addAll(itemListFull);
        } else {
            text = text.toLowerCase();
            for (String item : itemListFull) {
                if (item.toLowerCase().contains(text)) {
                    itemList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
