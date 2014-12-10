
package com.fei_ke.chiphellclient.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import com.fei_ke.chiphellclient.R;
import com.fei_ke.chiphellclient.bean.Thread;
import com.fei_ke.chiphellclient.ui.customviews.ThreadItemView;

import java.util.LinkedList;
import java.util.List;

/**
 * 帖子列表适配器
 *
 * @author fei-ke
 * @2014-6-15
 */
public class ThreadListAdapter extends BaseAdapter {
    private List<Thread> mThreads;
    private OnClickListener onFastReplylistener;
    private int lastPosition = -1;
    private int animToPosition = -1;

    @Override
    public int getCount() {
        return mThreads == null ? 0 : mThreads.size();
    }

    @Override
    public Thread getItem(int position) {
        return mThreads.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ThreadItemView threadItemView = null;
        if (convertView == null) {
            threadItemView = ThreadItemView.getInstance(parent.getContext());
        } else {
            threadItemView = (ThreadItemView) convertView;
        }
        Thread thread = getItem(position);
        threadItemView.bindValue(thread);
        if (onFastReplylistener != null) {
            threadItemView.getTextViewCount().setTag(thread);
            threadItemView.setOnFastReplyClickListener(onFastReplylistener);
        }
        if (position > lastPosition && position <= animToPosition) {
            Animation animation = AnimationUtils.loadAnimation(parent.getContext(), R.anim.slide_in_from_bottom);
            animation.setDuration(600);
//            threadItemView.startAnimation(animation);
            // AnimatorSet animator = (AnimatorSet)
            // AnimatorInflater.loadAnimator(context,R.animator.rotate_animation);
            Animator animator = AnimatorInflater.loadAnimator(parent.getContext(),
                    R.animator.rotate_animation);

            animator.setStartDelay(position * 80);
            animator.setTarget(threadItemView);
            animator.start();
            lastPosition = position;
        }
        return threadItemView;
    }

    public OnClickListener getOnFastReplylistener() {
        return onFastReplylistener;
    }

    public void setOnFastReplylistener(OnClickListener onFastReplylistener) {
        this.onFastReplylistener = onFastReplylistener;
    }

    public void update(List<Thread> newThreads) {
        update(newThreads, -1);
    }

    public void update(List<Thread> newThreads, int animToPosition) {
        // if (mThreads == null) {
        // mThreads = new ArrayList<Thread>();
        // }/*
        // * else {
        // * mThreads.clear();
        // * }
        // */
        // mThreads.addAll(newThreads);
        // notifyDataSetChanged();
        this.animToPosition = animToPosition;
        this.lastPosition = -1;

        if (mThreads == null) {
            mThreads = new LinkedList<Thread>();
        }
        if (mThreads.size() == 0) {
            mThreads.addAll(newThreads);
            notifyDataSetChanged();
            return;
        }
        int oldSize = getCount();

        // i是老的，j是新的
        for (int i = 0, j = 0; j < newThreads.size(); i++) {
            Thread newThread = newThreads.get(j);
            if (i < mThreads.size()) {
                Thread oldThread = mThreads.get(i);
                if (oldThread.getTid().equals(newThread.getTid())) {
                    mThreads.remove(i);
                    mThreads.add(i, newThread);
                    j++;
                }
            } else {
                mThreads.add(newThread);
                j++;
            }

        }
        notifyDataSetChanged();
    }

    public void clear() {
        if (mThreads != null) {
            mThreads.clear();
            lastPosition = -1;
            animToPosition = -1;
        }
    }

}
