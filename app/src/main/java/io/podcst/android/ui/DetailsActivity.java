package io.podcst.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;

import io.podcst.android.data.Constants;
import io.podcst.android.data.Podcast;
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

        final Podcast podcast = bundle.getParcelable(Constants.DETAILS_BUNDLE);

        final RecyclerBinder recyclerBinder = new RecyclerBinder(
                viewContext,
                new LinearLayoutInfo(
                        getApplicationContext(),
                        OrientationHelper.VERTICAL,
                        false
                ));

        final Component view = Recycler.create(viewContext)
                .binder(recyclerBinder)
                .build();

        recyclerBinder.insertItemAt(0, PodcastDetails
                .create(viewContext)
                .podcast(podcast)
                .build());

        final LithoView lithoView = LithoView.create(
                this,
                view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setContentView(lithoView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
