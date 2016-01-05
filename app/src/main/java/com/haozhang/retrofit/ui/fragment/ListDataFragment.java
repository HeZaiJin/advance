package com.haozhang.retrofit.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.haozhang.retrofit.R;
import com.haozhang.retrofit.reset.modle.CustomEvent;
import com.haozhang.retrofit.reset.RESETClient;
import com.haozhang.retrofit.reset.RESETParamsBuilder;
import com.haozhang.retrofit.reset.modle.RequestListEventParams;
import com.haozhang.retrofit.reset.modle.ResponseListEvent;
import com.haozhang.retrofit.ui.adapter.ListAdapter;
import com.haozhang.retrofit.utils.CalendarUtils;

import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * 历史上的今天事件列表
 */
public class ListDataFragment extends BaseFragment {

    private static final String TAG = "ListDataFragment";

    private int mCurrentMonth = -1;
    private int mCurrentDay = -1;

    private ListAdapter mAdapter;
    private ListView mListView;

    public ListDataFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_info_layout, null, false);
        mListView = (ListView) view.findViewById(R.id.list_view);
        mListView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        init(context);
    }


    public void init(Context context) {
        mAdapter = new ListAdapter();
        mCurrentMonth = CalendarUtils.getCurMonth();
        mCurrentDay = CalendarUtils.getCurDay();
        // 创建params
        RequestListEventParams params = RESETParamsBuilder.buildRequestListEventParams(mCurrentMonth, mCurrentDay);
        // 建立请求事件
        RESETClient.qureyListEvent(params, new Callback<ResponseListEvent>() {
            @Override
            public void onResponse(Response<ResponseListEvent> response, Retrofit retrofit) {
                Log.d(TAG, "onResponse() called with: " + "response = [" + response.body());
                CustomEvent[] result = response.body().getResult();
                List<CustomEvent> infos = Arrays.asList(result);
                mAdapter.refresh(infos);
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
