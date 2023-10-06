package com.example.demoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.data.MyDbHandler;
import com.example.demoproject.model.UserModel;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText edtName, edtNumber, edtEmail, edtPassword;
    Button btnSignUp;
    TextView txtSignIn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MyDbHandler db = new MyDbHandler(SignUpActivity.this);

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtSignIn = findViewById(R.id.txtSignIn);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String number = edtNumber.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
                if (number.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                } else if (number.length() < 10) {
                    Toast.makeText(SignUpActivity.this, "Enter Valid Number", Toast.LENGTH_SHORT).show();
                }
                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length() > 4){
                    //valid sucess
                    UserModel userModel = new UserModel(name,number, email,password);
                    db.addUser(userModel);
                    Toast.makeText(SignUpActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(SignUpActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }


            }
        });


        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}