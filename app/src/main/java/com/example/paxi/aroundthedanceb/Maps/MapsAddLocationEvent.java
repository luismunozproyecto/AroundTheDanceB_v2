package com.example.paxi.aroundthedanceb.Maps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.Activities.ActivityEventsNewEvent;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsAddLocationEvent extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener
{
    public final static String EXTRA_LAT = "Lat";
    public final static String EXTRA_LONG = "Long";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private static final LatLngBounds BOUNDS = new LatLngBounds(new LatLng(-40, -168), new LatLng(71, 136));

    private AutoCompleteTextView txt_Search;

    private Boolean permisoLocation = false;
    private GoogleMap googleMapa;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private MapsAutocompleteAdapter mMapsPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;

    private Double latitud, longitud;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_events_newevent_addmap);
        txt_Search = (AutoCompleteTextView) findViewById(R.id.auto_search);

        getPermisosLocation();

        //region PETA

        /*googleMapa.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng point)
            {
                googleMapa.addMarker(new MarkerOptions().position(point).title("You are here"));
            }
        });*/

        //endregion
    }

    //region Inicializar

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        googleMapa = googleMap;

        if (permisoLocation)
        {
            //getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }

            inicializar();
        }
    }

    private void inicialiazarMapa()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsAddLocationEvent.this);
    }


    private void inicializar()
    {
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        txt_Search.setOnItemClickListener(mAutocompleteClickListener);

        mMapsPlaceAutocompleteAdapter = new MapsAutocompleteAdapter(this, mGoogleApiClient, BOUNDS, null);

        txt_Search.setAdapter(mMapsPlaceAutocompleteAdapter);

        txt_Search.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent)
            {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER)
                {
                    geoSearch();
                }

                return false;
            }
        });

        FideSoftKeboard();
    }

    //endregion

    //region Permisos

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        permisoLocation = false;

        switch(requestCode)
        {
            case LOCATION_PERMISSION_REQUEST_CODE:
            {
                if(grantResults.length > 0)
                {
                    for(int i = 0; i < grantResults.length; i++)
                    {
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED)
                        {
                            permisoLocation = false;
                            return;
                        }
                    }

                    permisoLocation = true;

                    inicialiazarMapa();
                }
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }

    private void getPermisosLocation()
    {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                permisoLocation = true;
                inicialiazarMapa();
            }
            else
            {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        }
        else
        {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    //endregion

    //region DeviceLocation
    private void getDeviceLocation()
    {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try
        {
            if(permisoLocation)
            {
                final Task location = fusedLocationProviderClient.getLastLocation();

                location.addOnCompleteListener(new OnCompleteListener()
                {
                    @Override
                    public void onComplete(@NonNull Task task)
                    {
                        if(task.isSuccessful())
                        {
                            Location currentLocation = (Location) task.getResult();

                            moverCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 10f, "AQUI");
                        }
                        else
                        {
                            Toast.makeText(MapsAddLocationEvent.this, R.string.unablelocation, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
        catch (SecurityException e)
        {

        }
    }

    //endregion

    //region Mover Camara
    private void moverCamera(LatLng latLng, float zoom, String tittle)
    {
        googleMapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if(!tittle.equals("My Location"))
        {
            MarkerOptions options = new MarkerOptions().position(latLng).title(tittle);
            googleMapa.addMarker(options);
        }

        FideSoftKeboard();
    }

    //endregion

    //region Teclado

    private void FideSoftKeboard()
    {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void hideKeyboard(Activity activity)
    {
        View view = activity.findViewById(android.R.id.content);

        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //endregion

    //region Autocomplete
    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
        {
            FideSoftKeboard();

            final AutocompletePrediction item = mMapsPlaceAutocompleteAdapter.getItem(i);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    //endregion

    //region Busquedas

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>()
    {
        @Override
        public void onResult(@NonNull PlaceBuffer places)
        {
            if(!places.getStatus().isSuccess())
            {
                places.release();
                return;
            }

            final Place place = places.get(0);

            moverCamera(new LatLng(place.getViewport().getCenter().latitude, place.getViewport().getCenter().longitude), DEFAULT_ZOOM, "");

            latitud = place.getViewport().getCenter().latitude;

            longitud = place.getViewport().getCenter().longitude;

            Confirmar();

            places.release();
        }
    };

    private void geoSearch() //Le da click a la lupa
    {
        String searchString = txt_Search.getText().toString();

        Geocoder geocoder = new Geocoder(MapsAddLocationEvent.this);
        List<Address> list = new ArrayList<>();

        try
        {
            list = geocoder.getFromLocationName(searchString, 1);
        }
        catch (IOException e)
        {

        }

        if(list.size() > 0)
        {
            Address address = list.get(0);

            moverCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));

            Confirmar();
        }
    }

    //endregion

    //region ConfirmarLocation

    public void Confirmar()
    {
        hideKeyboard(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                DialogoConfirmar();
            }
        }, 1000);
    }

    public void DialogoConfirmar()
    {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case DialogInterface.BUTTON_POSITIVE:

                        Intent intent = new Intent(MapsAddLocationEvent.this, ActivityEventsNewEvent.class);
                        intent.putExtra(EXTRA_LAT, latitud);
                        intent.putExtra(EXTRA_LONG, longitud);
                        setResult(10, intent);
                        finish();

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Nada
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.eventConfirmation).setPositiveButton(R.string.yes, dialogClickListener).setNegativeButton(R.string.no, dialogClickListener).show();
    }

    //endregion
}
