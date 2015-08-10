package com.imaginart.login_registeruser;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jean on 10/08/2015.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("Nombre", user.nombre);
        spEditor.putString("Username", user.username);
        spEditor.putString("Email", user.email);
        spEditor.putString("Password", user.password);
        spEditor.commit();

    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();
    }


    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }


    public User getLoggedInUser() {

        if (userLocalDatabase.getBoolean("LoggedIn", false) == false) {
            return null;
        }

        String nombre = userLocalDatabase.getString("Nombre", "");
        String email = userLocalDatabase.getString("Email", "");
        String username = userLocalDatabase.getString("Username", "");
        String password = userLocalDatabase.getString("Password", "");


        User storedUser = new User(nombre, username, password, email);

        return storedUser;
    }



    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("LoggedIn", false) == true){
            return true;

        }
        else {
            return false;
        }
    }

}

