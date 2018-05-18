package com.example.paxi.aroundthedanceb.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.Modelos.Estilo;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.Modelos.Tipo;
import com.example.paxi.aroundthedanceb.R;
import com.example.paxi.aroundthedanceb.Recycler.RecyclerAdaptador;

import java.util.ArrayList;
import java.util.List;


public class TabFragmentEvents extends Fragment
{
    EditText txtSearch;
    Button btnNewEvent, btnAdvanceSearch;
    ArrayList<Evento> lista_eventos = new ArrayList<>();
    FloatingActionButton floatingActionButton_addEvent;
    RecyclerView recyclerView;
    RecyclerAdaptador recyclerAdaptador;
    int pos;
    String name, fecha;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tab_events, container, false);

        txtSearch = (EditText) view.findViewById(R.id.txt_search);
        btnAdvanceSearch = (Button)  view.findViewById(R.id.btn_advancesearch);
        floatingActionButton_addEvent = view.findViewById(R.id.floatingbtn_addevent);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_events);

        simularDatos();
        //mostrarRecycler(lista_eventos);

        //region Search
        txtSearch.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String filtro = charSequence.toString();

                if(filtro.length()>3)
                {
                    ArrayList<Evento> lef = filtrarEvento(filtro);
                    mostrarRecycler(lef);
                }
                else if(filtro.length() == 0)
                {
                    mostrarRecycler(new ArrayList<Evento>());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        //endregion

        //region Buttons
        btnAdvanceSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        floatingActionButton_addEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), TabFragmentEvents_NewEvent.class);
                startActivity(intent);
            }
        });
        //endregion

        return view;
    }
    private ArrayList<Evento> filtrarEvento(String filtro)
    {
        ArrayList<Evento> lista_eventos_filtrados = new ArrayList<>();

        for (int i=0; i<lista_eventos.size(); i++)
        {
            if(lista_eventos.get(i).getNombre().contains(filtro))
            {
                lista_eventos_filtrados.add(lista_eventos.get(i));
            }
        }

        return lista_eventos_filtrados;
    }

    private void mostrarRecycler(ArrayList<Evento> lista_ev)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false).getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerAdaptador = new RecyclerAdaptador(lista_ev);

        recyclerAdaptador.Click(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pos = recyclerView.getChildAdapterPosition(view);
                name = lista_eventos.get(pos).getNombre();
                fecha = lista_eventos.get(pos).getFechaInicio();
                Toast.makeText(getActivity(), name + "\n" + fecha, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(recyclerAdaptador);
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

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("arraylist", lista_eventos);
        TabFragmentMaps tabFragmentMaps = new TabFragmentMaps();
        tabFragmentMaps.setArguments(bundle);
    }
}

