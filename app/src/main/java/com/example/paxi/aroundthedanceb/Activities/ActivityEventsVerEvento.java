package com.example.paxi.aroundthedanceb.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.paxi.aroundthedanceb.TabsFragments.TabFragmentEvents;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Locale;

public class ActivityEventsVerEvento extends AppCompatActivity
{
    TextView nameEvent, dateEvent, descriptionEvent, locationEvent, typesEvent;
    ImageView imageViewEvent;
    StorageReference storageRf;

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
        storageRf  = FirebaseStorage.getInstance().getReference();


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
            storageRf.child("eventos/"+evento.getImagen()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(getApplicationContext()).load(uri.toString()).into(imageViewEvent);

                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
            nameEvent.setText(evento.getNombre());
            dateEvent.setText(evento.getFechaInicio());
            descriptionEvent.setText(evento.getDescripcion());
            locationEvent.setText(evento.getPais() + ", " + evento.getCiudad());

            if(evento.getTipos() != null)
            {
                for (int i = 0; i < evento.getTipos().size(); i++)
                {
                    if(evento.getTipos().get(i).getEstilos() != null)
                    {
                        for (int j = 0; j < evento.getTipos().get(i).getEstilos().size(); j++)
                        {
                            if(evento.getTipos().get(i).getEstilos().get(j).getCategorias() != null)
                            {
                                for (int k = 0; k < evento.getTipos().get(i).getEstilos().get(j).getCategorias().size(); k++)
                                {
                                    typesEvent.setText(
                                            ("Type: " + evento.getTipos().get(i).getNombre() + ", " + "\n\t\t" +
                                                    "Style: " + evento.getTipos().get(i).getEstilos().get(j).getNombre() + ", " + "\n\t\t\t" +
                                                    "Category: " + evento.getTipos().get(i).getEstilos().get(j).getCategorias().get(k) + ", ").toString());
                                }
                            }
                            else
                            {
                                typesEvent.setText(
                                        ("Type: " + evento.getTipos().get(i).getNombre() + ", " + "\n\t\t" +
                                                "Style: " + evento.getTipos().get(i).getEstilos().get(j).getNombre() + ", " + "\n\t\t\t").toString());
                            }
                        }
                    }
                    else
                    {
                        typesEvent.setText(("Type: " + evento.getTipos().get(i).getNombre() + ", " + "\n\t\t").toString());
                    }
                }
            }
        }
    }

    //endregion

    public void onClickLocation(View v)
    {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + evento.getLat() + "," + evento.getLon()));
        startActivity(intent);
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
