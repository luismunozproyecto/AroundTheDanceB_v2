package com.example.paxi.aroundthedanceb.TabsFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paxi.aroundthedanceb.Activities.ActivityEventsEditEvent;
import com.example.paxi.aroundthedanceb.Activities.ActivityEventsVerEvento;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.example.paxi.aroundthedanceb.Recycler.RecyclerAdaptador;

import java.util.ArrayList;

import static com.example.paxi.aroundthedanceb.TabsFragments.TabFragmentEvents.EXTRA_VEREVENTO;

public class TabFragmentProfile extends Fragment
{
    ArrayList<Evento> lista_eventos;
    RecyclerView recyclerView;
    RecyclerAdaptador recyclerAdaptador;

    int pos;
    String name, fecha;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tab_profile, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_myevents);

        Bundle bundle = getArguments();

        if(bundle != null)
        {
            lista_eventos = bundle.getParcelableArrayList("lista_eventos");
        }


        mostrarRecycler(filtrarEventoUser());

        return view;
    }

    private ArrayList<Evento> filtrarEventoUser()
    {
        ArrayList<Evento> lista_eventos_filtrados = new ArrayList<>();

        for (int i=0; i<lista_eventos.size(); i++)
        {
            if(lista_eventos.get(i).getCreadoPor().equals("paxi07"))
            {
                lista_eventos_filtrados.add(lista_eventos.get(i));
            }
        }

        return lista_eventos_filtrados;
    }

    private void mostrarRecycler(ArrayList<Evento> lista_ev)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

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

                //Activity Ver Evento
                Intent intent = new Intent(getActivity(), ActivityEventsEditEvent.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_VEREVENTO, lista_eventos.get(pos));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(recyclerAdaptador);
    }
}
