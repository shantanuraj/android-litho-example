package io.podcst.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.Text;

import io.podcst.android.data.Api;
import io.podcst.android.data.PodcstsResponse;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eve on 19/04/17.
 */

public class MainActivity extends AppCompatActivity {

    private Api.ApiService api;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = Api.get();

        final ComponentContext c = new ComponentContext(this);

        final LithoView lithoView = LithoView.create(
                this /* context */,
                Text.create(c)
                        .text("Gamezop")
                        .textSizeDip(50)
                        .build());

        setContentView(lithoView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        api.getFeaturedPodcasts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<PodcstsResponse>() {
                    @Override
                    public void onNext(PodcstsResponse value) {
                        Log.d("NEXT", value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("NEXT", "Error", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("NEXT", "Boom");
                    }
                });
    }
}
