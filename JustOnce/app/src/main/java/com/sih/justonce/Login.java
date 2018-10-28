package com.sih.justonce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity{

    private Button buttonLogin;
    private EditText email;
    private EditText password;
    private TextView SignUp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    @Override
    protected void onStart(){
        super.onStart();

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){

            finish();
            startActivity(new Intent(getApplicationContext(), EventCategories.class));
        }
    }

    public void goToSignUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void loginUser(View view){

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        String _email = email.getText().toString().trim();
        String _password = password.getText().toString().trim();

        if(TextUtils.isEmpty(_email) || TextUtils.isEmpty(_password))
        {
            Toast.makeText(this, "Please enter all the information!", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(_email,_password).addOnCompleteListener(this, new
                OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            finish();
                            startActivity(new Intent(getApplicationContext(), EventCategories.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Could not Login!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
