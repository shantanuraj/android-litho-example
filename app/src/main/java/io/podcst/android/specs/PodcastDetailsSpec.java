package io.podcst.android.specs;

import android.graphics.Color;
import android.text.style.TtsSpan;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.fresco.FrescoImage;
import com.facebook.litho.widget.Card;
import com.facebook.yoga.YogaEdge;

import io.podcst.android.R;
import io.podcst.android.data.Podcast;

/**
 * Created by eve on 19/04/17.
 */

@LayoutSpec
public class PodcastDetailsSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop Podcast podcast) {

        final DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(podcast.cover)
                .build();

        final Card.Builder titleBar = Card.create(c)
                .content(PodcastTitleBar.create(c)
                        .title(podcast.title)
                        .author(podcast.author)
                );

        final Card.Builder description = Card.create(c)
                .content(PodcastDescription.create(c)
                        .description(podcast.description)
                );

        final Card.Builder image = Card.create(c)
                .content(FrescoImage.create(c)
                        .placeholderImageRes(R.drawable.ic_placeholder_cover)
                        .placeholderImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                        .controller(controller));

        final ComponentLayout.ContainerBuilder info = Column.create(c)
                .paddingDip(YogaEdge.ALL, 16)
                .child(titleBar)
                .child(description);

        return Column.create(c)
                .backgroundColor(Color.WHITE)
                .child(image)
                .child(info)
                .build();
    }
}
