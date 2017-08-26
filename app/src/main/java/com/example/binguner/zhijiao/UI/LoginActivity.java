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
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginbtn)
    Button loginbtn;
    @BindView(R.id.canclebtn)
    Button canclebtn;
    @BindView(R.id.username_textinputlayout)
    TextInputLayout username_textinputlayout;
    @BindView(R.id.password_textinputlayout)
    TextInputLayout password_textinputlayout;
    @BindView(R.id.ed_username)
    EditText ed_username;
    @BindView(R.id.ed_password)
    EditText ed_password;
    String username,password;
    TYUTUtils tyutUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_login);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initId();
        initViews();
        setListener();
    }

    private void setListener() {
        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
                tyutUtils.firstLogin(username,password);


            }
        });




    }

    private void initViews() {
        username_textinputlayout.setHint("Username");
        password_textinputlayout.setHint("Password");
    }

    private void initId() {
        tyutUtils =  new TYUTUtils(this);
    }
}
