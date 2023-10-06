package com.example.demoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demoproject.data.MyDbHandler;
import com.example.demoproject.model.UserModel;

public class MainActivity extends AppCompatActivity {

    Button btnLogout, btnPUpdate;
    EditText edtUName, edtUNumber;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);
        btnPUpdate = findViewById(R.id.btnUProfile);
        edtUName = findViewById(R.id.edtUName);
        edtUNumber = findViewById(R.id.edtUNumber);

        MyDbHandler db = new MyDbHandler(MainActivity.this);


        btnPUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtUName.getText().toString();
                String number = edtUNumber.getText().toString();
                UserModel userModel = new UserModel(name,number);
                db.updateUser(userModel);
                Toast.makeText(MainActivity.this, "Update Your Profile", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}