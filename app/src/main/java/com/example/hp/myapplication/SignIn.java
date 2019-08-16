Author: Veeresh Raj Bahri

package com.example.hp.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.myapplication.Common.Common;
import com.example.hp.myapplication.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);                     // This object is used for storing the  Phone number
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);              // This object is used for storing the  Password

        btnSignIn = (Button)findViewById(R.id.btnSignIn);                            // Button for Sign In

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);       // Code for Progress Bar on SignIn
                progressDialog.setMessage("Please wait!");
                progressDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {      s

                            progressDialog.dismiss();

                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());
                            if(edtPhone.getText().toString().equals("") || edtPassword.getText().toString().equals(""))      // Condition check for unfilled field
                            {
                                Toast.makeText(SignIn.this,"Phone Number and password can't be empty!",Toast.LENGTH_SHORT).show();

                            }
                            else if (user.getPassword().equals(edtPassword.getText().toString())) {
                                //Toast.makeText(SignIn.this, "Welcome to Tomato..!", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(SignIn.this,Home.class);                                            // Home Activity opens here
                                Common.currentUser = user;
                                startActivity(home);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Phone no. or Password is incorrect..", Toast.LENGTH_SHORT).show();   //C ondition check fails  for password
                            }
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(SignIn.this, "Please Sign Up First..!",Toast.LENGTH_SHORT).show();                      // Message for  signin first
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
