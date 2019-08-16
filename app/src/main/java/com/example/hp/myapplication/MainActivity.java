//Author: Veeresh Raj Bahri

package com.example.hp.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    TextView slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignUp = (Button) findViewById(R.id.btnSignUp); //Button for Sign Up
        btnSignIn = (Button)findViewById(R.id.btnSignIn); //Button for Sign In

        slogan = (TextView)findViewById(R.id.slogan); //For the text slogan

        Typeface face = Typeface.createFromAsset(getAssets(),"Fonts/NABILA.TTF");
        slogan.setTypeface(face);

        btnSignIn.setOnClickListener(new View.OnClickListener() {            // Click Listener is attached here to the button
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SignIn.class);        // SignIn Activity opens here
                startActivity(i);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {             // Click Listener is attached here to the button
            @Override
            public void onClick(View v) {
            Intent SignUp = new Intent(MainActivity.this, SignUp.class);     // SignUp activity opens here
            startActivity(SignUp);
            }
        });
    }
}
