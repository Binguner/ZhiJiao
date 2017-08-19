package com.example.binguner.zhijiao.UI;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.binguner.zhijiao.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginbtn,canclebtn;
    private TextInputLayout username_textinputlayout,password_textinputlayout;
    private EditText ed_username,ed_password;
    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_login);
        setContentView(R.layout.activity_login);
        initId();
        initViews();
        setListener();
    }

    private void setListener() {
        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = username_textinputlayout.getEditText().getText().toString();
                password = password_textinputlayout.getEditText().getText().toString();
                Toast.makeText(LoginActivity.this,"Clicked "+username+" "+password,Toast.LENGTH_SHORT).show();
                if(username.isEmpty()){
                    username_textinputlayout.setError("Please input the username !");
                }
                if(password.isEmpty()){
                    password_textinputlayout.setError("Please input the password !");
                }else {
                    username_textinputlayout.setErrorEnabled(false);
                    password_textinputlayout.setErrorEnabled(false);
                }
            }
        });




    }

    private void initViews() {
        username_textinputlayout.setHint("Username");
        password_textinputlayout.setHint("Password");
    }

    private void initId() {
        canclebtn = findViewById(R.id.canclebtn);
        ed_password = findViewById(R.id.ed_password);
        ed_username = findViewById(R.id.ed_username);
        loginbtn = findViewById(R.id.loginbtn);
        password_textinputlayout = findViewById(R.id.password_textinputlayout);
        username_textinputlayout = findViewById(R.id.username_textinputlayout);
    }
}
