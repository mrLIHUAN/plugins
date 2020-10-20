package io.flutter.plugins.webviewflutter.utils;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ResultFragment extends Fragment {
    private ResultCallBack resultCallBack;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCallBack != null) {
            resultCallBack.onResult(requestCode, resultCode, data);
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(this).commitNow();
    }

    public void setResultCallBack(ResultCallBack resultCallBack) {
        this.resultCallBack = resultCallBack;
    }

    public interface ResultCallBack {
        void onResult(int requestCode, int resultCode, Intent data);
    }
}
