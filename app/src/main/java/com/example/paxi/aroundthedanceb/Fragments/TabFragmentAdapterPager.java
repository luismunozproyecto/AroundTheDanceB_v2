package com.example.paxi.aroundthedanceb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.paxi.aroundthedanceb.Modelos.Estilo;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.Modelos.Tipo;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAdapterPager extends FragmentStatePagerAdapter
{
    int NumOfTabs;
    ArrayList<Evento> lista_eventos = new ArrayList<>();
    Bundle bundle;

    public TabFragmentAdapterPager(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.NumOfTabs = NumOfTabs;
        simularDatos();
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                TabFragmentEvents tabFragmentEvents = new TabFragmentEvents();
                bundle = new Bundle();
                bundle.putParcelableArrayList("lista_eventos", lista_eventos);
                tabFragmentEvents.setArguments(bundle);

                return tabFragmentEvents;
            case 1:
                TabFragmentCalendar tabFragmentCalendar = new TabFragmentCalendar();
                return tabFragmentCalendar;
            case 2:
                TabFragmentMaps tabFragmentMaps = new TabFragmentMaps();
                bundle = new Bundle();
                bundle.putParcelableArrayList("lista_eventos", lista_eventos);
                tabFragmentMaps.setArguments(bundle);

                return tabFragmentMaps;
            case 3:
                TabFragmentProfile tabFragmentProfile = new TabFragmentProfile();
                return tabFragmentProfile;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return NumOfTabs;
    }

    private void simularDatos()
    {
        //region ObjetosVirtuales

        List<String> categorias1 = new ArrayList<>();
        categorias1.add("1vs1");
        categorias1.add("3vs3");
        categorias1.add("4vs4");
        categorias1.add("2vs2");

        List<String> categorias2 = new ArrayList<>();
        categorias1.add("1vs1");
        categorias1.add("3vs3");

        Estilo estiloPrueba1 = new Estilo("Hip hop", categorias1);
        Estilo estiloPrueba2 = new Estilo("Locking", categorias2);

        List<Estilo> estilos = new ArrayList<>();
        estilos.add(estiloPrueba1);
        estilos.add(estiloPrueba2);

        Tipo tipo1 = new Tipo("Battle", estilos);

        List<Tipo> tipos = new ArrayList<>();
        tipos.add(tipo1);


        Evento eventoPrueba = new Evento
                ("Alicante",
                        "Pablo",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        "30-12-2018",
                        "29-12-2018",
                        "20:00",
                        "23",
                        "imagen.jpg",
                        38.3398819,
                        -0.4934223,
                        "Battle Alicante",
                        "España",
                        tipos);


        Evento eventoPrueba2 = new Evento
                ("Alicante",
                        "Pablo",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        "30-12-2018",
                        "29-12-2018",
                        "20:00",
                        "23",
                        "imagen.jpg",
                        37.3754338,
                        -5.9900777,
                        "Aqui Sevilla",
                        "España",
                        tipos);

        Evento eventoPrueba3 = new Evento
                ("Alicante",
                        "Pablo",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        "30-12-2018",
                        "29-12-2018",
                        "20:00",
                        "23",
                        "imagen.jpg",
                        37.397671,
                        -6.0004626,
                        "Sevilla Dance",
                        "España",
                        tipos);

        Evento eventoPrueba4 = new Evento
                ("Alicante",
                        "Pablo",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        "30-12-2018",
                        "29-12-2018",
                        "20:00",
                        "23",
                        "imagen.jpg",
                        38.3579029,
                        -0.5075435,
                        "Aqui Battle",
                        "España",
                        tipos);

        Evento eventoPrueba5 = new Evento
                ("Alicante",
                        "Pablo",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        "30-12-2018",
                        "29-12-2018",
                        "20:00",
                        "23",
                        "imagen.jpg",
                        38.5374398,
                        -0.1475051,
                        "Just For Dance",
                        "España",
                        tipos);

        Evento eventoPrueba6 = new Evento
                ("Alicante",
                        "Pablo",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        "30-12-2018",
                        "29-12-2018",
                        "20:00",
                        "23",
                        "imagen.jpg",
                        41.7033604,
                        -4.8788518,
                        "Faro Urbano",
                        "España",
                        tipos);

        Evento eventoPrueba7 = new Evento
                ("Alicante",
                        "Pablo",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                        "30-12-2018",
                        "29-12-2018",
                        "20:00",
                        "23",
                        "imagen.jpg",
                        38.3900032,
                        -0.5143003,
                        "Terreta Urbana",
                        "España",
                        tipos);

        //endregion

        lista_eventos.add(eventoPrueba);
        lista_eventos.add(eventoPrueba2);
        lista_eventos.add(eventoPrueba3);
        lista_eventos.add(eventoPrueba4);
        lista_eventos.add(eventoPrueba5);
        lista_eventos.add(eventoPrueba6);
        lista_eventos.add(eventoPrueba7);
    }
}
