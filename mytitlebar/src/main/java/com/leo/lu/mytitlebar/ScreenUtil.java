package com.leo.lu.mytitlebar;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class ScreenUtil {

    /*屏幕的高度*/
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
        return px2dp(activity, heightPixels);
    }

    /*屏幕的高度*/
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        return px2dp(activity, widthPixels);
    }


    /*顶部导航栏*/
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourcesId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = resources.getDimensionPixelSize(resourcesId);
        return px2dp(context, dimensionPixelSize);
    }

    /*底部导航栏*/
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return px2dp(context, height);
    }

    /*view 的高度*/
    public static int getViewHeight(View view, Context context) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return px2dp(context, view.getHeight());
    }

    public static int getTitleBarHeight(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
            return px2dp(context, actionBarHeight);
        }
        return 0;
    }

    /**
     * @param context context
     * @param pxValue pxValue
     * @return pxValue
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param context context
     * @param px      px
     * @return px
     */
    public static int dpFromPx(Context context, float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }
}
