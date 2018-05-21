package com.example.paxi.aroundthedanceb.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.Maps.MapsAddLocationEvent;
import com.example.paxi.aroundthedanceb.Modelos.Estilo;
import com.example.paxi.aroundthedanceb.Modelos.Evento;
import com.example.paxi.aroundthedanceb.Modelos.Tipo;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class ActivityLoginForgetPassword extends AppCompatActivity
{
    EditText txtEmail;
    Button btnEnviar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forgotpassword);

        setupToolBar();

        mAuth = FirebaseAuth.getInstance();

        txtEmail = (EditText) findViewById(R.id.txt_email_resetpassword);
        btnEnviar = (Button) findViewById(R.id.btn_send);

        btnEnviar.setEnabled(false);

        txtEmail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                txtEmail.setHintTextColor(Color.GRAY);
                txtEmail.setBackgroundResource(android.R.drawable.edit_text);

                if(!txtEmail.getText().toString().equals(""))
                {
                    btnEnviar.setEnabled(true);
                    btnEnviar.setBackgroundResource(R.drawable.fondo_boton3);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mAuth.getInstance().sendPasswordResetEmail(txtEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(ActivityLoginForgetPassword.this, R.string.check, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }

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
