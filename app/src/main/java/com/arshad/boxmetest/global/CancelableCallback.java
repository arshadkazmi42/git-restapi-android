package com.arshad.boxmetest.global;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit.Callback;

/**
 * Created by Arshad on 01-01-2017.
 */
public abstract class CancelableCallback<T> implements Callback<T> {
    private static List<CancelableCallback> mList = new ArrayList<>();

    private boolean isCancelled = false;
    private Object mTag = null;

    public static void cancelAll() {
        Iterator<CancelableCallback> iterator = mList.iterator();
        while (iterator.hasNext()){
            iterator.next().isCancelled = true;
            iterator.remove();

        }
    }

    public static void cancel(Object tag) {
        if (tag != null) {
            Iterator<CancelableCallback> iterator = mList.iterator();
            CancelableCallback item;
            while (iterator.hasNext()) {
                item = iterator.next();
                if (tag.equals(item.mTag)) {
                    item.isCancelled = true;
                    iterator.remove();
                    Log.e("CallBack"," CallBack Cancelled "+iterator.toString());
                }
            }
        }
    }

    public CancelableCallback() {
        mList.add(this);
    }

    public CancelableCallback(Object tag) {
        mTag = tag;
        mList.add(this);
    }

    public void cancel() {
        isCancelled = true;
        mList.remove(this);
    }

    /*@Override
    public final void success(T t, Response response) {
        *//*if (!isCanceled)
            onSuccess(t, response);*//*
        mList.remove(this);
    }
    @Override
    public final void failure(Throwable error) {
        *//*if (!isCanceled)
            onFailure(error);*//*
        mList.remove(this);
    }*/

    /*public abstract void onSuccess(T t, Response response);
    public abstract void onFailure(Throwable error);*/
}
