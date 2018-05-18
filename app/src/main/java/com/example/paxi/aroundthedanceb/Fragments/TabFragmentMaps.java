package com.example.paxi.aroundthedanceb.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.Activities.ActivityInicio;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.maps.CameraUpdateFactory;
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
    private AutoCompleteTextView txt_Search;

    GoogleMap mGoogleMap;
    MapView mapView;
    View view;
    ArrayList<Evento> lista_eventos;

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
        Marker marker1,marker2,marker3,marker4,marker5,marker6,marker7;
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Añadir markers
        /*for(int i = 0; i < lista_eventos.size(); i++)
        {
            marker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lista_eventos.get(i).getLat(),lista_eventos.get(i).getLon()))
                .title(lista_eventos.get(i).getNombre()));

            marker.setTag(i);
        }*/

        /*marker1 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(
                        38.3398819,
                        -0.4934223))
                .title("asdasd"));
        marker1.setTag(1);

        marker2 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(
                        37.3754338,
                        -5.9900777))
                .title("asdasd"));
        marker2.setTag(2);

        marker3 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(
                        37.397671,
                        -6.0004626))
                .title("asdasd"));
        marker3.setTag(3);

        marker4 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(
                        38.3579029,
                        -0.5075435))
                .title("asdasd"));
        marker4.setTag(4);

        marker5 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(
                        38.5374398,
                        -0.1475051))
                .title("asdasd"));
        marker5.setTag(5);

        marker6 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(
                        41.7033604,
                        -4.8788518))
                .title("asdasd"));
        marker6.setTag(6);

        marker7 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(
                        38.3900032,
                        -0.5143003))
                .title("asdasd"));
        marker7.setTag(7);*/
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
