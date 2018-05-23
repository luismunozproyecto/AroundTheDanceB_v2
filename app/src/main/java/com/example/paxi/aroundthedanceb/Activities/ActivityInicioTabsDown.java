package com.example.paxi.aroundthedanceb.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.example.paxi.aroundthedanceb.TabsFragments.TabFragmentEvents;
import com.example.paxi.aroundthedanceb.TabsFragments.TabFragmentMaps;
import com.example.paxi.aroundthedanceb.TabsFragments.TabFragmentProfile;
import com.example.paxi.aroundthedanceb.Modelos.Estilo;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.Modelos.Tipo;
import com.example.paxi.aroundthedanceb.R;

import java.util.ArrayList;

public class ActivityInicioTabsDown extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    TabFragmentEvents tabFragmentEvents;
    TabFragmentMaps tabFragmentMaps;
    TabFragmentProfile tabFragmentProfile;

    ArrayList<Evento> arrayList_eventos = new ArrayList<Evento>();

    @Override
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_tabsdown);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout_bottom);

        tabFragmentEvents = new TabFragmentEvents();
        tabFragmentMaps = new TabFragmentMaps();
        tabFragmentProfile = new TabFragmentProfile();

        simularDatos();

        setFragment(tabFragmentEvents);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {

                switch (item.getItemId())
                {
                    case R.id.tab_bottom_events:
                        setFragment(tabFragmentEvents);
                        return true;

                    case R.id.tab_bottom_map:
                        setFragment(tabFragmentMaps);
                        return true;

                    case R.id.tab_bottom_profile:
                        setFragment(tabFragmentProfile);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    public void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout_bottom, fragment);
        fragmentTransaction.commit();
    }

    //region SimularDatos

    public void simularDatos()
    {

        ArrayList<String> categorias2 = new ArrayList<String>();
        categorias2.add("5vs5");

        Estilo estilo2 = new Estilo("Popping", categorias2);
        ArrayList<Estilo> estilos = new ArrayList<Estilo>();
        estilos.add(estilo2);

        Tipo tipo = new Tipo("Battle", estilos);
        ArrayList<Tipo> tipos = new ArrayList<Tipo>();
        tipos.add(tipo);

        //region Eventos

        Evento evento1 = new Evento("1", "Battle Alicante",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 38.3777032, -0.4905777,
                tipos,
                "imagen.jpg",
                "");


        Evento evento2 = new Evento("1", "Battle Sevilla",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 40.4381311, -3.8196197,
                null,
                "imagen.jpg",
                "");


        Evento evento3 = new Evento("1", "Feel The Dance",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 52.3621549, 4.8816178,
                null,
                "imagen.jpg",
                "");

        Evento evento4 = new Evento("1", "Switch On",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 34.0207305, -118.6919159,
                null,
                "imagen.jpg",
                "");


        Evento evento5 = new Evento("1", "Alicante Dance",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 55.7580365, 37.3728602,
                null,
                "imagen.jpg",
                "paxi07");


        Evento evento6 = new Evento("1", "Sevilla Dance Center",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 37.7577627, 122.4726194,
                null,
                "imagen.jpg",
                "");

        Evento evento7 = new Evento("1", "Battle Alicante Ciudad",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 49.2578263, 123.1939435,
                null,
                "imagen.jpg",
                "paxi07");


        Evento evento8 = new Evento("1", "Jam Esta",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 35.6786436, 139.5583965,
                null,
                "imagen.jpg",
                "");


        Evento evento9 = new Evento("1", "Battle 123",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 1.3143394, 103.7041651,
                null,
                "imagen.jpg",
                "");



        //endregion

        arrayList_eventos.add(evento1);
        arrayList_eventos.add(evento2);
        arrayList_eventos.add(evento3);
        arrayList_eventos.add(evento4);
        arrayList_eventos.add(evento5);
        arrayList_eventos.add(evento6);
        arrayList_eventos.add(evento7);
        arrayList_eventos.add(evento8);
        arrayList_eventos.add(evento9);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("lista_eventos", arrayList_eventos);
        tabFragmentEvents.setArguments(bundle);
        tabFragmentMaps.setArguments(bundle);
        tabFragmentProfile.setArguments(bundle);
    }

    //endregion
}
