package io.podcst.android.specs;

import android.text.Layout;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaJustify;

import io.podcst.android.R;

/**
 * Created by eve on 19/04/17.
 */

@LayoutSpec
public class LoadingSpec {

    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {

        return Column.create(c)
                .alignContent(YogaAlign.CENTER)
                .justifyContent(YogaJustify.CENTER)
                .child(
                        Text.create(c)
                                .textRes(R.string.loading)
                                .textSizeSp(20)
                                .textAlignment(Layout.Alignment.ALIGN_CENTER)
                ).build();
    }
}
