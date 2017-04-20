package io.podcst.android.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.podcst.android.R;
import io.podcst.android.data.Constants;

/**
 * Created by eve on 19/04/17.
 */

public class NuMainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String podcastsCategory = null;
            switch (item.getItemId()) {
                case R.id.navigation_popular:
                    podcastsCategory = Constants.POPULAR; break;
                case R.id.navigation_featured:
                    podcastsCategory = Constants.FEATURED; break;
                case R.id.navigation_trending:
                    podcastsCategory = Constants.TRENDING; break;
            }
            if (podcastsCategory != null) {
                updateFragment(podcastsCategory);
            }
            return true;
        }

    };

    private void updateFragment(final String podcastsCategory) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, MainFragment.newInstance(podcastsCategory))
                .commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        updateFragment(Constants.FEATURED);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
