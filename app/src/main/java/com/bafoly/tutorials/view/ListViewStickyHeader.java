package com.bafoly.tutorials.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bafoly.tutorials.R;

public class ListViewStickyHeader extends ListView {

    public ListViewStickyHeader(Context context) {
        super(context);
        init();
    }

    public ListViewStickyHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ListViewStickyHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    int mScrollState = OnScrollListener.SCROLL_STATE_IDLE;
    private void init(){
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                mScrollState = i;
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(mScrollState==OnScrollListener.SCROLL_STATE_IDLE){
                    return;
                }
                View listItem = absListView.getChildAt(0);
                if (listItem == null)
                    return;

                LinearLayout header = (LinearLayout) listItem.findViewById(R.id.header);
                int topMargin = 0;
                int top = listItem.getTop();
                int height = listItem.getHeight();
                if (top < 0)
                    topMargin = header.getHeight() < (top + height) ? -top : (height - header.getHeight());

                header.setTranslationY(topMargin);

            }
        });
    }



}
