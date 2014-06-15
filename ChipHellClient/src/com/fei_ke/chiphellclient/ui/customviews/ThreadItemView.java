
package com.fei_ke.chiphellclient.ui.customviews;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fei_ke.chiphellclient.R;
import com.fei_ke.chiphellclient.bean.Thread;
import com.fei_ke.chiphellclient.constant.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.layout_thread_item)
public class ThreadItemView extends FrameLayout {
    @ViewById(R.id.textView_title)
    TextView textViewTitle;

    @ViewById(R.id.textView_count)
    TextView textViewCount;

    @ViewById(R.id.textView_by)
    TextView textViewBy;

    @ViewById(R.id.textView_date)
    TextView textViewDate;

    @ViewById(R.id.imageView_icon)
    ImageView imageViewIcon;

    public static ThreadItemView getInstance(Context context) {
        return ThreadItemView_.build(context);
    }

    public ThreadItemView(Context context) {
        super(context);
    }

    public void bindValue(Thread thread) {
        textViewTitle.setText(thread.getTitle());
        textViewCount.setText(thread.getCount());
        textViewBy.setText(thread.getBy());
        textViewDate.setText(thread.getDate());
        String imgSrc = thread.getImgSrc();
        if (TextUtils.isEmpty(imgSrc)) {
            imageViewIcon.setVisibility(GONE);
        } else {
            imageViewIcon.setVisibility(VISIBLE);
            ImageLoader.getInstance().displayImage(Constants.BASE_URL + imgSrc, imageViewIcon);
        }
        if (thread.getTitleColor() != 0) {
            textViewTitle.setTextColor(thread.getTitleColor());
        } else {
            textViewTitle.setTextColor(Color.BLACK);
        }
    }
}
