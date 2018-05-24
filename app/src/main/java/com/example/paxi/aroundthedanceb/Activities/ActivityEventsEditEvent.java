package com.example.paxi.aroundthedanceb.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paxi.aroundthedanceb.TabsFragments.TabFragmentEvents;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.example.paxi.aroundthedanceb.TabsFragments.TabFragmentProfile;

public class ActivityEventsEditEvent extends AppCompatActivity
{
    EditText nameEvent, dateEvent, descriptionEvent, locationEvent;

    Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_edit);

        //region Controles

        nameEvent = (EditText) findViewById(R.id.edit_event_namet);
        dateEvent = (EditText) findViewById(R.id.edit_event_date);
        descriptionEvent = (EditText) findViewById(R.id.edit_event_description);
        locationEvent = (EditText) findViewById(R.id.edit_event_location);

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
            evento = bundle.getParcelable(TabFragmentProfile.EXTRA_EDITAREVENTO);

            nameEvent.setText(evento.getNombre());
            dateEvent.setText(evento.getFechaInicio());
            descriptionEvent.setText(evento.getDescripcion());
            locationEvent.setText(evento.getPais() + ", " + evento.getCiudad());
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