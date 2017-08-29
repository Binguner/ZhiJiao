package com.example.binguner.zhijiao.UI;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @OnClick(R.id.login_findMe)
    void findMe(View v){
        openQQ(v,"819985138");
    }

    private void openQQ(View view,@Nullable String qqNum){
        if(checkAppExist(this,"com.tencent.mobileqq")){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin="+qqNum+"&version=1")));
        }else if (checkAppExist(this,"com.tencent.tim")){
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin="+qqNum+"&version=1")));
        }else {
            Snackbar.make(view,"未安装QQ",Snackbar.LENGTH_SHORT).show();
        }
    }

    private boolean checkAppExist(Context context, String packageName){
        if(packageName == null || "".equals(packageName)){
            return false;
        }try{
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
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
        ed_username.setTextColor(R.color.colorWhite);
        password_textinputlayout.setHint("Password");
    }

    private void initId() {
        tyutUtils =  new TYUTUtils(this);
    }
}
