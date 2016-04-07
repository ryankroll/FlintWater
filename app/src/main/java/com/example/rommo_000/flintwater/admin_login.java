package com.example.rommo_000.flintwater;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class admin_login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void AuthUser(View view) {
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase(getString(R.string.Firebase_URL));
        EditText uname = (EditText)findViewById(R.id.username);
        String username = uname.getText().toString();
        EditText pass = (EditText)findViewById(R.id.password);
        String password = pass.getText().toString();
        //firebase.authWithPassword(findViewById(R.id.username).toString(), findViewById(R.id.password).toString(), new AuthResult());
        firebase.authWithPassword(username,password,new AuthResult());
    }

    public void goToEditdb() {

        Intent intent = new Intent(this,edit_db.class);
        startActivity(intent);
    }
    public void didntWork()
    {
        Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show();
    }

    class AuthResult implements Firebase.AuthResultHandler {

        @Override
        public void onAuthenticated(AuthData authData) {
            goToEditdb();
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            didntWork();
        }
    }
}
