package com.example.myscriptdemo.common;

import android.os.Build;

import androidx.annotation.Nullable;

import com.example.myscriptdemo.BuildConfig;

import java.util.HashMap;

public class SoleKeyHashMap<K, V> extends HashMap<K, V> {
    @Nullable
    @Override
    public V put(K key, V value) {
        // containsKey的时间复杂度O(1)
        if (containsKey(key) && BuildConfig.DEBUG) {
            throw new IllegalArgumentException("the same key is existed");
        }
        // todo
        // release环境可以上报或做其他处理
        return super.put(key, value);
    }
}
