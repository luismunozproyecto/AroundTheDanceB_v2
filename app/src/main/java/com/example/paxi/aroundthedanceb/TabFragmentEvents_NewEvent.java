package com.example.paxi.aroundthedanceb;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;

//region Clases

class LocalTypes
{
    String type;
    ArrayList<LocalStyles> Styles;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalTypes(String type) {
        this.type = type;
        Styles = new ArrayList<LocalStyles>();
    }

    public LocalTypes()
    {

    }

    @Override
    public String toString()
    {
        return type;
    }
}

class LocalStyles
{
    String style;
    ArrayList<LocalCategories> categories;

    public LocalStyles(String style) {
        this.style = style;
        categories = new ArrayList<LocalCategories>();

    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString()
    {
        return style;
    }
}

class LocalCategories
{
    String categorie;

    public LocalCategories(String style) {
        this.categorie = style;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString()
    {
        return categorie;
    }
}

//endregion

public class TabFragmentEvents_NewEvent extends AppCompatActivity
{
    //region Variables

    ImageView imagenEvent;
    Button btnImageEvent, btnUbicacion, btnAddType, btnAddStyle,btnAddCategory, btnCreateEvent;
    EditText txtNameEvent, txtDescription;
    TextView textViewFechaInicio, textViewFechaFinal, textViewHoraInicio, textViewHoraFinal;
    Spinner spinnerTypes, spinnerCategories, spinnerStyles;

    Calendar calendar = Calendar.getInstance();
    int fecha = 0;
    static final int DATE_ID = 0;
    private int mYear, mMonth, mDay, Hora, Minutos;
    private int sYear, sMonth, sDay;

    //BDAntiguaBackgroundWorker BDAntiguaBackgroundWorker;

    String type, category, style;
    private static final int GALLERY_INTENT = 2;
    ArrayList<LocalTypes> types = new ArrayList<LocalTypes>();
    SharedPreferences sharedPref;

    StorageReference storageRef;



    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmenteventsnewevent);

        setupToolBar();

        //BDAntiguaBackgroundWorker = new BDAntiguaBackgroundWorker(this);

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
        textViewHoraFinal   = (TextView) findViewById(R.id.textview_horafinal);

        spinnerTypes      = (Spinner) findViewById(R.id.spinner_types);
        spinnerStyles     = (Spinner) findViewById(R.id.spinner_styles);
        spinnerCategories = (Spinner) findViewById(R.id.spinner_categories);

        sDay = calendar.get(Calendar.DAY_OF_MONTH);
        sMonth = calendar.get(Calendar.MONTH);
        sYear = calendar.get(Calendar.YEAR);
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
                Intent intent = new Intent(TabFragmentEvents_NewEvent.this, MapsAddLocationEvent.class);
                startActivity(intent);
            }
        });

        btnAddType.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                type = spinnerTypes.getSelectedItem().toString();

                LocalTypes tipo = new LocalTypes(type);

                types.add(tipo);

            }
        });

        btnAddStyle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                style = spinnerStyles.getSelectedItem().toString();

                LocalStyles estilo = new  LocalStyles(style);

                types.get(types.size() - 1).Styles.add(estilo);
            }
        });


        btnAddCategory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                category = spinnerCategories.getSelectedItem().toString();

                LocalCategories categorie = new LocalCategories(category);

                types.get(types.size() - 1).Styles.get(types.get(types.size() - 1).Styles.size() - 1).categories.add(categorie);
            }
        });

        btnCreateEvent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

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

                //BDAntiguaBackgroundWorker.execute("login", "aqui", "aqui");
            }
        });

        //endregion

        //region Spinners

        spinnerTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                // your code here
                type = spinnerTypes.getSelectedItem().toString();

                if(!type.equals(""))
                {
                    if(type.equals("Battle"))
                    {
                        spinnerCategories.setVisibility(View.VISIBLE);
                        spinnerStyles.setVisibility(View.VISIBLE);
                    }
                    else if(type.equals("Choreo Championship"))
                    {
                        spinnerCategories.setVisibility(View.VISIBLE);
                        spinnerStyles.setVisibility(View.VISIBLE);
                    }
                    else if(type.equals("Jam"))
                    {
                        spinnerStyles.setVisibility(View.VISIBLE);
                        spinnerCategories.setVisibility(View.INVISIBLE);
                    }
                    else if(type.equals("Workshop"))
                    {
                        spinnerStyles.setVisibility(View.VISIBLE);
                        spinnerCategories.setVisibility(View.INVISIBLE);
                    }
                    else if(type.equals("Showcase"))
                    {
                        //spinnerCategories.setVisibility(View.VISIBLE);
                        spinnerStyles.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                // your code here
            }
        });

        //endregion
    }

    //region Storage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        storageRef = FirebaseStorage.getInstance().getReference();

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK)
        {
            Uri uri = data.getData();
            StorageReference filepath = storageRef.child("Photos").child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    Toast.makeText(TabFragmentEvents_NewEvent.this, "Upload Done", Toast.LENGTH_SHORT).show();
                }
            });
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
                textViewHoraFinal.setText(hour + ":" + minute);
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

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();

        return false;
    }

    //endregion
}