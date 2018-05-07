package com.example.paxi.aroundthedanceb.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.Fragments.FragmentLoginCreateUser;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ActivityLogin extends AppCompatActivity
{
    //Mis Controles
    Button button_logIn, button_forgetPassword, button_newAccount;
    EditText txtPassword, txtEmail;

    //Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    //Variables
    String userId, userEmail;
    SharedPreferences sharedPref;

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null)
        {
            Intent i = new Intent(ActivityLogin.this, ActivityInicio.class);
            startActivity(i);
        }
        else
        {
            mAuth.addAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if(mAuthListener != null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
            mAuth.signOut();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylogin);

        //Mis Controles
        button_logIn          = (Button)findViewById(R.id.btn_login);
        button_forgetPassword = (Button)findViewById(R.id.btn_forget);
        button_newAccount     = (Button)findViewById(R.id.btn_newaccount);

        txtPassword = (EditText) findViewById(R.id.txt_password);
        txtEmail    = (EditText) findViewById(R.id.txt_email);

        txtEmail.setBackgroundResource(android.R.drawable.edit_text);
        txtPassword.setBackgroundResource(android.R.drawable.edit_text);

        button_logIn.setEnabled(false);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        //region YaLogeado
        sharedPref = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String userIdRecogida, userEmailRecogida;
        userIdRecogida = sharedPref.getString("key_id", "");
        userEmailRecogida = sharedPref.getString("key_email", "");

        if (!userIdRecogida.equals("") && !userEmailRecogida.equals(""))
        {
            startActivity(new Intent(ActivityLogin.this, ActivityInicio.class));
        }

        //endregion

        //region ListenerFirebaseAuth
        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                if(firebaseUser != null)
                {
                    startActivity(new Intent(ActivityLogin.this, ActivityInicio.class));

                    userId = firebaseUser.getUid();
                    userEmail = firebaseUser.getEmail();

                    sharedPref = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();

                    editor.putString("key_id", userId);
                    editor.putString("key_email", userEmail);

                    editor.commit();
                }
                else
                {
                    if(!txtPassword.getText().toString().equals("") && !txtEmail.getText().toString().equals(""))
                    {
                        Toast.makeText(ActivityLogin.this, "Your email or your password is wrong, please check again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        //endregion

        //region Buttons
        button_logIn.setOnClickListener(new View.OnClickListener() //Busqueda en base de datos
        {
            @Override
            public void onClick(View view)
            {
                if(txtEmail.getText().toString().equals("") &&  txtPassword.getText().toString().equals(""))
                {
                    txtEmail.setHintTextColor(Color.RED);
                    txtEmail.setBackgroundResource(R.drawable.edittext_border);

                    txtPassword.setHintTextColor(Color.RED);
                    txtPassword.setBackgroundResource(R.drawable.edittext_border);
                }
                else if(txtEmail.getText().toString().equals("") &&  !txtPassword.getText().toString().equals(""))
                {
                    txtEmail.setHintTextColor(Color.RED);
                    txtEmail.setBackgroundResource(R.drawable.edittext_border);
                }
                else if(!txtEmail.getText().toString().equals("") &&  txtPassword.getText().toString().equals(""))
                {
                    txtPassword.setHintTextColor(Color.RED);
                    txtPassword.setBackgroundResource(R.drawable.edittext_border);
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())
                            .addOnCompleteListener(ActivityLogin.this, new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if(!task.isSuccessful())
                                    {
                                        Toast.makeText(ActivityLogin.this, "Your email account or your password are invalid", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        button_forgetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Enviar a activity de olvidar contraseña
            }
        });

        //Crear Usuario
        button_newAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Enviar a activity de crear user
                startActivity(new Intent(ActivityLogin.this, FragmentLoginCreateUser.class));
            }
        });

        //endregion

        //region EditText

        txtEmail.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                //AQUI
                txtEmail.setHintTextColor(Color.GRAY);
                txtEmail.setBackgroundResource(android.R.drawable.edit_text);

                if(!txtPassword.getText().toString().equals("") && !txtEmail.getText().toString().equals(""))
                {
                    button_logIn.setEnabled(true);
                    button_logIn.setBackgroundResource(R.drawable.fondo_boton3);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        txtPassword.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                txtPassword.setHintTextColor(Color.GRAY);
                txtPassword.setBackgroundResource(android.R.drawable.edit_text);

                if(!txtPassword.getText().toString().equals("") && !txtEmail.getText().toString().equals(""))
                {
                    button_logIn.setEnabled(true);
                    button_logIn.setBackgroundResource(R.drawable.fondo_boton3);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        //endregion
    }
}


