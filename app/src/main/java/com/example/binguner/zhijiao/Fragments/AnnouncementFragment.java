package com.example.binguner.zhijiao.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Adapter.Work_Announcement_Adapter;
import com.example.binguner.zhijiao.CallBack.CallBackSuccedLogin;
import com.example.binguner.zhijiao.Entity.AnnouncementBean;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;
import com.example.binguner.zhijiao.UI.DetialAty;
import com.example.binguner.zhijiao.Utils.CircularAnim;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnnouncementFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnnouncementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnnouncementFragment extends Fragment {

    private SwipeRefreshLayout announcement_swiperefreshlayout;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView announcement_recyclerview;
    private Work_Announcement_Adapter work_announcement_adapter;
    private OnFragmentInteractionListener mListener;
    private static List<AnnouncementBean.InfoBean> infoBeans = new ArrayList<>();
    private TYUTUtils tyutUtils;
    private int lastItemPosition;
    private static int page = 1;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initId();
        initViews();
        setListener();
    }

    private void initId() {
        work_announcement_adapter = new Work_Announcement_Adapter(R.layout.card_layout_news,infoBeans);
        announcement_swiperefreshlayout = getActivity().findViewById(R.id.announcement_swiperefreshlayout);
        announcement_recyclerview = getActivity().findViewById(R.id.announcement_recyclerview);
        tyutUtils = new TYUTUtils(work_announcement_adapter,announcement_swiperefreshlayout,getContext());
    }


    private void initViews() {

        linearLayoutManager = new LinearLayoutManager(getContext());
        announcement_recyclerview.setLayoutManager(linearLayoutManager);
        announcement_recyclerview.setHasFixedSize(true);
        announcement_recyclerview.setAdapter(work_announcement_adapter);
        announcement_swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.colorRed),getResources().getColor(R.color.colorYellow),getResources().getColor(R.color.colorBlue),getResources().getColor(R.color.colorGreen));
        View view = getLayoutInflater().inflate(R.layout.activity_footer_view,null);
        work_announcement_adapter.addFooterView(view);
        work_announcement_adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        work_announcement_adapter.isFirstOnly(true);
        //第一次加载数据
        //announcement_swiperefreshlayout.setRefreshing(true);
        tyutUtils.getAnnouncements(page);
        tyutUtils.setCallBack(null, null, new CallBackSuccedLogin() {
            @Override
            public void callBackLoginStats(int stats) {
                if(stats == 1){

                }else if(stats == 2){
                    final Snackbar snackbar = Snackbar.make(getView(),"请检查网络",Snackbar.LENGTH_SHORT);
                    snackbar.setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            snackbar.dismiss();
                        }
                    }).show();
                }
            }
        });
       /* tyutUtils.setCallBack(new CallBackStatus() {
            @Override
            public void callBackRefreshing(int status) {
                if(status == 1){
                    announcement_swiperefreshlayout.setRefreshing(false);
                }
            }
        });*/
    }

    public static void AddDatas(List<AnnouncementBean.InfoBean> minfoBeans){
        infoBeans.addAll(minfoBeans);
    }

    public static int getSize(){
        return infoBeans.size();
    }
    private void setListener() {
        announcement_swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                announcement_swiperefreshlayout.setRefreshing(true);
                //重新加载
                DelateAllDatas();
                page = 1;
                announcement_swiperefreshlayout.setRefreshing(false);
                tyutUtils.setCallBack(null, null, new CallBackSuccedLogin() {
                    @Override
                    public void callBackLoginStats(int stats) {
                        Log.d("Testjaja",stats+"");
                        if(stats == 1){
                            final Snackbar snackbar = Snackbar.make(getView(),"加载完毕",Snackbar.LENGTH_SHORT);
                            snackbar.setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    snackbar.dismiss();
                                }
                            }).show();
                        }else if(stats == 2){
                            final Snackbar snackbar = Snackbar.make(getView(),"请检查网络",Snackbar.LENGTH_SHORT);
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
                //Toast.makeText(getContext(),"刷新完毕",Toast.LENGTH_SHORT).show();
            }
        });

        announcement_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition+3 >= linearLayoutManager.getItemCount()){
                    //announcement_swiperefreshlayout.setRefreshing(true);
                    tyutUtils.getAnnouncements(++page);
                    /*tyutUtils.setCallBack(new CallBackStatus() {
                        @Override
                        public void callBackRefreshing(int status) {
                            if(status == 1){
                                announcement_swiperefreshlayout.setRefreshing(false);
                            }
                        }
                    });*/
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

        work_announcement_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, final View view, int position) {

                //Toast.makeText(getContext(),"第 "+ position + "个",Toast.LENGTH_SHORT).show();
                final Intent intent = new Intent(getContext(), DetialAty.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",infoBeans.get(position).getUrl());
                intent.putExtras(bundle);
                final View view1 = getLayoutInflater().inflate(R.layout.activity_detial_aty,null);
                //CircularAnim.show(view1).triggerView(view).go();
                /*CircularAnim.fullActivity(getActivity(),view)
                        .colorOrImageRes(R.color.colorBlue)
                        .duration(300)
                        .startRadius(100)
                        //.overridePendingTransition()
                        .go(new CircularAnim.OnAnimationEndListener() {
                            @Override
                            public void onAnimationEnd() {
                                startActivity(intent);
                            }
                        });*/
                //startActivity(intent);


                CircularAnim.hide(view)
                        .duration(300)
                        .go(new CircularAnim.OnAnimationEndListener() {
                            @Override
                            public void onAnimationEnd() {
                                CircularAnim.fullActivity(getActivity(),view)
                                        .colorOrImageRes(R.color.colorBlue)
                                        .go(new CircularAnim.OnAnimationEndListener() {
                                            @Override
                                            public void onAnimationEnd() {
                                                startActivity(intent);
                                                view.setVisibility(View.VISIBLE);
                                            }
                                        });
                            }
                        });
                //view.animate().rotationBy(90);


            }
        });
    }
    private void DelateAllDatas(){
        try{
            int size = infoBeans.size();
            for(int i = 0; i < size; i++){
                work_announcement_adapter.remove(0);
            }
            infoBeans.clear();
            work_announcement_adapter.notifyDataSetChanged();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public AnnouncementFragment() {

    }


    public static AnnouncementFragment newInstance() {
        AnnouncementFragment fragment = new AnnouncementFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_announcement, container, false);
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
