package io.podcst.android.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;

import java.util.ArrayList;
import java.util.List;

import io.podcst.android.data.Api;
import io.podcst.android.data.Podcast;
import io.podcst.android.specs.Loading;
import io.podcst.android.specs.PodcastList;

public class MainFragment extends Fragment {

    private Api.ApiService api;
    private ComponentContext viewContext;

    private static final String PODCASTS_CATEGORY = "podcastsCategory";
    private static final String PODCAST_LIST = "podcastList";

    private String podcastsCategory;
    private List<Podcast> podcasts = null;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String podcastsCategory, ArrayList<Podcast> podcasts) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(PODCASTS_CATEGORY, podcastsCategory);
        if (podcasts != null) {
            args.putParcelableArrayList(PODCAST_LIST, podcasts);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            podcastsCategory = getArguments().getString(PODCASTS_CATEGORY);
            podcasts = getArguments().getParcelableArrayList(PODCAST_LIST);
        }

        api = Api.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewContext = new ComponentContext(getContext());

        if (podcasts == null) {
            return LithoView.create(
                    getContext(),
                    Loading.create(viewContext)
                            .build());
        }

        return LithoView.create(
                getContext(),
                PodcastList.create(viewContext)
                        .podcasts(podcasts)
                        .build());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    interface OnFragmentInteractionListener {
        void onPodcastsLoaded(List<Podcast> podcasts);
    }
}
