package com.example.binguner.zhijiao.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;
import com.example.binguner.zhijiao.UI.FooterView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AchieveFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AchieveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AchieveFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private Button btn_achieve_searchgrades,btn_achieve_studentgpa,btn_achieve_schedule,btn_achieve_easyjudge,btn_achieve_studentinfo;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initId();
        initViews();
        setListener();

    }

    private void initViews() {

    }

    private void setListener() {
        btn_achieve_searchgrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"成绩查询",Toast.LENGTH_SHORT).show();
                //TYUTUtils tyutUtils = new TYUTUtils();
                //tyutUtils.getAnnouncements();
                try {
                    Intent intent = new Intent(getContext(), FooterView.class);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        btn_achieve_studentgpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"学分绩点",Toast.LENGTH_SHORT).show();
                /*String str = "2015/07/20 11:01 来源: 测试";
                String regex = "\\d{4}[-|/]\\d{2}[-|/]\\d{2} \\d{2}:\\d{2}";
                Pattern p = Pattern.compile(regex);
                Matcher matcher = p.matcher(str);
                if (matcher.find()) {
                    System.out.println(matcher.group());
                }*/
            }
        });
        btn_achieve_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"学期课表",Toast.LENGTH_SHORT).show();
            }
        });
        btn_achieve_easyjudge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"一键评教",Toast.LENGTH_SHORT).show();
            }
        });
        btn_achieve_studentinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"学籍信息",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initId() {
        btn_achieve_studentinfo = getView().findViewById(R.id.btn_achieve_studentinfo);
        btn_achieve_easyjudge = getView().findViewById(R.id.btn_achieve_easyjudge);
        btn_achieve_schedule = getView().findViewById(R.id.btn_achieve_schedule);
        btn_achieve_studentgpa = getView().findViewById(R.id.btn_achieve_studentgpa);
        btn_achieve_searchgrades = getView().findViewById(R.id.btn_achieve_searchgrades);
    }

    public AchieveFragment() {
        // Required empty public constructor
    }

    public static AchieveFragment newInstance() {
        AchieveFragment fragment = new AchieveFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_achieve, container, false);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
