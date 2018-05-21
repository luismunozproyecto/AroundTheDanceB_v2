package com.example.paxi.aroundthedanceb.FragmentsTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class TabFragmentMaps extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener
{
    GoogleMap mGoogleMap;
    MapView mapView;
    View view;
    ArrayList<Evento> lista_eventos;

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
        MapsInitializer.initialize(getContext());

        Marker marker;

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //region Markers

        /*for(int i = 0; i < lista_eventos.size(); i++)
        {
            marker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lista_eventos.get(i).getLat(),lista_eventos.get(i).getLon()))
                .title(lista_eventos.get(i).getNombre()));

            marker.setTag(i);
        }*/

        //endregion
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
