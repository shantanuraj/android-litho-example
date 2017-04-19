package io.podcst.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.widget.Toast;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;

import java.util.List;

import io.podcst.android.data.Api;
import io.podcst.android.data.Podcast;
import io.podcst.android.data.PodcastsResponse;
import io.podcst.android.specs.PodcastRowCard;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eve on 19/04/17.
 */

public class MainActivity extends AppCompatActivity {

    private Api.ApiService api;

    private RecyclerBinder recyclerBinder;
    private ComponentContext viewContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = Api.get();

        viewContext = new ComponentContext(this);

        recyclerBinder = new RecyclerBinder(
                viewContext,
                new LinearLayoutInfo(
                        getApplicationContext(),
                        OrientationHelper.VERTICAL,
                        false
                ));

        final Component component = Recycler.create(viewContext)
                .binder(recyclerBinder)
                .build();

        final LithoView lithoView = LithoView.create(
                this,
                component);

        setContentView(lithoView);

        loadPodcasts();
    }

    private static void populateList(RecyclerBinder recyclerBinder,
                                     ComponentContext c,
                                     List<Podcast> podcasts) {
        int i = 0;
        for (Podcast podcast : podcasts) {
            ComponentInfo.Builder componentInfoBuilder = ComponentInfo.create();
            componentInfoBuilder.component(
                    PodcastRowCard.create(c)
                        .podcast(podcast)
                        .build());

            recyclerBinder.insertItemAt(i++,  componentInfoBuilder.build());
        }
    }

    private void loadPodcasts() {
        api.getFeaturedPodcasts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<PodcastsResponse>() {
                    @Override
                    public void onNext(PodcastsResponse value) {
                        populateList(recyclerBinder, viewContext, value.data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(),
                                "Could not load podcasts",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
