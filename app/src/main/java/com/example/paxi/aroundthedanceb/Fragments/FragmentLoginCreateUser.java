package com.example.paxi.aroundthedanceb.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paxi.aroundthedanceb.Activities.ActivityInicio;
import com.example.paxi.aroundthedanceb.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentLoginCreateUser extends FragmentActivity
{
    Button buttonNextStep;
    int contador = 0, cont = 0;

    EditText txtNickName, txtEmail, txtPassword, txtPasswordRepeat;
    TextView tittle;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_newuser);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        //region Controles
        buttonNextStep    = (Button)   findViewById(R.id.botonnext);
        txtNickName       = (EditText) findViewById(R.id.txt_nickname);
        txtEmail          = (EditText) findViewById(R.id.txt_email_fragment);
        txtPassword       = (EditText) findViewById(R.id.txt_password_fragment);
        txtPasswordRepeat = (EditText) findViewById(R.id.txt_repeatpassword_fragment);
        tittle            = (TextView) findViewById(R.id.txt_tittle);

        txtNickName.setBackgroundResource(android.R.drawable.edit_text);
        txtEmail.setBackgroundResource(android.R.drawable.edit_text);
        txtPassword.setBackgroundResource(android.R.drawable.edit_text);
        txtPasswordRepeat.setBackgroundResource(android.R.drawable.edit_text);

        buttonNextStep.setEnabled(false);

        //endregion

        //region EditTexts
        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                if (txtNickName.getText().toString().equals(""))
                {
                    txtNickName.setHintTextColor(Color.RED);
                    txtNickName.setBackgroundResource(R.drawable.edittext_border);


                    Toast.makeText(FragmentLoginCreateUser.this, "NickName cant be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                if (!txtEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
                {
                    txtEmail.setHintTextColor(Color.RED);
                    txtEmail.setBackgroundResource(R.drawable.edittext_border);


                    Toast.makeText(FragmentLoginCreateUser.this, "This email is invalid", Toast.LENGTH_SHORT).show();
                }

                if (txtNickName.getText().toString().equals(""))
                {
                    txtNickName.setHintTextColor(Color.RED);
                    txtNickName.setBackgroundResource(R.drawable.edittext_border);


                    Toast.makeText(FragmentLoginCreateUser.this, "NickName cant be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtPasswordRepeat.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View view, boolean b)
            {
                if (!txtEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
                {
                    txtEmail.setHintTextColor(Color.RED);
                    txtEmail.setBackgroundResource(R.drawable.edittext_border);

                    Toast.makeText(FragmentLoginCreateUser.this, "This email is invalid", Toast.LENGTH_SHORT).show();
                }

                if (txtNickName.getText().toString().equals(""))
                {
                    txtNickName.setHintTextColor(Color.RED);
                    txtNickName.setBackgroundResource(R.drawable.edittext_border);


                    Toast.makeText(FragmentLoginCreateUser.this, "NickName cant be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtEmail.addTextChangedListener(new TextWatcher()
        {
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                txtEmail.setHintTextColor(Color.GRAY);
                txtEmail.setBackgroundResource(android.R.drawable.edit_text);

                if(!txtPassword.getText().toString().equals("") && !txtPasswordRepeat.getText().toString().equals(""))
                {
                    buttonNextStep.setEnabled(true);
                    buttonNextStep.setBackgroundResource(R.drawable.fondo_boton3);
                }
            }
            public void afterTextChanged(Editable s)
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

                if(!txtPasswordRepeat.getText().toString().equals("") && !txtEmail.getText().toString().equals(""))
                {
                    buttonNextStep.setEnabled(true);
                    buttonNextStep.setBackgroundResource(R.drawable.fondo_boton3);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        txtPasswordRepeat.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                txtPasswordRepeat.setHintTextColor(Color.GRAY);
                txtPasswordRepeat.setBackgroundResource(android.R.drawable.edit_text);

                if(!txtPassword.getText().toString().equals("") && !txtEmail.getText().toString().equals(""))
                {
                    buttonNextStep.setEnabled(true);
                    buttonNextStep.setBackgroundResource(R.drawable.fondo_boton3);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        //endregion

        //region Buttons
        buttonNextStep.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!txtEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
                {
                    txtEmail.setHintTextColor(Color.RED);
                    txtEmail.setBackgroundResource(R.drawable.edittext_border);

                    Toast.makeText(FragmentLoginCreateUser.this, "This email is invalid", Toast.LENGTH_SHORT).show();

                    if (!txtPassword.getText().toString().equals(txtPasswordRepeat.getText().toString()))
                    {
                        txtPassword.setHintTextColor(Color.RED);
                        txtPassword.setBackgroundResource(R.drawable.edittext_border);

                        txtPasswordRepeat.setHintTextColor(Color.RED);
                        txtPasswordRepeat.setBackgroundResource(R.drawable.edittext_border);

                        Toast.makeText(FragmentLoginCreateUser.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (txtPassword.getText().toString().length() < 6)
                {
                    txtPassword.setHintTextColor(Color.RED);
                    txtPassword.setBackgroundResource(R.drawable.edittext_border);

                    txtPasswordRepeat.setHintTextColor(Color.RED);
                    txtPasswordRepeat.setBackgroundResource(R.drawable.edittext_border);

                    Toast.makeText(FragmentLoginCreateUser.this, "Password is too short, 6 characters minimum", Toast.LENGTH_SHORT).show();
                }
                else if (!txtPassword.getText().toString().equals(txtPasswordRepeat.getText().toString()))
                {
                    txtPassword.setHintTextColor(Color.RED);
                    txtPassword.setBackgroundResource(R.drawable.edittext_border);

                    txtPasswordRepeat.setHintTextColor(Color.RED);
                    txtPasswordRepeat.setBackgroundResource(R.drawable.edittext_border);

                    Toast.makeText(FragmentLoginCreateUser.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                }
                else if(txtEmail.getText().toString().equals(""))
                {
                    txtEmail.setHintTextColor(Color.RED);
                    txtEmail.setBackgroundResource(R.drawable.edittext_border);
                }
                else if(txtPassword.getText().toString().equals(""))
                {
                    txtPassword.setHintTextColor(Color.RED);
                    txtPassword.setBackgroundResource(R.drawable.edittext_border);
                }
                else if(txtPasswordRepeat.getText().toString().equals(""))
                {
                    txtPasswordRepeat.setHintTextColor(Color.RED);
                    txtPasswordRepeat.setBackgroundResource(R.drawable.edittext_border);
                }
                /*else if(contador == 0)
                {
                    txtEmail.setVisibility(View.GONE);
                    txtPassword.setVisibility(View.GONE);
                    txtPasswordRepeat.setVisibility(View.GONE);
                    tittle.setVisibility(View.GONE);

                    FragmentTransaction Ft = getSupportFragmentManager().beginTransaction();

                    Ft.replace(R.id.fragmentnewuser, fragmentMaps);

                    Ft.addToBackStack(null);
                    Ft.commit();
                    contador++;
                }*/
                else
                {
                    //Recibir datos de maps y despues crear usuario
                    mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                //Añadir datos de usuario
                                /*Firebase users = firebase.child("users");
                                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser() ;
                                users.child(currentUser.getUid()).child("User").setValue(txtNickName.getText().toString());*/
                            }
                        }
                    });

                    startActivity(new Intent(FragmentLoginCreateUser.this, ActivityInicio.class));
                }
            }
        });
        //endregion
    }
}
