package com.imaginart.login_registeruser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register extends AppCompatActivity implements View.OnClickListener{

    Button  btnRegister;
    EditText   strNombre, strUsername, strPassword, strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        strUsername = (EditText) findViewById(R.id.strUsuario);
        strEmail = (EditText) findViewById(R.id.strEmail);
        strNombre = (EditText) findViewById(R.id.strNombre);
        strPassword = (EditText) findViewById(R.id.strPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegister:
                String nombre = strNombre.getText().toString();
                String username = strUsername.getText().toString();
                String password = strPassword.getText().toString();
                String email = strEmail.getText().toString();

                User user = new User(nombre, username, password, email);
                registerUser(user);
                break;
        }

    }

    private void registerUser(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent loginIntent = new Intent(register.this, Login.class);
                startActivity(loginIntent);
            }
        });
    }
}
