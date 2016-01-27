package com.hezaijin.advance.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hezaijin.advance.R;
import com.hezaijin.advance.utils.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BaseFragment<T> extends Fragment {

    private static final String TAG = "BaseFragment";

    private Subscription sSubscription;

    public BaseFragment() {
        // Required empty public constructor
    }

    protected void startAsyncOperation(){
        sSubscription = getSubscription();
    }

    protected Subscription getSubscription(){
        if (null!=getObservable(null)) {
            return getObservable(null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(getSubscriber());
        }
        return  null ;
    }

    protected Observable<T> getObservable(Object obj) {
        return null;
    }

    protected Subscriber<T> getSubscriber() {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                onRxCompleted();
            }

            @Override
            public void onError(Throwable e) {
                onRxError();
                e.printStackTrace();
            }

            @Override
            public void onNext(T t) {
                onRxNext(t);
            }
        };
    }


    protected void onRxCompleted() {
        Logger.d(TAG, "onCompleted");
    }

    protected void onRxError() {
        Logger.d(TAG, "onError");

    }

    protected void onRxNext(T t) {
        Logger.d(TAG, "onNext");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != sSubscription ) {
            sSubscription.unsubscribe();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
