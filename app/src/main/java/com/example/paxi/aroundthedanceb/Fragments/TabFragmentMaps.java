package com.example.paxi.aroundthedanceb.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.Activities.ActivityInicio;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TabFragmentMaps extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener
{
    private AutoCompleteTextView txt_Search;

    GoogleMap mGoogleMap;
    MapView mapView;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(com.example.paxi.aroundthedanceb.R.layout.fragment_tab_maps, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView)  view.findViewById(R.id.google_map);

        if(mapView != null)
        {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        MapsInitializer.initialize(getContext());

        Marker marker;
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Añadir markers
        /*for(int i = 0; i < numeroEventos; i++)
        {
            marker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(,))
                .title(nombre_evento));

            marker.setTag(i);
        }*/
    }


    @Override
    public boolean onMarkerClick(Marker marker)
    {
        String nameEvent = marker.getTitle();
        String idEvento = marker.getId();

        //Busqueda del evento clickado
        //activity de ver evento

        return false;
    }
}
