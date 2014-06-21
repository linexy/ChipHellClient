
package com.fei_ke.chiphellclient.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.fei_ke.chiphellclient.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Activity基类
 * 
 * @author 杨金阳
 * @2014-6-14
 */
@EActivity
public abstract class BaseActivity extends FragmentActivity {
    protected MenuItem menuItemRefresh;
    boolean mIsRefreshing = true;;

    /**
     * 切勿调用和复写此方法
     */
    @AfterViews
    protected void onPrivateAfterViews() {
        onAfterViews();
    }

    /**
     * 此方法在onCreate之后调用,勿在此方法上添加@AfterViews注解
     */
    protected abstract void onAfterViews();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if (Build.VERSION.SDK_INT >= 19) {// 设置状态栏
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setNavigationBarAlpha(0);
            // tintManager.setNavigationBarTintResource(R.color.chh_red);
            tintManager.setStatusBarTintResource(R.color.chh_red);
        }
        initActionBar();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuItemRefresh = menu.findItem(R.id.action_refresh);
        if (menuItemRefresh != null && mIsRefreshing) {
            menuItemRefresh.setActionView(R.layout.indeterminate_progress_action);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 开始刷新
     */
    public void onStartRefresh() {
        mIsRefreshing = true;

        if (menuItemRefresh != null) {
            menuItemRefresh.setActionView(R.layout.indeterminate_progress_action);
        }
    }

    /**
     * 刷新结束
     */
    public void onEndRefresh() {
        mIsRefreshing = false;

        if (menuItemRefresh != null) {
            menuItemRefresh.setActionView(null);
            menuItemRefresh.setIcon(R.drawable.white_ptr_rotate);
        }
    }

    private void initActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
        // win.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        win.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
}
