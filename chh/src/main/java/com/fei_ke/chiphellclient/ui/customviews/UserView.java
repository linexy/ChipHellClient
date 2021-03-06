package com.fei_ke.chiphellclient.ui.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fei_ke.chiphellclient.R;
import com.fei_ke.chiphellclient.bean.User;
import com.fei_ke.chiphellclient.constant.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * 用户视图
 *
 * @author fei-ke
 * @2014-6-16
 */
@EViewGroup(R.layout.layout_user)
public class UserView extends FrameLayout {

    @ViewById(R.id.imageView_avatar)
    protected ImageView imageViewAvatar;

    @ViewById(R.id.textView_name)
    protected TextView textViewName;

    @ViewById(R.id.textView_info)
    protected TextView textViewInfo;

    @ViewById(R.id.button_favorite)
    protected TextView buttonFavorite;

    @ViewById(R.id.button_my_post)
    protected TextView buttonMyPost;

    @ViewById(R.id.main_frame)
    protected View mainFrame;

    public static UserView newInstance(Context context) {
        return UserView_.build(context);
    }

    public UserView(Context context) {
        super(context);
    }

    public UserView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UserView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void bindValue(User user) {
        ImageLoader.getInstance().displayImage(user.getAvatarUrl(), imageViewAvatar, Constants.avatarDisplayOption,
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        /*if (loadedImage != null) {
                            Bitmap bg = BitmapUtil.fastblur(loadedImage, 30);
                            mainFrame.setBackgroundDrawable(new BitmapDrawable(bg));
                        } else {
                            mainFrame.setBackgroundResource(R.drawable.card_bg_normal);
                        }*/
                    }
                });
        textViewName.setText(user.getName());
        textViewInfo.setText(Html.fromHtml(user.getInfo()));
    }

    public TextView getButtonFavorite() {
        return buttonFavorite;
    }

    public TextView getButtonMyPost() {
        return buttonMyPost;
    }
}
