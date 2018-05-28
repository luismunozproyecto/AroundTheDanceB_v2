package com.example.paxi.aroundthedanceb.TabsFragments;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.Activities.ActivityEventsNewEvent;
import com.example.paxi.aroundthedanceb.Activities.ActivityEventsVerEvento;
import com.example.paxi.aroundthedanceb.Dialog.DialogAdvanceSearch;
import com.example.paxi.aroundthedanceb.Dialog.Dialog_AdvanceSearch;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.example.paxi.aroundthedanceb.Recycler.RecyclerAdaptador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;




public class TabFragmentEvents extends Fragment implements Dialog_AdvanceSearch.PasarArrayListFiltros
{
    public final static String EXTRA_VEREVENTO = "EVENTO_VER";



    //FIREBASE
    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public ValueEventListener valueEventListener;


    EditText txtSearch;
    Button btnNewEvent, btnAdvanceSearch;
    ArrayList<Evento> lista_eventos;
    FloatingActionButton floatingActionButton_addEvent;
    RecyclerView recyclerView;
    RecyclerAdaptador recyclerAdaptador;
    int pos;
    String name, fecha;

    boolean filtroAvanzados=false;
      String tipo="", estilo, categoria;
    ArrayList<String> filtros;

    ArrayList<Evento> lista_eventos_filtrados;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tab_events, container, false);

        txtSearch = (EditText) view.findViewById(R.id.txt_search);
        btnAdvanceSearch = (Button)  view.findViewById(R.id.btn_advancesearch);
        floatingActionButton_addEvent = view.findViewById(R.id.floatingbtn_addevent);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_events);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Bundle bundle = getArguments();

        if(bundle != null)
        {
            lista_eventos = bundle.getParcelableArrayList("lista_eventos");
        }

        cargarEventosFireBase();
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

                if(filtro.length() > 3)
                {
                    ArrayList<Evento> lef = filtrarEventoNombre(filtro);
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
               // new Dialog_AdvanceSearch(getContext(), TabFragmentEvents.this);
              /* FragmentManager fragmentManager = getActivity().getFragmentManager();
                DialogAdvanceSearch das = new DialogAdvanceSearch();
                das.show( fragmentManager,"tagDas");
*/
// get prompts.xml view
                LayoutInflater li = LayoutInflater.from(getContext());
                View promptsView = li.inflate(R.layout.dialog_custom, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getContext());




                final Spinner spTipo = (Spinner) promptsView.findViewById(R.id.spinner_busqueda_tipos_dialog);
                final Spinner  spEstilo = (Spinner) promptsView.findViewById(R.id.spinner_busqueda_estilos_dialog);
                final Spinner spCategoria = (Spinner) promptsView.findViewById(R.id.spinner_busqueda_categorias_dialog);
                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Buscar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        tipo = spTipo.getSelectedItem().toString();
                                        estilo =spEstilo.getSelectedItem().toString();
                                        categoria =spCategoria.getSelectedItem().toString();
                                        if(tipo.equals("")){
                                            Toast.makeText(getActivity(), "Debes de seleccionar a menos el tipo",Toast.LENGTH_LONG).show();
                                        }else{
                                            filtroAvanzados =true;

                                            cargarEventosFireBase();
                                        }

                                    }
                                })
                        .setNegativeButton("Cerrar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


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

    //region Recycler

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
                Intent intent = new Intent(getActivity(), ActivityEventsVerEvento.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_VEREVENTO, lista_eventos.get(pos));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(recyclerAdaptador);
    }

    private ArrayList<Evento> filtrarEventoNombre(String filtro)
    {
        ArrayList<Evento> lista_eventos_filtrados = new ArrayList<>();

        for (int i=0; i<lista_eventos.size(); i++)
        {
            if(lista_eventos.get(i).getNombre().toLowerCase().contains(filtro.toLowerCase()))
            {
                lista_eventos_filtrados.add(lista_eventos.get(i));
            }
        }

        return lista_eventos_filtrados;
    }

    //endregion

    @Override
    public void PasarFiltros(ArrayList<String> arraylist_filtros)
    {
        filtros = arraylist_filtros;
    }




     /*==================================

            METODOS DE FIREBASE

    ======================================
     */

    private void cargarEventosFireBase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("eventos");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               /* for (DataSnapshot dataSnapshotExamenes: dataSnapshot.getChildren()) {

                    Evento e = dataSnapshotExamenes.getValue(Evento.class);
                    lista_eventos.add(e);

                }
*/
                lista_eventos.clear();
                for (DataSnapshot dataSnapshotExamenes: dataSnapshot.getChildren()) {
                    cargarRecyclerViewEvento(dataSnapshotExamenes);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ActivityParte2","DATABASE ERROR");
            }
        };
        databaseReference.addValueEventListener(valueEventListener);


    }

    private ArrayList<Evento> eventosFiltrosAvanzados(ArrayList<Evento> lista_eventos){
        ArrayList<Evento> lsFiltrado=new ArrayList<>();
        for (int i=0; i<lista_eventos.size(); i++)
        {
            if(lista_eventos.get(i).getTipos().get(0).getNombre().equals(tipo)
                    || lista_eventos.get(i).getTipos().get(0).getEstilos().get(0).equals(estilo)
                        || lista_eventos.get(i).getTipos().get(0).getEstilos().get(0).equals(categoria))
            {
                lsFiltrado.add(lista_eventos.get(i));
            }
        }

        return lsFiltrado;
    }
    private void cargarRecyclerViewEvento(DataSnapshot dataSnapshot){

        lista_eventos.add(dataSnapshot.getValue(Evento.class));

        if(filtroAvanzados){
            lista_eventos = eventosFiltrosAvanzados(lista_eventos);
            mostrarRecycler(lista_eventos);

        }else{
            mostrarRecycler(lista_eventos);
        }



    }


    private Evento obtenerEvento(int position){
        return lista_eventos.get(position);
    }





}



