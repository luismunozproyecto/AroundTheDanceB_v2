package com.example.paxi.aroundthedanceb.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;

import java.util.ArrayList;

public class RecyclerAdaptador extends RecyclerView.Adapter<RecyclerHolder> implements  View.OnClickListener
{
    private ArrayList<Evento> datos;
    View.OnClickListener listener;

    public RecyclerAdaptador(ArrayList<Evento> datos)
    {
        this.datos = datos;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View intemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_linea_event, viewGroup, false);

        intemView.setOnClickListener(this);

        RecyclerHolder holder = new RecyclerHolder(intemView);

        return holder;
    }

    public void Click(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int i)
    {
        Evento item = datos.get(i);
        holder.bind(item);
    }

    @Override
    public int getItemCount()
    {
        return datos.size();
    }

    @Override
    public void onClick(View view)
    {
        if(listener != null)
        {
            listener.onClick(view);
        }
    }
}
