package com.example.paxi.aroundthedanceb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import Modelos.Evento;


public class TabFragmentEvents extends Fragment
{
    EditText txtSearch;
    Button btnNewEvent, btnAdvanceSearch;
    ArrayList<Evento> lista_eventos=new ArrayList<>();
    FloatingActionButton floatingActionButton_addEvent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        simularDatos();
        View view = inflater.inflate(R.layout.fragmenttabevents, container, false);

        txtSearch = (EditText) view.findViewById(R.id.txt_search);
        btnNewEvent = (Button)  view.findViewById(R.id.btn_newevent);
        btnAdvanceSearch = (Button)  view.findViewById(R.id.btn_advancesearch);
        floatingActionButton_addEvent = view.findViewById(R.id.floatingbtn_addevent);

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

        btnNewEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

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
                Intent intent = new Intent(getActivity(), NewEvent.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void simularDatos()
    {
        lista_eventos.add(new Evento());
        lista_eventos.add(new Evento());
        lista_eventos.add(new Evento());
        lista_eventos.add(new Evento());
        lista_eventos.add(new Evento());
    }
}

