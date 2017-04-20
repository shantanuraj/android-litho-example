package io.podcst.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;

import io.podcst.android.specs.Loading;

/**
 * Created by eve on 21/04/17.
 */

public class LoadingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ComponentContext viewContext = new ComponentContext(getContext());
        return LithoView.create(
                getContext(),
                Loading.create(viewContext)
                        .build());
    }
}
