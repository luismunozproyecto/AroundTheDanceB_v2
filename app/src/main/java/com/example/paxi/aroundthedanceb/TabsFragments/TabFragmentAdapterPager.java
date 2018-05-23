package com.example.paxi.aroundthedanceb.TabsFragments;

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
                TabFragmentMaps tabFragmentMaps = new TabFragmentMaps();
                bundle = new Bundle();
                bundle.putParcelableArrayList("lista_eventos", lista_eventos);
                tabFragmentMaps.setArguments(bundle);

                return tabFragmentMaps;
            case 2:
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

        //Crear Eventos

        //endregion
    }
}
