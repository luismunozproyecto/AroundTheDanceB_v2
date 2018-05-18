package com.example.paxi.aroundthedanceb.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;

public class RecyclerHolder extends RecyclerView.ViewHolder
{
    private TextView RNameEvent, RDateEvent;

    public RecyclerHolder(View itemView)
    {
        super(itemView);
        RNameEvent = (TextView) itemView.findViewById(R.id.recyclerlinea_text_nameevent);
        RDateEvent = (TextView) itemView.findViewById(R.id.recyclerlinea_text_fechaevent);
    }

    public void bind(Evento entity)
    {
        RNameEvent.setText(entity.getNombre());
        RDateEvent.setText(entity.getFechaInicio());
    }
}
