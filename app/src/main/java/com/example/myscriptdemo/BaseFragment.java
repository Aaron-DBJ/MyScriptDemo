package com.example.myscriptdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.function.BinaryOperator;

public abstract class BaseFragment extends Fragment {
    // 视图加载完成标志位
    private boolean isViewCreated;
    // 视图对用户可见标志位
    private boolean isVisible;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(getFragmentView(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // fragment视图加载完成，设置视图加载完成标志位
        isViewCreated = true;
        // 可以开启懒加载
        lazyLoad();
    }

    // 该方法是在onCreate方法之前调用的
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            isVisible = true;
            lazyLoad();
        }else {
            isVisible = false;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // fragment视图销毁时也重置标志位
        isVisible = false;
        isViewCreated = false;

    }

    private void lazyLoad(){
        /**
         * 牢记懒加载的2个重要条件：
         * 1、fragment视图已经加载完成
         * 2、fragment视图对用户可见了
         *
         * 所以，根据2个标志位进行判断
         */
        if (isViewCreated && isVisible) {
            loadData();
            // 数据加载后重置标志位，避免重复加载
            isVisible = false;
            isViewCreated = false;
        }
    }

    protected abstract void loadData();
    protected abstract int getFragmentView();
}
