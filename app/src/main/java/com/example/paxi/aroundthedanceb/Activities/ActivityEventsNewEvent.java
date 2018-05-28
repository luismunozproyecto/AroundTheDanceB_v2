package com.example.paxi.aroundthedanceb.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.paxi.aroundthedanceb.Maps.MapsAddLocationEvent;
import com.example.paxi.aroundthedanceb.Modelos.Estilo;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.Modelos.Tipo;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class ActivityEventsNewEvent extends AppCompatActivity
{
    //BDAntiguaBackgroundWorker BDAntiguaBackgroundWorker;


    //region static

    private static final long SPLASH_SCREEN_DELAY = 1000;
    private final int PICK_IMAGE_REQUEST = 71;
    private static final int GALLERY_INTENT = 2;
    private static final int DATE_ID = 0;

    //endregion

    //region Controles

    ImageView imagenEvent;
    Button btnImageEvent, btnUbicacion, btnAddType, btnAddStyle,btnAddCategory, btnCreateEvent;
    EditText txtNameEvent, txtDescription;
    TextView textViewFechaInicio, textViewFechaFinal, textViewHoraInicio;
    Spinner spinnerTypes, spinnerCategories, spinnerStyles;

    //endregion

    //region Variables
    DatabaseReference dbRef;
    ValueEventListener valueEventListener;


    FirebaseStorage storage;
    StorageReference storageReference;

    Calendar calendar = Calendar.getInstance();

    int fecha = 0;
    private int mYear, mMonth, mDay, Hora, Minutos;
    private int sYear, sMonth, sDay;

    private List<Tipo> arraylist_tipos = new ArrayList<>();
    private List<Estilo> arraylist_estilos = new ArrayList<>();
    private List<String> arraylist_categorias = new ArrayList<>();

    String string_type, string_categoria, string_estilo;

    Evento evento;
    Tipo tipo;
    Estilo estilo;
    Double latitud=0.0;
    Double longitud=0.0;

    private Uri filePath;

    String urlImagen;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_newevent);

        setupToolBar();

        //BDAntiguaBackgroundWorker = new BDAntiguaBackgroundWorker(this);

        //region RecogerLAT&LON

        Bundle b = getIntent().getExtras();

        if(b != null)
        {
              latitud = b.getDouble(MapsAddLocationEvent.EXTRA_LAT);
              longitud = b.getDouble(MapsAddLocationEvent.EXTRA_LONG);
            //llamar aqui al metodo que haga lo que yo quiera con lat y ong
        }

        //endregion

        //region Controles

        imagenEvent = (ImageView) findViewById(R.id.imagenevent);

        btnImageEvent   = (Button) findViewById(R.id.btn_imagenevent);
        btnUbicacion    = (Button) findViewById(R.id.btn_ubicacion);
        btnAddType      = (Button) findViewById(R.id.btn_addtype);
        btnAddStyle     = (Button) findViewById(R.id.btn_addstyle);
        btnAddCategory  = (Button) findViewById(R.id.btn_addcategory);
        btnCreateEvent  = (Button) findViewById(R.id.btn_createevent);

        txtNameEvent   = (EditText) findViewById(R.id.txt_nameevent);
        txtDescription = (EditText) findViewById(R.id.txt_description);

        textViewFechaInicio = (TextView) findViewById(R.id.textview_fechainicio);
        textViewFechaFinal  = (TextView) findViewById(R.id.textview_fechafinal);
        textViewHoraInicio  = (TextView) findViewById(R.id.textview_horainicio);
        //textViewHoraFinal   = (TextView) findViewById(R.id.textview_horafinal);

        spinnerTypes      = (Spinner) findViewById(R.id.spinner_types);
        spinnerStyles     = (Spinner) findViewById(R.id.spinner_styles);
        spinnerCategories = (Spinner) findViewById(R.id.spinner_categories);

        sDay = calendar.get(Calendar.DAY_OF_MONTH);
        sMonth = calendar.get(Calendar.MONTH);
        sYear = calendar.get(Calendar.YEAR);

        spinnerStyles.setVisibility(View.GONE);
        spinnerCategories.setVisibility(View.GONE);
        //btnAddType.setEnabled(Dis);
        btnAddType.setEnabled(false);
        btnAddStyle.setVisibility(View.GONE);
        btnAddCategory.setVisibility(View.GONE);

        //endregion

        //Firebase
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //region Spinners

        spinnerTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                string_type = spinnerTypes.getSelectedItem().toString();

                if(!string_type.equals(""))
                {
                    btnAddType.setEnabled(true);
                    btnAddType.setBackgroundResource(R.drawable.fondo_boton2);
                }

                if(string_type.equals(""))
                {
                    btnAddType.setEnabled(false);
                    btnAddType.setBackgroundResource(R.drawable.fondo_boton1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        spinnerStyles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                string_estilo = spinnerStyles.getSelectedItem().toString();

                if(!string_estilo.equals(""))
                {
                    btnAddStyle.setEnabled(true);
                    btnAddStyle.setBackgroundResource(R.drawable.fondo_boton2);
                }

                if(string_estilo.equals(""))
                {
                    btnAddStyle.setEnabled(false);
                    btnAddStyle.setBackgroundResource(R.drawable.fondo_boton1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });


        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                string_categoria = spinnerCategories.getSelectedItem().toString();

                if(!string_categoria.equals(""))
                {
                    btnAddCategory.setEnabled(true);
                    btnAddCategory.setBackgroundResource(R.drawable.fondo_boton2);
                }

                if(string_categoria.equals(""))
                {
                    btnAddCategory.setEnabled(false);
                    btnAddCategory.setBackgroundResource(R.drawable.fondo_boton1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        //endregion

        //region Buttons

        btnImageEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        btnUbicacion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ActivityEventsNewEvent.this, MapsAddLocationEvent.class);
                startActivity(intent);
            }
        });

        btnAddType.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                string_type = spinnerTypes.getSelectedItem().toString();

                tipo = new Tipo(string_type);  //Tipo con su nombre

                arraylist_tipos.add(tipo);

                spinnerStyles.setVisibility(View.VISIBLE);
                btnAddStyle.setVisibility(View.VISIBLE);
            }
        });

        btnAddStyle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                string_estilo = spinnerStyles.getSelectedItem().toString();

                estilo = new Estilo(string_estilo);

                arraylist_estilos.add(estilo);

                spinnerCategories.setVisibility(View.VISIBLE);
                btnAddCategory.setVisibility(View.VISIBLE);
            }
        });


        btnAddCategory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                string_categoria = spinnerCategories.getSelectedItem().toString();

                arraylist_categorias.add(string_categoria);
            }
        });

        btnCreateEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                 //uploadImage();
                if(arraylist_estilos.size()==0 || arraylist_tipos.size()==0){
                    Toast.makeText(getApplicationContext(),
                            "Debes de agregar Tipo, estilo y categoría",
                            Toast.LENGTH_LONG).show();

                }else{


                arraylist_estilos.get(arraylist_estilos.size() - 1).setCategorias(arraylist_categorias);
                arraylist_tipos.get(arraylist_tipos.size() - 1).setEstilos(arraylist_estilos);

                String nombre = txtNameEvent.getText().toString();
                String descripcion = txtDescription.getText().toString();
                String fechainicio = textViewFechaInicio.getText().toString();
                String horainicio = textViewHoraInicio.getText().toString();
                String fechafin = textViewFechaFinal.getText().toString();
                String ciudad = "Madrid";
                String pais = "España";


               evento = new Evento("", nombre,
                        descripcion,
                        fechainicio, horainicio, fechafin,
                        ciudad, pais, latitud, longitud,
                        arraylist_tipos,
                        "",
                        "paxi07");

              /*  evento = new Evento("evento12", "nombre",
                        "descripcion",
                        "02/02/2019", "09:30", "03/04/2019",
                        ciudad, pais, latitud, longitud,
                        arraylist_tipos,
                        "evento1.jpg",
                        "paxi07");*/
                dbRef = FirebaseDatabase.getInstance().getReference()
                        .child("eventos");
                String claveP3 = dbRef.push().getKey();

                evento.setId(claveP3);
                evento.setImagen(claveP3+".png");



               dbRef.child(evento.getId()).setValue(evento, new DatabaseReference.CompletionListener(){
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                        if(error == null) {
                            Toast.makeText(getApplicationContext(),
                                    "Evento guardado",
                                    Toast.LENGTH_LONG).show();

                            uploadImage();

                            Intent i=new Intent(getApplicationContext(), ActivityInicioTabsDown.class);
                            startActivity(i);

                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "No se ha podido crear el evento",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

                }
            }
        });

        //endregion
    }

    //region SubirImagen

    private void uploadImage()
    {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            urlImagen = evento.getImagen().toString();

            StorageReference ref = storageReference.child("eventos/"+urlImagen);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            progressDialog.dismiss();
                           //Toast.makeText(ActivityEventsNewEvent.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(ActivityEventsNewEvent.this, "Error al subir la imagen "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
                    {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());

                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    //endregion

    //region onActivityResult

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null )
        {
            filePath = data.getData();

            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imagenEvent.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


        if(requestCode == 10)
        {
            latitud = data.getDoubleExtra(MapsAddLocationEvent.EXTRA_LAT, 0);
            longitud = data.getDoubleExtra(MapsAddLocationEvent.EXTRA_LONG, 0);

            Toast.makeText(this, "Lat: " + latitud + ", long: " + longitud, Toast.LENGTH_SHORT).show();
        }
    }


    //endregion

    //region FechaHora

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2)
        {
            mYear = i;
            mMonth = i1;
            mDay = i2;

            if(fecha == 1)
            {
                textViewFechaInicio.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
            }
            else
            {
                textViewFechaFinal.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
            }
        }
    };

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_ID:
                return  new DatePickerDialog(this, onDateSetListener, sYear, sMonth, sDay);
        }
        return null;
    }

    public void onClickFechaInicio(View v)
    {
        showDialog(DATE_ID);
        fecha = 1;
    }

    public void onClickHoraInicio(View v)
    {
        final Calendar calendar = Calendar.getInstance();

        Hora = calendar.get(Calendar.HOUR);
        Minutos = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute)
            {
                textViewHoraInicio.setText(hour + ":" + minute);
            }

        }, Hora, Minutos, false);
        timePickerDialog.show();
    }

    public void onClickFechaFinal(View v)
    {
        showDialog(DATE_ID);
        fecha = 2;
    }

    public void onClickHoraFinal(View v)
    {
        final Calendar calendar = Calendar.getInstance();

        Hora = calendar.get(Calendar.HOUR);
        Minutos = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute)
            {
                //textViewHoraFinal.setText(hour + ":" + minute);
            }

        }, Hora, Minutos, false);
        timePickerDialog.show();
    }

    //endregion

    //region Toolbar

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