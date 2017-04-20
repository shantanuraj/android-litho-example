package io.podcst.android.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MainFragment extends Fragment {

    private Api.ApiService api;
    private RecyclerBinder recyclerBinder;
    private ComponentContext viewContext;

    private static final String PODCASTS_CATEGORY = "podcastsCategory";

    private String podcastsCategory;
    private List<Podcast> podcasts;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String podcastsCategory) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(PODCASTS_CATEGORY, podcastsCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            podcastsCategory = getArguments().getString(PODCASTS_CATEGORY);
        }

        api = Api.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewContext = new ComponentContext(getContext());

        recyclerBinder = new RecyclerBinder(
                viewContext,
                new LinearLayoutInfo(
                        getContext(),
                        OrientationHelper.VERTICAL,
                        false
                ));

        final Component component = Recycler.create(viewContext)
                .binder(recyclerBinder)
                .build();

        final LithoView lithoView = LithoView.create(
                getContext(),
                component);

        return lithoView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPodcasts();
    }

    private void loadPodcasts() {
        api.getPodcasts(podcastsCategory)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<PodcastsResponse>() {
                    @Override
                    public void onNext(PodcastsResponse value) {
                        populateList(recyclerBinder, viewContext, value.data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(),
                                "Could not load podcasts",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
