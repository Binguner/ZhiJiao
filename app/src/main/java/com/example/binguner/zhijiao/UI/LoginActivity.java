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

    private Toolbar logintoolbar;
    private Button loginbtn;
    private TextInputLayout username_textinputlayout,password_textinputlayout;
    private EditText ed_username,ed_password;
    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Base);
        setContentView(R.layout.activity_login);
        initId();
        initViews();
        setListener();
    }

    private void setListener() {
        logintoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"Clicked "+username+" "+password,Toast.LENGTH_SHORT).show();
            }
        });

        ed_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                username = editable.toString();
            }
        });

        ed_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                password = editable.toString();
            }
        });

    }

    private void initViews() {
        logintoolbar.setNavigationIcon(R.mipmap.back_white_48);
        username_textinputlayout.setHint("Username");
        password_textinputlayout.setHint("Password");
       // username_textinputlayout.set
    }

    private void initId() {
        ed_password = findViewById(R.id.ed_password);
        ed_username = findViewById(R.id.ed_username);
        loginbtn = findViewById(R.id.loginbtn);
        password_textinputlayout = findViewById(R.id.password_textinputlayout);
        username_textinputlayout = findViewById(R.id.username_textinputlayout);
        logintoolbar = findViewById(R.id.logintoolbar);
    }
}
