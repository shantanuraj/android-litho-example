package io.podcst.android.specs;

import android.graphics.Color;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.fresco.FrescoImage;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

import io.podcst.android.R;
import io.podcst.android.data.Podcst;

/**
 * Created by eve on 19/04/17.
 */

@LayoutSpec
public class PodcastDetailsSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop Podcst podcast) {

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(podcast.cover)
                .build();

        ComponentLayout.ContainerBuilder text = Column.create(c)
                .paddingDip(YogaEdge.ALL, 16)
                .child(
                        Text.create(c)
                                .text(podcast.title)
                                .textSizeSp(20)
                ).child(
                        Text.create(c)
                                .text(podcast.author)
                                .textSizeSp(12)
                );

        return Column.create(c)
                .backgroundColor(Color.WHITE)
                .child(
                        FrescoImage.create(c)
                                .placeholderImageRes(R.drawable.ic_placeholder_cover)
                                .controller(controller)
                ).child(
                        text
                ).build();
    }
}
