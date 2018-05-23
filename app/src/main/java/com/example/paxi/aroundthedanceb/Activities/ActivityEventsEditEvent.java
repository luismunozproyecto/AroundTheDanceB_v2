package com.example.paxi.aroundthedanceb.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paxi.aroundthedanceb.FragmentsTabs.TabFragmentEvents;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;

public class ActivityEventsEditEvent extends AppCompatActivity
{
    TextView nameEvent, dateEvent, descriptionEvent, locationEvent, typesEvent;
    ImageView imageViewEvent;

    Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_ver);

        //region Controles

        nameEvent = (TextView) findViewById(R.id.verevent_namet);
        dateEvent = (TextView) findViewById(R.id.verevent_date);
        descriptionEvent = (TextView) findViewById(R.id.verevent_description);
        locationEvent = (TextView) findViewById(R.id.verevent_location);
        typesEvent = (TextView) findViewById(R.id.verevent_types);

        imageViewEvent = (ImageView) findViewById(R.id.verevent_imagen);

        //endregion

        recogerExtras();

        setupToolBar();
    }

    //region Extras

    private void recogerExtras()
    {
        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            evento = bundle.getParcelable(TabFragmentEvents.EXTRA_VEREVENTO);

            nameEvent.setText(evento.getNombre());
            dateEvent.setText(evento.getFechaInicio());
            descriptionEvent.setText(evento.getDescripcion());
            locationEvent.setText(evento.getPais() + ", " + evento.getCiudad());

            for (int i = 0; i < evento.getTipos().size(); i++)
            {
                for (int j = 0; j < evento.getTipos().get(i).getEstilos().size(); j++)
                {
                    for (int k = 0; k < evento.getTipos().get(i).getEstilos().get(j).getCategorias().size(); k++)
                    {
                        typesEvent.setText
                                (
                                        ("Type: " + evento.getTipos().get(i).getNombre() + ", " + "\n\t\t" +
                                                "Style: " + evento.getTipos().get(i).getEstilos().get(j).getNombre() + ", " + "\n\t\t\t" +
                                                "Category: " + evento.getTipos().get(i).getEstilos().get(j).getCategorias().get(k) + ", ").toString()
                                );
                    }
                }
            }
        }
    }

    //endregion

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