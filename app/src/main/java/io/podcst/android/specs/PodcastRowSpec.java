package io.podcst.android.specs;

import android.graphics.Color;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

/**
 * Created by eve on 19/04/17.
 */

@LayoutSpec
public class PodcastRowSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {

        return Column.create(c)
                .paddingDip(YogaEdge.ALL, 16)
                .backgroundColor(Color.WHITE)
                .child(
                        Text.create(c)
                            .text("Hello, world")
                            .textSizeSp(40)
                ).child(
                        Text.create((c))
                            .text("Litho")
                            .textSizeSp(20)
                ).build();
    }
}
