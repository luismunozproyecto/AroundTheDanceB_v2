package com.example.paxi.aroundthedanceb.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActivityEventsNewEvent extends AppCompatActivity
{
    //BDAntiguaBackgroundWorker BDAntiguaBackgroundWorker;

    //region static

    private static final long SPLASH_SCREEN_DELAY = 1000;
    private static final int GALLERY_INTENT = 2;
    private static final int DATE_ID = 0;

    //endregion

    SharedPreferences sharedPref;

    StorageReference storageRef;

    //region Controles

    ImageView imagenEvent;
    Button btnImageEvent, btnUbicacion, btnAddType, btnAddStyle,btnAddCategory, btnCreateEvent;
    EditText txtNameEvent, txtDescription;
    TextView textViewFechaInicio, textViewFechaFinal, textViewHoraInicio;
    Spinner spinnerTypes, spinnerCategories, spinnerStyles;

    //endregion

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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_events_newevent);

        setupToolBar();

        //BDAntiguaBackgroundWorker = new BDAntiguaBackgroundWorker(this);
        Bundle b = getIntent().getExtras();
        if(b!=null){
              latitud = b.getDouble(MapsAddLocationEvent.EXTRA_LAT);
              longitud = b.getDouble(MapsAddLocationEvent.EXTRA_LONG);
            //llamar aqui al metodo que haga lo que yo quiera con lat y ong
        }
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

        //endregion

        spinnerStyles.setVisibility(View.GONE);
        spinnerCategories.setVisibility(View.GONE);
        //btnAddType.setEnabled(Dis);
        btnAddType.setEnabled(false);

        btnAddStyle.setVisibility(View.GONE);
        btnAddCategory.setVisibility(View.GONE);

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
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType("image/*");

                startActivityForResult(intent, GALLERY_INTENT);
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
                //region BDAntigua

                /*String server_url = "http://192.168.129.2/insert_event.php";
                sharedPref = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewEvent.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        alertDialog.setTitle("Server Response");
                        alertDialog.setMessage("Response: " + response);
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        });

                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();

                    }
                },new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(NewEvent.this, "Error", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError
                        {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("name", txtNameEvent.getText().toString());
                            params.put("description", txtDescription.getText().toString());
                            return super.getParams();
                        }
                    };

                MySingleton.getInstance(NewEvent.this).addTorequestque(stringRequest);*/


                /*BDAntiguaBackgroundWorker.execute
                (
                        "insertevent",
                        txtNameEvent.getText().toString(),
                        textViewFechaInicio.getText().toString() + " " + textViewHoraInicio.getText().toString(),
                        textViewFechaFinal.getText().toString() + " " + textViewHoraFinal.getText().toString(),
                        txtDescription.getText().toString(),
                        "este"
                );*/

                //BDAntiguaBackgroundWorker.execute("activity_login", "aqui", "aqui");

                //endregion

                arraylist_estilos.get(arraylist_estilos.size() - 1).setCategorias(arraylist_categorias);

                arraylist_tipos.get(arraylist_tipos.size() - 1).setEstilos(arraylist_estilos);

                evento = new Evento("1", "Battle Alicante",
                        "HASGBHDASBDHASVBDHASDASBDBVASDBGHASDG",
                        "20/05/2018", "17:00", "24/05/2018",
                        "Alicante", "España", 0.0, 0.0,
                        arraylist_tipos,
                        "imagen.jpg",
                        "paxi07");


            }
        });

        //endregion
    }

    //region onActivityResult

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

         /*storageRef = FirebaseStorage.getInstance().getReference();

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK)
        {
            Uri uri = data.getData();
            StorageReference filepath = storageRef.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    Toast.makeText(ActivityEventsNewEvent.this, "Upload Done", Toast.LENGTH_SHORT).show();
                }
            });
        }*/

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

    //Toolbar

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