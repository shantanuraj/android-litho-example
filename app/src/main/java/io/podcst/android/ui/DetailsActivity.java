package io.podcst.android.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;

import io.podcst.android.data.Constants;
import io.podcst.android.data.Podcst;
import io.podcst.android.specs.PodcastDetails;

/**
 * Created by eve on 19/04/17.
 */

public class DetailsActivity extends AppCompatActivity {

    private ComponentContext viewContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewContext = new ComponentContext(this);

        final Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        final Podcst podcast = bundle.getParcelable(Constants.DETAILS_BUNDLE);

        final Component view = PodcastDetails.create(viewContext)
                .podcast(podcast)
                .build();

        final LithoView lithoView = LithoView.create(
                this,
                view);

        setContentView(lithoView);
    }
}
