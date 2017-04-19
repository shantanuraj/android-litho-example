package io.podcst.android.specs;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

/**
 * Created by eve on 19/04/17.
 */

@LayoutSpec
public class PodcastDescriptionSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c,
                                          @Prop String description) {

        Spanned desc;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            desc = Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY);
        } else {
            desc = Html.fromHtml(description);
        }

        return Column.create(c)
                .paddingDip(YogaEdge.ALL, 16)
                .child(
                        Text.create(c)
                                .text(desc)
                                .textSizeSp(16)
                ).build();
    }
}
