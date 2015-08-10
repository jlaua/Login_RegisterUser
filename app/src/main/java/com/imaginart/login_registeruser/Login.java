package com.imaginart.login_registeruser;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends ActionBarActivity implements View.OnClickListener {

    Button btnLogin;
    EditText strUsername, strPassword;
    TextView txtRegisterLink;
    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        strUsername = (EditText) findViewById(R.id.strUsuario);
        strPassword = (EditText) findViewById(R.id.strPassword);
        txtRegisterLink = (TextView) findViewById(R.id.txtRegisterLink);

        btnLogin.setOnClickListener(this);
        txtRegisterLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnLogin:
                String username = strUsername.getText().toString();
                String password = strPassword.getText().toString();

                User user = new User(username, password);
                authenticate(user);
                break;

            case R.id.txtRegisterLink:
                Intent registerIntent = new Intent(Login.this, register.class);
                startActivity(registerIntent);
                break;


        }
    }

    private void authenticate(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchUserDataAsyncTask(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(this, MainActivity.class));
    }
}
