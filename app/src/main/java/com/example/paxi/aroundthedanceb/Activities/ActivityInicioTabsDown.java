package com.example.paxi.aroundthedanceb.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.example.paxi.aroundthedanceb.FragmentsTabs.TabFragmentEvents;
import com.example.paxi.aroundthedanceb.FragmentsTabs.TabFragmentMaps;
import com.example.paxi.aroundthedanceb.FragmentsTabs.TabFragmentProfile;
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
        ArrayList<String> categorias1 = new ArrayList<String>();
        categorias1.add("1vs1");
        categorias1.add("3vs3");

        ArrayList<String> categorias2 = new ArrayList<String>();
        categorias1.add("5vs5");

        Estilo estilo1 = new Estilo("Locking", categorias1);

        Estilo estilo2 = new Estilo("Popping", categorias2);

        ArrayList<Estilo> estilos1 = new ArrayList<Estilo>();

        estilos1.add(estilo1);
        estilos1.add(estilo2);

        Tipo tipo1 = new Tipo("Battle", estilos1);

        ArrayList<Tipo> tipos1 = new ArrayList<Tipo>();

        tipos1.add(tipo1);

        Evento evento1 = new Evento("1", "Battle Alicante",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 0.0, 0.0,
                tipos1,
                "imagen.jpg",
                "paxi07");


        Evento evento2 = new Evento("1", "Battle Sevilla",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 0.0, 0.0,
                tipos1,
                "imagen.jpg",
                "paxi07");


        Evento evento3 = new Evento("1", "Feel The Dance",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 0.0, 0.0,
                tipos1,
                "imagen.jpg",
                "paxi07");

        Evento evento4 = new Evento("1", "Switch On",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 0.0, 0.0,
                tipos1,
                "imagen.jpg",
                "paxi07");


        Evento evento5 = new Evento("1", "Alicante Dance",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 0.0, 0.0,
                tipos1,
                "imagen.jpg",
                "paxi07");


        Evento evento6 = new Evento("1", "Sevilla Dance Center",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 0.0, 0.0,
                tipos1,
                "imagen.jpg",
                "paxi07");

        Evento evento7 = new Evento("1", "Battle Alicante Ciudad",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 0.0, 0.0,
                tipos1,
                "imagen.jpg",
                "paxi07");


        Evento evento8 = new Evento("1", "Jam Esta",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 0.0, 0.0,
                tipos1,
                "imagen.jpg",
                "paxi07");


        Evento evento9 = new Evento("1", "Battle 123",
                "EVENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "30-12-2018", "13:00", "31-12-2018",
                "Alicante", "España", 0.0, 0.0,
                tipos1,
                "imagen.jpg",
                "paxi07");

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
    }

    //endregion
}
