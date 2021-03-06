package io.podcst.android.specs;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Card;

import io.podcst.android.data.Podcast;

/**
 * Created by eve on 19/04/17.
 */

@LayoutSpec
public class PodcastRowCardSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop Podcast podcast) {

        return Column.create(c)
                .child(Card.create(c)
                        .content(PodcastRow
                                .create(c)
                                .podcast(podcast)))
                .build();
    }
}
