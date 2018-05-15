package com.example.rz.apptesttool.tools;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by rz on 4/25/18.
 */

public class FragmentObjectHolder<T> extends Fragment {

    private T content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public T getContent() {
        return content;
    }

    public FragmentObjectHolder setContent(T content) {
        this.content = content;
        return this;
    }
}
