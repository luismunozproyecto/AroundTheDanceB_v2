package com.example.paxi.aroundthedanceb.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.paxi.aroundthedanceb.R;

import java.util.ArrayList;

public class Dialog_AdvanceSearch
{
    public interface PasarArrayListFiltros
    {
        void PasarFiltros(ArrayList<String> arraylist_filtros);
    }

    private PasarArrayListFiltros pasarArrayListFiltros;

    private ArrayList<String> arraylist_filtros = new ArrayList<>();
    String string_type, string_categoria, string_estilo, string;

    public Dialog_AdvanceSearch(Context context, PasarArrayListFiltros actividad)
    {
        pasarArrayListFiltros = actividad;

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_busquedapersonalizada);

        final Button btnAddTypeBusqueda, btnAddStyleBusqueda, btnAddCategoryBusqueda, btnBusqueda;
        final Spinner spinnerTypesBusqueda, spinnerStylesBusqueda, spinnerCategoriesBusqueda;

        btnAddTypeBusqueda = (Button) dialog.findViewById(R.id.btn_busqueda_addtype);
        btnAddStyleBusqueda = (Button) dialog.findViewById(R.id.btn_busqueda_addstyle);
        btnAddCategoryBusqueda = (Button) dialog.findViewById(R.id.btn_busqueda_addcategory);
        btnBusqueda = (Button) dialog.findViewById(R.id.btn_busqueda);

        spinnerTypesBusqueda = (Spinner) dialog.findViewById(R.id.spinner_busqueda_tipos);
        spinnerStylesBusqueda = (Spinner) dialog.findViewById(R.id.spinner_busqueda_estilos);
        spinnerCategoriesBusqueda = (Spinner) dialog.findViewById(R.id.spinner_busqueda_categorias);


        btnAddTypeBusqueda.setEnabled(false);

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
                string = spinnerTypesBusqueda.getSelectedItem().toString();

                arraylist_filtros.add(string);
            }
        });

        btnAddStyleBusqueda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                string = spinnerStylesBusqueda.getSelectedItem().toString();

                arraylist_filtros.add(string);
            }
        });

        btnAddCategoryBusqueda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                string = spinnerCategoriesBusqueda.getSelectedItem().toString();

                arraylist_filtros.add(string);
            }
        });

        btnBusqueda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pasarArrayListFiltros.PasarFiltros(arraylist_filtros);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
