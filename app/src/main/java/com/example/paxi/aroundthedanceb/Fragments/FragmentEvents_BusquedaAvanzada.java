package com.example.paxi.aroundthedanceb.Fragments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.paxi.aroundthedanceb.Modelos.Estilo;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.Modelos.Tipo;
import com.example.paxi.aroundthedanceb.R;
import java.util.ArrayList;
import java.util.List;

public class FragmentEvents_BusquedaAvanzada extends AppCompatActivity
{
    Button btnAddTypeBusqueda, btnAddStyleBusqueda, btnAddCategoryBusqueda, btnBusqueda;
    Spinner spinnerTypesBusqueda, spinnerStylesBusqueda, spinnerCategoriesBusqueda;
    EditText txtCiudad, txtPais;

    String string_type, string_categoria, string_estilo;

    private List<Tipo> arraylist_tipos = new ArrayList<>();
    private List<Estilo> arraylist_estilos = new ArrayList<>();
    private List<String> arraylist_categorias = new ArrayList<>();

    Evento evento;
    Tipo tipo;
    Estilo estilo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_events_busquedapersonalizada);

        //region Controles

        btnAddTypeBusqueda = (Button) findViewById(R.id.btn_busqueda_addtype);
        btnAddStyleBusqueda = (Button) findViewById(R.id.btn_busqueda_addstyle);
        btnAddCategoryBusqueda = (Button) findViewById(R.id.btn_busqueda_addcategory);
        btnBusqueda = (Button) findViewById(R.id.btn_busqueda);

        spinnerTypesBusqueda = (Spinner) findViewById(R.id.spinner_busqueda_tipos);
        spinnerStylesBusqueda = (Spinner) findViewById(R.id.spinner_busqueda_estilos);
        spinnerCategoriesBusqueda = (Spinner) findViewById(R.id.spinner_busqueda_categorias);

        txtCiudad = (EditText) findViewById(R.id.txt_busqueda_ciudad);
        txtPais = (EditText) findViewById(R.id.txt_busqueda_pais);

        //endregion

        spinnerStylesBusqueda.setVisibility(View.GONE);
        spinnerCategoriesBusqueda.setVisibility(View.GONE);
        btnAddTypeBusqueda.setEnabled(false);
        btnAddStyleBusqueda.setVisibility(View.GONE);
        btnAddCategoryBusqueda.setVisibility(View.GONE);

        //region Spinners

        spinnerTypesBusqueda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                string_type = spinnerTypesBusqueda.getSelectedItem().toString();

                if(!string_type.equals(""))
                {
                    btnAddTypeBusqueda.setEnabled(true);
                    btnAddTypeBusqueda.setBackgroundResource(R.drawable.fondo_boton2);
                }

                if(string_type.equals(""))
                {
                    btnAddTypeBusqueda.setEnabled(false);
                    btnAddTypeBusqueda.setBackgroundResource(R.drawable.fondo_boton1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        spinnerStylesBusqueda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                string_estilo = spinnerStylesBusqueda.getSelectedItem().toString();

                if(!string_estilo.equals(""))
                {
                    btnAddStyleBusqueda.setEnabled(true);
                    btnAddStyleBusqueda.setBackgroundResource(R.drawable.fondo_boton2);
                }

                if(string_estilo.equals(""))
                {
                    btnAddStyleBusqueda.setEnabled(false);
                    btnAddStyleBusqueda.setBackgroundResource(R.drawable.fondo_boton1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });


        spinnerCategoriesBusqueda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                string_categoria = spinnerCategoriesBusqueda.getSelectedItem().toString();

                if(!string_categoria.equals(""))
                {
                    btnAddCategoryBusqueda.setEnabled(true);
                    btnAddCategoryBusqueda.setBackgroundResource(R.drawable.fondo_boton2);
                }

                if(string_categoria.equals(""))
                {
                    btnAddCategoryBusqueda.setEnabled(false);
                    btnAddCategoryBusqueda.setBackgroundResource(R.drawable.fondo_boton1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        //endregion

        //region Buttons

        btnAddTypeBusqueda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                string_type = spinnerTypesBusqueda.getSelectedItem().toString();

                tipo = new Tipo(string_type);

                arraylist_tipos.add(tipo);

                spinnerStylesBusqueda.setVisibility(View.VISIBLE);
                btnAddStyleBusqueda.setVisibility(View.VISIBLE);
            }
        });

        btnAddStyleBusqueda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                string_estilo = spinnerStylesBusqueda.getSelectedItem().toString();

                estilo = new Estilo(string_estilo);

                arraylist_estilos.add(estilo);

                spinnerCategoriesBusqueda.setVisibility(View.VISIBLE);
                btnAddCategoryBusqueda.setVisibility(View.VISIBLE);
            }
        });

        btnAddCategoryBusqueda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                string_categoria = spinnerCategoriesBusqueda.getSelectedItem().toString();

                arraylist_categorias.add(string_categoria);
            }
        });

        btnBusqueda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        //endregion

        setupToolBar();
    }

    //region ToolBar

    private void setupToolBar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar == null) return;

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();

        return false;
    }

    //endregion
}
