package com.example.paxi.aroundthedanceb.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.R;
import com.example.paxi.aroundthedanceb.Recycler.RecyclerAdaptador;

import java.util.ArrayList;

import Modelos.Evento;


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
        View view = inflater.inflate(R.layout.fragmenttabevents, container, false);

        txtSearch = (EditText) view.findViewById(R.id.txt_search);
        btnNewEvent = (Button)  view.findViewById(R.id.btn_newevent);
        btnAdvanceSearch = (Button)  view.findViewById(R.id.btn_advancesearch);
        floatingActionButton_addEvent = view.findViewById(R.id.floatingbtn_addevent);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_events);

        simularDatos();

        recyclerAdaptador = new RecyclerAdaptador(lista_eventos);

        recyclerView.setAdapter(recyclerAdaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        recyclerAdaptador.Click(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pos = recyclerView.getChildAdapterPosition(view);
                name = lista_eventos.get(pos).getNombre();
                fecha = lista_eventos.get(pos).getFechaIncio();
                Toast.makeText(getActivity(), name + "\n" + fecha, Toast.LENGTH_SHORT).show();
            }
        });

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

            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        //endregion

        //region Buttons
        btnNewEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), TabFragmentEvents_NewEvent.class);
                startActivity(intent);
            }
        });

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


    private void simularDatos()
    {
        lista_eventos.add(new Evento("1", "a", "f", "k", "11", "", "", "", 0, 0, "Name1", ""));
        lista_eventos.add(new Evento("2", "b", "g", "l", "90", "", "", "", 0, 0, "Name2", ""));
        lista_eventos.add(new Evento("3", "c", "h", "m", "34", "", "", "", 0, 0, "Name3", ""));
        lista_eventos.add(new Evento("4", "d", "i", "ñ", "23", "", "", "", 0, 0, "Name4", ""));
        lista_eventos.add(new Evento("5", "e", "j", "o", "15", "", "", "", 0, 0, "Name5", ""));
    }
}

