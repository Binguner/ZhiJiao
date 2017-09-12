package com.example.binguner.zhijiao.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Adapter.WorkInfo_Adapter;
import com.example.binguner.zhijiao.CallBack.CallBackSuccedLogin;
import com.example.binguner.zhijiao.Entity.WorkBean;
import com.example.binguner.zhijiao.CallBack.CallBackType;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;
import com.example.binguner.zhijiao.UI.DetialAty;
import com.example.binguner.zhijiao.UI.MainActivity;
import com.example.binguner.zhijiao.Utils.CircularAnim;
import com.example.binguner.zhijiao.View.WaveView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WorkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WorkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkFragment extends Fragment {

    private SwipeRefreshLayout work_swiperefreshlayout;
    private TextView work_type_name;
    private RecyclerView work_recyclerview;
    private OnFragmentInteractionListener mListener;
    private LinearLayoutManager linearLayoutManager;
    private WorkInfo_Adapter workInfo_adapter;
    private static List<WorkBean.InfoBean> infoBeans = new ArrayList<>();
    private TYUTUtils tyutUtils;
    private int lastItemPosition;
    private static int page = 1;
    private static int type = 1;
    private static int isFirstLoad = 1;

    //private MainActivity mainActivity = new MainActivity();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initId();
        initViews();
        setListener();
    }

    /*
    * 1 固定岗位
    * 2 临时岗位
    * 3 专业技术岗位
    * 4 校外冈
    * */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    DeleteAllDatas();
                    page = 1;
                    work_type_name.setText("固定岗位");
                    //Snackbar.make(getView(),"1",Snackbar.LENGTH_SHORT).show();
                    tyutUtils.getWorkInfo(1,page);

                    break;
                case 2:
                    DeleteAllDatas();
                    page = 1;
                    work_type_name.setText("临时岗位");
                    //Snackbar.make(getView(),"2",Snackbar.LENGTH_SHORT).show();
                    tyutUtils.getWorkInfo(2,page);
                    DeleteAllDatas();
                    break;
                case 3:
                    DeleteAllDatas();
                    page = 1;
                    work_type_name.setText("专业技术岗位");
                    tyutUtils.getWorkInfo(3,page);
                    //Snackbar.make(getView(),"3",Snackbar.LENGTH_SHORT).show();

                    break;
                case 4:
                    DeleteAllDatas();
                    page = 1;
                    work_type_name.setText("校外岗位");
                    tyutUtils.getWorkInfo(4,page);
                    //Snackbar.make(getView(),"4",Snackbar.LENGTH_SHORT).show();

                    break;
                default:
                    break;
            }
        }
    };

    private void setType(int type){
        this.type = type;
    }
    private void setListener() {
        workInfo_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, final View view, int position) {


                if(infoBeans.get(position).getUrl().isEmpty()||infoBeans.get(position).getApplyStatus().contains("截止")){
                    Snackbar.make(getView(), "此招聘已截止", Snackbar.LENGTH_SHORT).show();
                }
                final Intent intent = new Intent(getContext(), DetialAty.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",infoBeans.get(position).getUrl());
                intent.putExtras(bundle);
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

            }
        });

        MainActivity.setCallBack(new CallBackType() {
            @Override
            public void CallBackWorkType(int type) {
                //Snackbar.make(getView(), "The type is " + type, Snackbar.LENGTH_SHORT).show();
                setType(type);
                Message message = Message.obtain();
                message.what = type;
                handler.sendMessage(message);
            }
        });

        work_swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                work_swiperefreshlayout.setRefreshing(true);
                isFirstLoad = 1;
                page = 1;
                DeleteAllDatas();
                work_swiperefreshlayout.setRefreshing(false);
                //LoadDatas();
                tyutUtils.setCallBack(null, null, new CallBackSuccedLogin() {
                    @Override
                    public void callBackLoginStats(int stats) {
                        if(stats == 1){
                            Snackbar.make(getView(), "刷新完毕", Snackbar.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        work_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition + 3 >= linearLayoutManager.getItemCount() /*&& infoBeans.size()>2*/) {
                    try {
                          //tyutUtils.getWorkInfo(type,++page);
                          LoadDatas();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Snackbar.make(getView(), "加载失败。。。", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });



    }

    private void DeleteAllDatas() {
        try {

            //workInfo_adapter.notifyItemRangeRemoved(0,infoBeans.size()-1);
            //workInfo_adapter.removeAllHeaderView();
            /*while(infoBeans.size()>0){
                infoBeans.remove(infoBeans.get(0));
                workInfo_adapter.notifyItemRemoved(0);
                workInfo_adapter.remove(0);

                //workInfo_adapter.removeHeaderView(getView());
            }*/
            int size = infoBeans.size();
            Log.d("WorkFre","一共有："+size+"个");
            for(int i =0;i<size;i++){
                //infoBeans.remove(i);
                //删除 Adapter 中的数据
                workInfo_adapter.remove(0);
                //Log.d("WorkFre","还有： "+infoBeans.size()+" 个");
                //workInfo_adapt
            }
            //删除 Fragment 中 list 中的数据
            infoBeans.clear();
            workInfo_adapter.notifyDataSetChanged();
            //workInfo_adapter.clearAdapterDatas();
            //workInfo_adapter.removeAllDatas();
            //workInfo_adapter.notifyDataSetChanged();
            //work_recyclerview.removeAllViews();
           // Log.d("WorkFre", infoBeans.size() + "");
           // workInfo_adapter.notifyDataSetChanged();
            //workInfo_adapter.notifyItemRangeRemoved(0, infoBeans.size());

            //infoBeans.clear();

            //列表批量删除的动画效果
           // workInfo_adapter.notifyItemInserted(infoBeans.size());
        } catch (Exception e) {
            Log.d("WorkFre", e.toString());
        }

    }

    private void initViews() {

        linearLayoutManager = new LinearLayoutManager(getContext());
        work_recyclerview.setLayoutManager(linearLayoutManager);
        work_recyclerview.setHasFixedSize(true);
        work_recyclerview.setAdapter(workInfo_adapter);
        work_swiperefreshlayout.setColorSchemeColors(getResources().getColor(R.color.colorRed), getResources().getColor(R.color.colorYellow), getResources().getColor(R.color.colorBlue), getResources().getColor(R.color.colorGreen));
        View view = getLayoutInflater().inflate(R.layout.activity_footer_view,null);
        workInfo_adapter.addFooterView(view);
        workInfo_adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        workInfo_adapter.isFirstOnly(true);
        //第一次加载数据
        /*try {
            tyutUtils.getWorkInfo(type,page);
        } catch (Exception e) {
            e.printStackTrace();
            //Snackbar.make(getView(), "加载失败，请检查网络。", Snackbar.LENGTH_SHORT).show();
        }*/
        LoadDatas();


    }
    /**
     * 初次进入页面，上拉加载，并下拉刷新
     * 更换 Type，page = 1，上拉加载，下拉刷新
     * */

    private void LoadDatas(){
        if(isFirstLoad == 1) {
            tyutUtils.getWorkInfo(type, page);
            isFirstLoad = 0;
        }else if(isFirstLoad == 0){
            tyutUtils.getWorkInfo(type,page);
        }
        tyutUtils.setCallBack(null, null, new CallBackSuccedLogin() {
            @Override
            public void callBackLoginStats(int stats) {
                if(stats == 1){
                    page+=1;
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
        Log.d("pageTag",page+"");
    }

    private void initId() {
        work_type_name = getActivity().findViewById(R.id.work_type_name);
        workInfo_adapter = new WorkInfo_Adapter(R.layout.card_layout_work_info, infoBeans, getContext());
        work_swiperefreshlayout = getActivity().findViewById(R.id.work_swiperefreshlayout);
        work_recyclerview = getActivity().findViewById(R.id.work_recyclerview);
        tyutUtils = new TYUTUtils(workInfo_adapter,getContext());
    }

    public static void addWorkDatas(List<WorkBean.InfoBean> minfoBeans) {
        Log.d("whasdad", "hewe");
        infoBeans.addAll(minfoBeans);
        Log.d("whasdad", infoBeans.size() + "");
    }

    public static int getSize() {
        return infoBeans.size();
    }

    public WorkFragment() {

    }

    public static WorkFragment newInstance() {
        WorkFragment fragment = new WorkFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_work, container, false);
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

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
