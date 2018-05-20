package com.example.paxi.aroundthedanceb.FragmentsTabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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

import com.example.paxi.aroundthedanceb.Activities.ActivtyEventsBusquedaAvanzada;
import com.example.paxi.aroundthedanceb.Activities.ActivityEventsNewEvent;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.example.paxi.aroundthedanceb.Recycler.RecyclerAdaptador;

import java.util.ArrayList;


public class TabFragmentEvents extends Fragment
{
    EditText txtSearch;
    Button btnNewEvent, btnAdvanceSearch;
    ArrayList<Evento> lista_eventos;
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

        //simularDatos();
        //mostrarRecycler(lista_eventos);

        Bundle bundle = getArguments();

        if(bundle != null)
        {
            lista_eventos = bundle.getParcelableArrayList("lista_eventos");
        }

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
                Intent intent = new Intent(getActivity(), ActivtyEventsBusquedaAvanzada.class);
                startActivity(intent);
            }
        });

        floatingActionButton_addEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), ActivityEventsNewEvent.class);
                startActivity(intent);
            }
        });
        //endregion

        return view;
    }

    //region Busqueda

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

    //endregion

    //region Recycler

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

    //endregion

}

