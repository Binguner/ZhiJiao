package com.example.binguner.zhijiao.UI;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binguner.zhijiao.CallBack.CallBackSuccedLogin;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Interceptor;

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
    @BindView(R.id.islongint_rot)
    ImageView islongint_rot;

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
        openQQ(v,"478718805");
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
                }else {
                    username_textinputlayout.setErrorEnabled(false);
                }
                if(password.isEmpty()){
                    password_textinputlayout.setError("Please input the password !");
                }else {
                   // username_textinputlayout.setErrorEnabled(false);
                    password_textinputlayout.setErrorEnabled(false);
                }
                hideKeybord();
                if(!password.isEmpty()&&!username.isEmpty()){
                    setIsLoging(view);
                    startRo();
                    tyutUtils.firstLogin(username,password);
                    tyutUtils.setCallBack(null, null, new CallBackSuccedLogin() {
                        @Override
                        public void callBackLoginStats(int stats) {
                            if(stats == 1){
                                islongint_rot.setAnimation(null);
                                islongint_rot.setVisibility(View.INVISIBLE);
                                LoginActivity.this.finish();

                            }
                            if(stats == 0){
                                islongint_rot.setAnimation(null);
                                islongint_rot.setVisibility(View.INVISIBLE);
                            }else if(stats == 2){
                                final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(),"请检查网络",Snackbar.LENGTH_SHORT);
                                snackbar.setAction("Check", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                        startActivity(intent);
                                        snackbar.dismiss();
                                    }
                                }).show();
                            }
                        }
                    });
                }
            }
        });

    }


    private void setIsLoging(View view){
        islongint_rot.setVisibility(View.VISIBLE);
        /*popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.isloginglayout,null));
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00808080));
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(view);*/
        //popupWindow.showAtLocation(panrnt,Gravity.CENTER,0,0);
    }

    private void startRo(){
        /*View view = LayoutInflater.from(this).inflate(R.layout.isloginglayout,null);
        ImageView imageView = view.findViewById(R.id.isloging_rot);
        Log.d("rotating","before");
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.loging_ro);
       // imageView.setAnimation(animation);
        Log.d("rotating",imageView.toString());
        view.startAnimation(animation);
        loginbtn.startAnimation(animation);
        canclebtn.startAnimation(animation);
        Log.d("rotating","after");*/
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.loging_ro);
        animation.setInterpolator(linearInterpolator);
        islongint_rot.startAnimation(animation);
    }

    private void hideKeybord(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if(view!=null){
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    

    private void initViews() {
        username_textinputlayout.setHint("Username");
        ed_username.setTextColor(getResources().getColor(R.color.colorWhite));
        password_textinputlayout.setHint("Password");
        //getWindow().setBackgroundDrawable(getResources().getDrawable(R.mipmap.login_bg1));
    }

    private void initId() {
        tyutUtils =  new TYUTUtils(this);
    }
}
