package com.example.paxi.aroundthedanceb.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;

public class RecyclerHolder extends RecyclerView.ViewHolder
{
    private TextView
            holder_NameEvent,
            holder_FechaEvent,
            holder_UbicacionEvent;


    public RecyclerHolder(View itemView )
    {
        super(itemView);

        //Imagenes slider
        //Nombre
        //Lugar & Fecha
        //Valoracion del organizador & numero de comentarios


        holder_NameEvent = (TextView) itemView.findViewById(R.id.recyclerlinea_text_nameevent);
        holder_FechaEvent = (TextView) itemView.findViewById(R.id.recyclerlinea_text_fechaevent);
        holder_UbicacionEvent = (TextView) itemView.findViewById(R.id.recyclerlinea_text_ubicacionevent);

    }

    public void bind(Evento evento)
    {
        holder_NameEvent.setText(evento.getNombre());
        holder_FechaEvent.setText(evento.getFechaInicio());
        holder_UbicacionEvent.setText(evento.getPais() + ", " + evento.getCiudad());
    }
}

