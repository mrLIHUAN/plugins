package io.flutter.plugins.webviewflutter.utils;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

import io.flutter.plugins.webviewflutter.utils.ResultFragment.ResultCallBack;

public class ActivityUtils {
    public static void startActivityForResult(Context context, Intent intent, ResultCallBack resultCallback) {
        ResultFragment resultFragment = new ResultFragment();
        resultFragment.setResultCallBack(resultCallback);

        if (context instanceof FragmentActivity) {
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().add(resultFragment, null).commitNow();
            resultFragment.startActivityForResult(intent, 10000);
        }
    }
}
