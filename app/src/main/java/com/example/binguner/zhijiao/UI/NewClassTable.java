package com.example.binguner.zhijiao.UI;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.binguner.zhijiao.CallBack.CallBackSuccedLogin;
import com.example.binguner.zhijiao.Entity.ClassBean;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;
import com.example.binguner.zhijiao.Utils.DensityUtil;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewClassTable extends AppCompatActivity {

    @BindView(R.id.ct_1)
    Button ct_1;
    @BindView(R.id.ct_1_mon)
    Button ct_1_mon;
    @BindView(R.id.ct_1_tues)
    Button ct_1_tues;
    @BindView(R.id.ct_1_wen)
    Button ct_1_wen;
    @BindView(R.id.ct_1_thur)
    Button ct_1_thur;
    @BindView(R.id.ct_1_fri)
    Button ct_1_fri;
    @BindView(R.id.ct_1_sat)
    Button ct_1_sat;
    @BindView(R.id.ct_1_sun)
    Button ct_1_sun;

    @BindView(R.id.ct_2)
    Button ct_2;
    @BindView(R.id.ct_2_mon)
    Button ct_2_mon;
    @BindView(R.id.ct_2_tues)
    Button ct_2_tues;
    @BindView(R.id.ct_2_wen)
    Button ct_2_wen;
    @BindView(R.id.ct_2_thur)
    Button ct_2_thur;
    @BindView(R.id.ct_2_fri)
    Button ct_2_fri;
    @BindView(R.id.ct_2_sat)
    Button ct_2_sat;
    @BindView(R.id.ct_2_sun)
    Button ct_2_sun;

    @BindView(R.id.ct_3)
    Button ct_3;
    @BindView(R.id.ct_3_mon)
    Button ct_3_mon;
    @BindView(R.id.ct_3_tues)
    Button ct_3_tues;
    @BindView(R.id.ct_3_wen)
    Button ct_3_wen;
    @BindView(R.id.ct_3_thur)
    Button ct_3_thur;
    @BindView(R.id.ct_3_fri)
    Button ct_3_fri;
    @BindView(R.id.ct_3_sat)
    Button ct_3_sat;
    @BindView(R.id.ct_3_sun)
    Button ct_3_sun;

    @BindView(R.id.ct_4)
    Button ct_4;
    @BindView(R.id.ct_4_mon)
    Button ct_4_mon;
    @BindView(R.id.ct_4_tues)
    Button ct_4_tues;
    @BindView(R.id.ct_4_wen)
    Button ct_4_wen;
    @BindView(R.id.ct_4_thur)
    Button ct_4_thur;
    @BindView(R.id.ct_4_fri)
    Button ct_4_fri;
    @BindView(R.id.ct_4_sat)
    Button ct_4_sat;
    @BindView(R.id.ct_4_sun)
    Button ct_4_sun;

    @BindView(R.id.ct_5)
    Button ct_5;
    @BindView(R.id.ct_5_mon)
    Button ct_5_mon;
    @BindView(R.id.ct_5_tues)
    Button ct_5_tues;
    @BindView(R.id.ct_5_wen)
    Button ct_5_wen;
    @BindView(R.id.ct_5_thur)
    Button ct_5_thur;
    @BindView(R.id.ct_5_fri)
    Button ct_5_fri;
    @BindView(R.id.ct_5_sat)
    Button ct_5_sat;
    @BindView(R.id.ct_5_sun)
    Button ct_5_sun;

    @BindView(R.id.ct_6)
    Button ct_6;
    @BindView(R.id.ct_6_mon)
    Button ct_6_mon;
    @BindView(R.id.ct_6_tues)
    Button ct_6_tues;
    @BindView(R.id.ct_6_wen)
    Button ct_6_wen;
    @BindView(R.id.ct_6_thur)
    Button ct_6_thur;
    @BindView(R.id.ct_6_fri)
    Button ct_6_fri;
    @BindView(R.id.ct_6_sat)
    Button ct_6_sat;
    @BindView(R.id.ct_6_sun)
    Button ct_6_sun;

    @BindView(R.id.ct_7)
    Button ct_7;
    @BindView(R.id.ct_7_mon)
    Button ct_7_mon;
    @BindView(R.id.ct_7_tues)
    Button ct_7_tues;
    @BindView(R.id.ct_7_wen)
    Button ct_7_wen;
    @BindView(R.id.ct_7_thur)
    Button ct_7_thur;
    @BindView(R.id.ct_7_fri)
    Button ct_7_fri;
    @BindView(R.id.ct_7_sat)
    Button ct_7_sat;
    @BindView(R.id.ct_7_sun)
    Button ct_7_sun;

    @BindView(R.id.ct_8)
    Button ct_8;
    @BindView(R.id.ct_8_mon)
    Button ct_8_mon;
    @BindView(R.id.ct_8_tues)
    Button ct_8_tues;
    @BindView(R.id.ct_8_wen)
    Button ct_8_wen;
    @BindView(R.id.ct_8_thur)
    Button ct_8_thur;
    @BindView(R.id.ct_8_fri)
    Button ct_8_fri;
    @BindView(R.id.ct_8_sat)
    Button ct_8_sat;
    @BindView(R.id.ct_8_sun)
    Button ct_8_sun;

    @BindView(R.id.ct_9)
    Button ct_9;
    @BindView(R.id.ct_9_mon)
    Button ct_9_mon;
    @BindView(R.id.ct_9_tues)
    Button ct_9_tues;
    @BindView(R.id.ct_9_wen)
    Button ct_9_wen;
    @BindView(R.id.ct_9_thur)
    Button ct_9_thur;
    @BindView(R.id.ct_9_fri)
    Button ct_9_fri;
    @BindView(R.id.ct_9_sat)
    Button ct_9_sat;
    @BindView(R.id.ct_9_sun)
    Button ct_9_sun;

    @BindView(R.id.ct_10)
    Button ct_10;
    @BindView(R.id.ct_10_mon)
    Button ct_10_mon;
    @BindView(R.id.ct_10_tues)
    Button ct_10_tues;
    @BindView(R.id.ct_10_wen)
    Button ct_10_wen;
    @BindView(R.id.ct_10_thur)
    Button ct_10_thur;
    @BindView(R.id.ct_10_fri)
    Button ct_10_fri;
    @BindView(R.id.ct_10_sat)
    Button ct_10_sat;
    @BindView(R.id.ct_10_sun)
    Button ct_10_sun;

    @BindView(R.id.ct_11)
    Button ct_11;
    @BindView(R.id.ct_11_mon)
    Button ct_11_mon;
    @BindView(R.id.ct_11_tues)
    Button ct_11_tues;
    @BindView(R.id.ct_11_wen)
    Button ct_11_wen;
    @BindView(R.id.ct_11_thur)
    Button ct_11_thur;
    @BindView(R.id.ct_11_fri)
    Button ct_11_fri;
    @BindView(R.id.ct_11_sat)
    Button ct_11_sat;
    @BindView(R.id.ct_11_sun)
    Button ct_11_sun;

    private static List<ClassBean.TableBean> tableBeans = new ArrayList<>();
    private List<Button> buttons_time = new ArrayList<>();
    private List<Button> buttons_week = new ArrayList<>();
    private TYUTUtils tyutUtils = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classtablelayout);
        ButterKnife.bind(this);
        initTable();

        getDatas();
        tyutUtils.setCallBack(null, null, new CallBackSuccedLogin() {
            @Override
            public void callBackLoginStats(int stats) {
                if (stats == 1) {
                    //ct_1_mon.setText("33");
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    //Button b = buttons_week.get(2);
                    //b.setVisibility(View.INVISIBLE);
                }
            }
        });
        addListener();
    }

    private void addListener() {
        for(final Button b: buttons_week){
            if(!b.getText().toString().contains("   ")){
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Snackbar snackbar = Snackbar.make(view,b.getText().toString(),Snackbar.LENGTH_SHORT);
                        snackbar.setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                snackbar.dismiss();
                            }
                        }).show();
                    }
                });
            }
        }
    }

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    initDatas();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public static void setBeans(List<ClassBean.TableBean> tableBeans) {
        NewClassTable.tableBeans = tableBeans;
        Log.d("comaodansdoasn", tableBeans.get(0).getMonday().toString());
    }

    private void getDatas() {
        tyutUtils = new TYUTUtils(this);
        tyutUtils.getClass("2016006593", "144517");
    }

    private void initDatas() {
        try {
            /*for (int i = 0; i < 11; i++) {
                buttons_time.get(i).setText(tableBeans.get(i).getTime());
            }*/

            for (int i = 0; i < 11; i++) {
                //for(int j = 0 ;i < 10 ;j++){
                buttons_week.get(0).setText(tableBeans.get(0).getMonday());
                buttons_week.get(1).setText(tableBeans.get(1).getMonday());
                buttons_week.get(2).setText(tableBeans.get(2).getMonday());
                buttons_week.get(3).setText(tableBeans.get(3).getMonday());
                buttons_week.get(4).setText(tableBeans.get(4).getMonday());
                buttons_week.get(5).setText(tableBeans.get(5).getMonday());
                buttons_week.get(6).setText(tableBeans.get(6).getMonday());
                buttons_week.get(7).setText(tableBeans.get(7).getMonday());
                buttons_week.get(8).setText(tableBeans.get(8).getMonday());
                buttons_week.get(9).setText(tableBeans.get(9).getMonday());
                buttons_week.get(10).setText(tableBeans.get(10).getMonday());
//                buttons_week.get(11).setText(tableBeans.get(11).getMonday());
                //}
            }
            for (int i = 11; i < 22; i++) {
                buttons_week.get(i).setText(tableBeans.get(i-11).getTuesday());
            }
            for (int i = 22; i < 33; i++) {
                buttons_week.get(i).setText(tableBeans.get(i-22).getWednesday());
            }
            for (int i = 33; i < 44; i++) {
                buttons_week.get(i).setText(tableBeans.get(i-33).getThursday());
            }
            for (int i = 44; i < 55; i++) {
                buttons_week.get(i).setText(tableBeans.get(i-44).getFriday());
            }
            for (int i = 55; i < 66; i++) {
                buttons_week.get(i).setText(tableBeans.get(i-55).getSaturday());
            }
            for (int i = 66; i < 77; i++) {
                buttons_week.get(i).setText(tableBeans.get(i-66).getSunday());
            }
            //buttons_week.get(2).setText("shit");
            for (Button b : buttons_week) {
                if(b.getText().toString().contains("   ")){
                    b.setBackgroundColor(getResources().getColor(R.color.colorTransparent));
                }
            }

            for(int i = 1 ; i < 77 ;i ++){
                if(buttons_week.get(i).getText().equals(buttons_week.get(i-1).getText()) && !buttons_week.get(i).getText().toString().contains("   ")){
                    buttons_week.get(i).setBackgroundColor(getResources().getColor(R.color.colorTransparent));
                    buttons_week.get(i).setBackground(null);

                    buttons_week.get(i).setText("");
                    buttons_week.get(i).setClickable(false);


                    //int x = DensityUtil.dip2px(this,120);
                    //Log.d("XDFGHJHGFDFGHJHGFGH",x+"");
                    buttons_week.get(i-1).getLayoutParams().height = DensityUtil.dip2px(this,100);
                }else if(buttons_week.get(i).getText().toString().equals("   ")){
                    Log.d("dfgh",""+i);
                    buttons_week.get(i).setClickable(false);
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    private void initTable() {
        buttons_time.add(ct_1);
        buttons_time.add(ct_2);
        buttons_time.add(ct_3);
        buttons_time.add(ct_4);
        buttons_time.add(ct_5);
        buttons_time.add(ct_6);
        buttons_time.add(ct_7);
        buttons_time.add(ct_8);
        buttons_time.add(ct_9);
        buttons_time.add(ct_10);
        buttons_time.add(ct_11);

        buttons_week.add(ct_1_mon);// 0
        buttons_week.add(ct_2_mon);// 1
        buttons_week.add(ct_3_mon);// 2
        buttons_week.add(ct_4_mon);// 3
        buttons_week.add(ct_5_mon);// 4
        buttons_week.add(ct_6_mon);// 5
        buttons_week.add(ct_7_mon);// 6
        buttons_week.add(ct_8_mon);// 7
        buttons_week.add(ct_9_mon);// 8
        buttons_week.add(ct_10_mon);// 9
        buttons_week.add(ct_11_mon);// 10

        buttons_week.add(ct_1_tues);// 11
        buttons_week.add(ct_2_tues);// 12
        buttons_week.add(ct_3_tues);// 13
        buttons_week.add(ct_4_tues);// 14
        buttons_week.add(ct_5_tues);// 15
        buttons_week.add(ct_6_tues);// 16
        buttons_week.add(ct_7_tues);// 17
        buttons_week.add(ct_8_tues);// 18
        buttons_week.add(ct_9_tues);// 19
        buttons_week.add(ct_10_tues);// 20
        buttons_week.add(ct_11_tues);// 21

        buttons_week.add(ct_1_wen);//22
        buttons_week.add(ct_2_wen);//23
        buttons_week.add(ct_3_wen);//24
        buttons_week.add(ct_4_wen);//25
        buttons_week.add(ct_5_wen);//26
        buttons_week.add(ct_6_wen);//27
        buttons_week.add(ct_7_wen);//28
        buttons_week.add(ct_8_wen);//29
        buttons_week.add(ct_9_wen);//30
        buttons_week.add(ct_10_wen);//31
        buttons_week.add(ct_11_wen);//32

        buttons_week.add(ct_1_thur);//33
        buttons_week.add(ct_2_thur);//34
        buttons_week.add(ct_3_thur);//35
        buttons_week.add(ct_4_thur);//36
        buttons_week.add(ct_5_thur);//37
        buttons_week.add(ct_6_thur);//38
        buttons_week.add(ct_7_thur);//39
        buttons_week.add(ct_8_thur);//40
        buttons_week.add(ct_9_thur);//41
        buttons_week.add(ct_10_thur);//42
        buttons_week.add(ct_11_thur);//43

        buttons_week.add(ct_1_fri);//44
        buttons_week.add(ct_2_fri);//45
        buttons_week.add(ct_3_fri);//46
        buttons_week.add(ct_4_fri);//47
        buttons_week.add(ct_5_fri);//48
        buttons_week.add(ct_6_fri);//49
        buttons_week.add(ct_7_fri);//50
        buttons_week.add(ct_8_fri);//51
        buttons_week.add(ct_9_fri);//52
        buttons_week.add(ct_10_fri);//53
        buttons_week.add(ct_11_fri);//54

        buttons_week.add(ct_1_sat);//55
        buttons_week.add(ct_2_sat);//56
        buttons_week.add(ct_3_sat);//57
        buttons_week.add(ct_4_sat);//58
        buttons_week.add(ct_5_sat);//59
        buttons_week.add(ct_6_sat);//60
        buttons_week.add(ct_7_sat);//61
        buttons_week.add(ct_8_sat);//62
        buttons_week.add(ct_9_sat);//63
        buttons_week.add(ct_10_sat);//64
        buttons_week.add(ct_11_sat);//65

        buttons_week.add(ct_1_sun);//66
        buttons_week.add(ct_2_sun);//67
        buttons_week.add(ct_3_sun);//68
        buttons_week.add(ct_4_sun);//69
        buttons_week.add(ct_5_sun);//70
        buttons_week.add(ct_6_sun);//71
        buttons_week.add(ct_7_sun);//72
        buttons_week.add(ct_8_sun);//73
        buttons_week.add(ct_9_sun);//74
        buttons_week.add(ct_10_sun);//75
        buttons_week.add(ct_11_sun);//76
    }
}