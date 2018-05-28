package com.example.paxi.aroundthedanceb.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.R;

public class DialogAdvanceSearch extends DialogFragment {

    Button btnSearch;
    Spinner spTipo, spEstilo, spCategoria;
    View view;
String tipo, estilo, categoria;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout. dialog_custom, null);
        declararElementos();
        builder.setView(view);



        return builder.create();
    }


    private void declararElementos(){
        spTipo = (Spinner) view.findViewById(R.id.spinner_busqueda_tipos_dialog);
        spEstilo = (Spinner) view.findViewById(R.id.spinner_busqueda_estilos_dialog);
        spCategoria = (Spinner) view.findViewById(R.id.spinner_busqueda_categorias_dialog);

    }

    private String getTipo (){
        return tipo;
    }


}