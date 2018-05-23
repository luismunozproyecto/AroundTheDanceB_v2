package com.example.paxi.aroundthedanceb.FragmentsTabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paxi.aroundthedanceb.Activities.ActivityEventsVerEvento;
import com.example.paxi.aroundthedanceb.Maps.MapsCustomMarker;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.example.paxi.aroundthedanceb.FragmentsTabs.TabFragmentEvents.EXTRA_VEREVENTO;

public class TabFragmentMaps extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener
{
    public final static String EXTRA_VEREVENTO = "EVENTO_VER";

    GoogleMap mGoogleMap;
    MapView mapView;
    View view;
    ArrayList<Evento> lista_eventos;
    MapsCustomMarker customInfoWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(com.example.paxi.aroundthedanceb.R.layout.fragment_tab_maps, container, false);

        Bundle bundle = getArguments();

        if(bundle != null)
        {
            lista_eventos = bundle.getParcelableArrayList("lista_eventos");
        }

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
        Marker marker;
        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mGoogleMap.setOnMarkerClickListener(this);
        mGoogleMap.setOnInfoWindowClickListener(this);
        customInfoWindow = new MapsCustomMarker(getContext());

        //region Markers

        for(int i = 0; i < lista_eventos.size(); i++)
        {
            marker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lista_eventos.get(i).getLat(),lista_eventos.get(i).getLon()))
                .title(lista_eventos.get(i).getNombre()));


            mGoogleMap.setInfoWindowAdapter(customInfoWindow);

            marker.setTag(lista_eventos.get(i));
        }

        //endregion
    }


    @Override
    public boolean onMarkerClick(Marker marker)
    {
        marker.showInfoWindow();

        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker)
    {
        String nameEvent = marker.getTitle();

        for(int i = 0; i < lista_eventos.size(); i++)
        {
            if(lista_eventos.get(i).getNombre().equals(nameEvent))
            {
                AbrirEvento(lista_eventos.get(i));
            }
        }
    }

    public void AbrirEvento(Evento evento)
    {
        Intent intent = new Intent(getActivity(), ActivityEventsVerEvento.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_VEREVENTO, evento);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
