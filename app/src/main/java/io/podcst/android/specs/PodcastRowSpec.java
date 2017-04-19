package io.podcst.android.specs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.fresco.FrescoImage;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

import io.podcst.android.R;
import io.podcst.android.data.Constants;
import io.podcst.android.data.Podcast;
import io.podcst.android.ui.DetailsActivity;

/**
 * Created by eve on 19/04/17.
 */

@LayoutSpec
public class PodcastRowSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c, @Prop Podcast podcast) {

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(podcast.thumbnail)
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

        return Row.create(c)
                .backgroundColor(Color.WHITE)
                .clickHandler(PodcastRow.onClick(c))
                .child(
                        FrescoImage.create(c)
                                .placeholderImageRes(R.drawable.ic_placeholder_cover)
                                .controller(controller)
                                .withLayout()
                                .widthDip(100)
                                .heightDip(100)
                ).child(
                        text
                ).build();
    }

    @OnEvent(ClickEvent.class)
    static void onClick(
            ComponentContext c,
            @FromEvent View view,
            @Prop Podcast podcast
    ) {
        Intent intent = new Intent(c.getApplicationContext(), DetailsActivity.class);
        intent.putExtra(Constants.DETAILS_BUNDLE, podcast);
        c.startActivity(intent);
    }
}
