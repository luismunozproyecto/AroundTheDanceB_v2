package com.example.paxi.aroundthedanceb.Maps;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MapsCustomMarker implements GoogleMap.InfoWindowAdapter
{
    private Context context;

    public MapsCustomMarker(Context ctx)
    {
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker)
    {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker)
    {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.maps_custom_marker, null);

        TextView name_Event = view.findViewById(R.id.marker_name);
        ImageView image_Event = view.findViewById(R.id.marker_image);

        name_Event.setText(marker.getTitle());

        Evento evento = (Evento) marker.getTag();

        //Imagen del marker
        //int imageId = context.getResources().getIdentifier(evento.getImage().toLowerCase(), "drawable", context.getPackageName());
        image_Event.setImageResource(R.drawable.profile);

        return view;
    }
}
