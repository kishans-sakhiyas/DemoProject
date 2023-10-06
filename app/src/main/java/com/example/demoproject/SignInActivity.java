package com.example.demoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.data.MyDbHandler;
import com.example.demoproject.model.UserModel;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    TextView txtSignUp;
    EditText edtEmail, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        MyDbHandler db = new MyDbHandler(SignInActivity.this);

        txtSignUp = findViewById(R.id.txtSignUp);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);

        //get all user
        List<UserModel> allUser = db.getAllUser();




        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length() > 4) {
                    for (UserModel userModel : allUser){
                        Log.d("email",userModel.getEmail());
                        Log.d("password",userModel.getPassword());
                        if(userModel.getEmail().equals(email) && userModel.getPassword().equals(password)){
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }


                } else {
                    Toast.makeText(SignInActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}