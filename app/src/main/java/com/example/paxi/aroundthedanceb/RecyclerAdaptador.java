package com.example.paxi.aroundthedanceb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Modelos.Evento;

public class RecyclerAdaptador extends RecyclerView.Adapter implements  View.OnClickListener
{
    private ArrayList<Evento> datos;
    View.OnClickListener listener;

    public RecyclerAdaptador(ArrayList<Evento> datos)
    {
        this.datos = datos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View intemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerlinea, viewGroup, false);

        intemView.setOnClickListener(this);

        RecyclerHolder holder = new RecyclerHolder(intemView);

        return holder;
    }

    public void Click(View.OnClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i)
    {
        Evento item = datos.get(i);
        ((RecyclerHolder)holder).bind(item);
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
