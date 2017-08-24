package com.example.binguner.zhijiao.UI;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.View.WaveView;

public class DetialAty extends AppCompatActivity {

    private WebView detialWeview;
    private String url;
    private android.support.v7.widget.Toolbar detialToolbar;
    private ImageView detialsettingpoint;
    private WaveView detial_waveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View decorView = getWindow().getDecorView();
        //让应用主题内容占用系统状态栏的空间,注意:下面两个参数必须一起使用 stable 牢固的
        // int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        //decorView.setSystemUiVisibility(option);
        //Window window = this.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // getWindow().setStatusBarColor(R.color.colorBlue);
        getSupportActionBar().hide();
        setTheme(R.style.AppTheme);

        setContentView(R.layout.activity_detial_aty);
        initId();
        initViews();
        setListener();
    }


    private void setListener() {
        detialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        detialsettingpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(DetialAty.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.detial_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.detial_copy_link:
                                if (url != null) {
                                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    clipboardManager.setPrimaryClip(ClipData.newPlainText("detial_url", url));
                                    Toast.makeText(DetialAty.this, "保存成功", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(DetialAty.this, "保存失败", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case R.id.detial_open_in_browsher:
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                Uri uri = Uri.parse(url);
                                intent.setData(uri);
                                startActivity(intent);
                                break;
                            case R.id.detial_share:
                                Intent intent1 = new Intent(Intent.ACTION_SEND);
                                intent1.setType("text/plain");
                                intent1.putExtra(Intent.EXTRA_TEXT,url+" from 「知教」");
                                startActivity(Intent.createChooser(intent1,"分享"));
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        detialWeview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress == 100){
                    detial_waveView.setVisibility(View.GONE);
                    detialWeview.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initViews() {
        //获取数据
        Bundle bundle = this.getIntent().getExtras();
        url = bundle.getString("url");
        detialWeview.loadUrl("http://" + url);
        Log.d("urlTAg", url);
        detialWeview.getSettings().setJavaScriptEnabled(true);
        detialWeview.getSettings().setSupportZoom(true);
        detialWeview.getSettings().setBuiltInZoomControls(true);
        detialWeview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        detialWeview.getSettings().setLoadWithOverviewMode(true);
        detialWeview.getSettings().setUseWideViewPort(true);
        //detialWeview.getSettings().setDefaultZoom(ZoomDensity.CLOSE);// 默认缩放模式
        detialWeview.getSettings().setUseWideViewPort(true);
        detialWeview.setWebViewClient(new WebViewClient());

        //设置 toolbar
        detialToolbar.setNavigationIcon(R.mipmap.back_white_48);

    }

    private void initId() {
        detial_waveView = findViewById(R.id.detial_waveView);
        detialsettingpoint = findViewById(R.id.detialsettingpoint);
        detialToolbar = findViewById(R.id.detialToolbar);
        detialWeview = findViewById(R.id.detialWeview);
    }
}
