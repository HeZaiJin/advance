package com.haozhang.advance.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.haozhang.advance.base.BaseFragment;
import com.haozhang.advance.rest.RESTParamsBuilder;
import com.haozhang.advance.rest.modle.ResponseListEvent;
import com.haozhang.advance.utils.CalendarUtils;
import com.haozhang.retrofit.R;
import com.haozhang.advance.rest.RESTClient;
import com.haozhang.advance.rest.modle.CustomEvent;
import com.haozhang.advance.rest.modle.RequestListEventParams;
import com.haozhang.advance.ui.adapter.ListDataAdapter;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 历史上的今天事件列表
 */
public class ListDataFragment extends BaseFragment {

    private static final String TAG = "ListDataFragment";

    private int mCurrentMonth = -1;
    private int mCurrentDay = -1;

    private ListDataAdapter mAdapter;
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
        mAdapter = new ListDataAdapter(ListDataFragment.this);
    }


    public Subscription initSubscription() {
        // 有时候params的创建涉及到数据库操作，这时候在主线程构建params就会有影响了。

        Subscription subscription = Observable.create(new Observable.OnSubscribe<RequestListEventParams>() {
            @Override
            public void call(Subscriber<? super RequestListEventParams> subscriber) {
                // 创建params
                mCurrentMonth = CalendarUtils.getCurMonth();
                mCurrentDay = CalendarUtils.getCurDay();
                RequestListEventParams params = RESTParamsBuilder.buildRequestListEventParams(mCurrentMonth, mCurrentDay);
                subscriber.onNext(params);
                subscriber.onCompleted();
                subscriber.unsubscribe();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .flatMap(new Func1<RequestListEventParams, Observable<ResponseListEvent>>() {
                    @Override
                    public Observable<ResponseListEvent> call(RequestListEventParams params) {
                        return RESTClient.queryListEvent(params);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<ResponseListEvent, List<CustomEvent>>() {
                    @Override
                    public List<CustomEvent> call(ResponseListEvent responseListEvent) {
                        CustomEvent[] result = responseListEvent.getResult();
                        return Arrays.asList(result);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<CustomEvent>>() {
                    @Override
                    public void call(List<CustomEvent> customEvents) {
                        mAdapter.refresh(customEvents);
                    }
                });
        return subscription;
    }

    @Override
    protected Subscription getSubscription() {
        return initSubscription();
    }

    @Override
    public void onResume() {
        super.onResume();
        startAsyncOperation();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
