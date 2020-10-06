package com.leo.lu.mytitlebar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;


/**
 * Created by LeoLu on 2016/9/12.
 */

public class MyTitleBar extends RelativeLayout {

    private static final String TAG = "MyTitleBar";

    private CharSequence mTitleText, mLeftText, mRightText, mSearchHintText;

    private TextView mTitleTextView, mLeftTextView, mRightTextView, mRightButtonViewBadge;

    private ImageButton mNavButtonView, mRightButtonView;

    private CheckBox checkBox;

    private Drawable mRightCheckBoxButton, searchDrawable, mRightButtonViewBadgeBg;

    private ImageButton logoView;

    private View mCustomView, mRightCustomView;

    private int mCustomViewId;

    private EditText mSearchView;

    private int mTitleTextColor, mLeftTextColor, mRightTextColor, mSearchHintTextColor;

    private float mTitleSize, mLeftTextSize, mRightTextSize;

    private int textSize = 14;

    private int mRightTextMarginRight, mLeftTextMarginLeft, mCustomViewMarginRight, mCustomViewMarginLeft;

    private int mLeftTextDrawablePadding, mRightTextDrawablePadding, mTitleTextDrawablePadding;

    private boolean mStatueBarIsTransparent;

    private int mCustomViewGravity;

    private LinearLayout statueBar;

    private LinearLayout titleWrap;

    private RelativeLayout titleView;

    private int titleViewHeight;

    private int titleViewHeightMarginTop;

    private int statueHeight = 0;

    private CharSequence title;

    public MyTitleBar(Context context) {
        this(context, null);
    }

    public MyTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTitleBar(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyTitleBar, defStyleAttr, 0);
        mRightTextMarginRight = a.getDimensionPixelSize(R.styleable.MyTitleBar_mRightTextMarginRight, pxFromDp(15));
        mLeftTextMarginLeft = a.getDimensionPixelSize(R.styleable.MyTitleBar_mLeftTextMarginLeft, pxFromDp(15));
        mCustomViewMarginRight = a.getDimensionPixelSize(R.styleable.MyTitleBar_mCustomViewMarginRight, pxFromDp(40));
        mCustomViewMarginLeft = a.getDimensionPixelSize(R.styleable.MyTitleBar_mCustomViewMarginLeft, pxFromDp(40));
        mStatueBarIsTransparent = a.getBoolean(R.styleable.MyTitleBar_mStatueBarIsTransparent, false);
        titleViewHeight = a.getLayoutDimension(R.styleable.MyTitleBar_android_layout_height, -2);
        titleViewHeightMarginTop = a.getDimensionPixelSize(R.styleable.MyTitleBar_mHeightMarginTop, pxFromDp(0));
        if (titleViewHeight == -2) {
            if (a.hasValue(R.styleable.MyTitleBar_mHeight)) {
                titleViewHeight = a.getLayoutDimension(R.styleable.MyTitleBar_mHeight, pxFromDp(50));
            } else {
                titleViewHeight = pxFromDp(50);
            }
        }
        if (mStatueBarIsTransparent) {
            statueHeight = getStatueBarHeight();
        }
        setLayout();
        final Drawable rightButtonView = a.getDrawable(R.styleable.MyTitleBar_mRightButtonIcon);
        if (rightButtonView != null) {
            setRightButtonIcon(rightButtonView);
        }

        final CharSequence mRightText = a.getText(R.styleable.MyTitleBar_mRightText);
        if (!TextUtils.isEmpty(mRightText)) {
            setRightText(mRightText);
        }


        mRightTextDrawablePadding = a.getDimensionPixelSize(R.styleable.MyTitleBar_mRightTextDrawablePadding, pxFromDp(5));
        final Drawable mRightTextRightDrawable = a.getDrawable(R.styleable.MyTitleBar_mRightTextRightDrawable);
        if (mRightTextRightDrawable != null) {
            mRightTextRightDrawable.setBounds(0, 0, mRightTextRightDrawable.getMinimumWidth(), mRightTextRightDrawable.getMinimumHeight());
            mRightTextView.setCompoundDrawablePadding(mRightTextDrawablePadding);
            mRightTextView.setCompoundDrawables(null, null, mRightTextRightDrawable, null);
        }
        final Drawable mRightTextLeftDrawable = a.getDrawable(R.styleable.MyTitleBar_mRightTextLeftDrawable);
        if (mRightTextLeftDrawable != null) {
            mRightTextLeftDrawable.setBounds(0, 0, mRightTextLeftDrawable.getMinimumWidth(), mRightTextLeftDrawable.getMinimumHeight());
            mRightTextView.setCompoundDrawablePadding(mRightTextDrawablePadding);
            mRightTextView.setCompoundDrawables(mRightTextLeftDrawable, null, null, null);
        }

        if (a.hasValue(R.styleable.MyTitleBar_mRightTextColor)) {
            setRightTextColor(a.getColor(R.styleable.MyTitleBar_mRightTextColor, 0xffffffff));
        }

        if (a.hasValue(R.styleable.MyTitleBar_mRightTextSize)) {
            setRightTextSize(a.getDimension(R.styleable.MyTitleBar_mRightTextSize, textSize));
        }

        title = a.getText(R.styleable.MyTitleBar_mTitle);
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        if (a.hasValue(R.styleable.MyTitleBar_mTitleTextColor)) {
            setTitleTextColor(a.getColor(R.styleable.MyTitleBar_mTitleTextColor, 0xffffffff));
        }
        if (a.hasValue(R.styleable.MyTitleBar_mTitleSize)) {
            setTitleSize(a.getDimension(R.styleable.MyTitleBar_mTitleSize, 16));
        }

        mTitleTextDrawablePadding = a.getDimensionPixelSize(R.styleable.MyTitleBar_mTitleTextDrawablePadding, pxFromDp(5));
        final Drawable mTitleTextRightDrawable = a.getDrawable(R.styleable.MyTitleBar_mTitleTextRightDrawable);
        if (mTitleTextRightDrawable != null) {
            mTitleTextRightDrawable.setBounds(0, 0, mTitleTextRightDrawable.getMinimumWidth(), mTitleTextRightDrawable.getMinimumHeight());
            mTitleTextView.setCompoundDrawablePadding(mTitleTextDrawablePadding);
            mTitleTextView.setCompoundDrawables(null, null, mTitleTextRightDrawable, null);
        }

        final CharSequence searchHintText = a.getText(R.styleable.MyTitleBar_mSearchHintText);
        if (!TextUtils.isEmpty(searchHintText)) {
            setSearchText(searchHintText);
        }

        final Drawable searchDrawableLeft = a.getDrawable(R.styleable.MyTitleBar_mSearchViewDrawableLeft);
        if (searchDrawableLeft != null) {
            setSearchDrawableLeft(searchDrawableLeft);
        }

        final Drawable searchDrawableRight = a.getDrawable(R.styleable.MyTitleBar_mSearchViewDrawableRight);
        if (searchDrawableRight != null) {
            setSearchDrawableRight(searchDrawableRight);
        }

        if (a.hasValue(R.styleable.MyTitleBar_mSearchHintTextColor)) {
            setSearchHintTextColor(a.getColor(R.styleable.MyTitleBar_mSearchHintTextColor, 0xffffffff));
        }

        final Drawable navIcon = a.getDrawable(R.styleable.MyTitleBar_mNavButtonIcon);
        if (navIcon != null) {
            setNavButtonIcon(navIcon);
        }

        final CharSequence mLeftText = a.getText(R.styleable.MyTitleBar_mLeftText);
        if (!TextUtils.isEmpty(mLeftText)) {
            setLeftText(mLeftText);
        }
        if (a.hasValue(R.styleable.MyTitleBar_mLeftTextColor)) {
            setLeftTextColor(a.getColor(R.styleable.MyTitleBar_mLeftTextColor, 0xffffffff));
        }

        if (a.hasValue(R.styleable.MyTitleBar_mLeftTextSize)) {
            setLeftTextSize(a.getDimension(R.styleable.MyTitleBar_mLeftTextSize, textSize));
        }

        mLeftTextDrawablePadding = a.getDimensionPixelSize(R.styleable.MyTitleBar_mLeftTextDrawablePadding, pxFromDp(5));

        final Drawable leftTextRightDrawable = a.getDrawable(R.styleable.MyTitleBar_mLeftTextRightDrawable);
        if (leftTextRightDrawable != null) {
            leftTextRightDrawable.setBounds(0, 0, leftTextRightDrawable.getMinimumWidth(), leftTextRightDrawable.getMinimumHeight());
            mLeftTextView.setCompoundDrawablePadding(mLeftTextDrawablePadding);
            mLeftTextView.setCompoundDrawables(null, null, leftTextRightDrawable, null);
        }

        final Drawable leftTextLeftDrawable = a.getDrawable(R.styleable.MyTitleBar_mLeftTextLeftDrawable);
        if (leftTextLeftDrawable != null) {
            leftTextLeftDrawable.setBounds(0, 0, leftTextLeftDrawable.getMinimumWidth(), leftTextLeftDrawable.getMinimumHeight());
            mLeftTextView.setCompoundDrawablePadding(mLeftTextDrawablePadding);
            mLeftTextView.setCompoundDrawables(leftTextLeftDrawable, null, null, null);
        }

        final Drawable logoDrawable = a.getDrawable(R.styleable.MyTitleBar_mLogoIcon);
        if (logoDrawable != null) {
            setLogoIcon(logoDrawable);
        }

        if (mNavButtonView != null) {
            mNavButtonView.setOnClickListener(v -> {
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            });
        }
        mRightCheckBoxButton = a.getDrawable(R.styleable.MyTitleBar_mRightCheckBoxButton);
        if (mRightCheckBoxButton != null) {
            ensureCheckBoxView();
        }

        if (a.hasValue(R.styleable.MyTitleBar_mCustomView)) {
            mCustomViewId = a.getResourceId(R.styleable.MyTitleBar_mCustomView, 0);
        }
        mCustomViewGravity = a.getInt(R.styleable.MyTitleBar_mCustomViewGravity, 1);
        if (mCustomViewId != 0) {
            setCustomView(mCustomViewId);
        }

        a.recycle();
    }

    private void setLayout() {
        titleWrap = new LinearLayout(getContext());
        titleWrap.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, statueHeight + titleViewHeight);
        titleWrap.setLayoutParams(layoutParams);
        addView(titleWrap);
        if (mStatueBarIsTransparent) {
            statueBar = new LinearLayout(getContext());
            LinearLayout.LayoutParams statueBarLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, statueHeight);
            statueBar.setLayoutParams(statueBarLayoutParams);
            titleWrap.addView(statueBar);
        }
        titleView = new RelativeLayout(getContext());
        LayoutParams titleViewLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, titleViewHeight - titleViewHeightMarginTop);
        titleViewLayoutParams.setMargins(0, titleViewHeightMarginTop, 0, 0);
        titleView.setLayoutParams(titleViewLayoutParams);
        titleWrap.addView(titleView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(measureWidth(widthMeasureSpec), statueHeight + titleViewHeight);
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, r, t, b);
//        setDefaultLayoutParams();
////        setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, statueHeight + titleWrapHeight));
//    }

//    @Override
//    protected void onDraw(Canvas canvas) {
////        setDefaultLayoutParams();
//    }


    /**
     * @param resId resId
     */
    public void setCustomView(@LayoutRes int resId) {
        mCustomView = inflate(getContext(), resId, null);
        setCustomView(mCustomView);
    }

    /**
     * @param customView customView
     */
    public void setCustomView(View customView) {
        mCustomView = customView;
        if (customView != null) {
            if (mTitleTextView != null) {
                mTitleTextView.setVisibility(GONE);
            }
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            if (mCustomViewGravity == 0) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            } else if (mCustomViewGravity == 2) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            } else {
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            }

            int marginL = mCustomViewMarginLeft;
            int martinR = mCustomViewMarginRight;
            if (mLeftTextView != null && mLeftTextView.isShown()) {
                marginL += mLeftTextView.getWidth() + mLeftTextMarginLeft;
            }
            if (mNavButtonView != null && mNavButtonView.isShown()) {
                marginL += mNavButtonView.getWidth();
            }
            if (logoView != null && logoView.isShown()) {
                marginL += logoView.getWidth();
            }
            if (mRightTextView != null && mRightTextView.isShown()) {
                martinR += mRightTextView.getWidth() + mRightTextMarginRight;
            }
            if (mRightButtonView != null && mRightButtonView.isShown()) {
                martinR += mRightButtonView.getWidth();
            }

            layoutParams.setMargins(marginL, 0, martinR, 0);
            mCustomView.setLayoutParams(layoutParams);
            titleView.addView(customView);
        }
    }

    public void setCustomViewOnClickListener(OnClickListener listener) {
        if (mCustomView != null) {
            mCustomView.setOnClickListener(listener);
        }
    }

    public View getmCustomView() {
        return mCustomView;
    }

    /**
     * set Navigation  ClickListener
     *
     * @param listener listener
     */
    public void setNavigationOnClickListener(OnClickListener listener) {
//        ensureNavButtonView();
        if (mNavButtonView != null)
            mNavButtonView.setOnClickListener(listener);
    }

    /**
     * set RightButton  ClickListener
     *
     * @param listener listener
     */
    public void setRightButtonOnClickListener(OnClickListener listener) {
        //ensureRightButtonView();
        if (mRightButtonView != null)
            mRightButtonView.setOnClickListener(listener);

    }

    /**
     * set Search ClickListener
     *
     * @param listener listener
     */
    public void setSearchViewOnClickListener(OnClickListener listener) {
        if (mSearchView != null)
            mSearchView.setOnClickListener(listener);

    }

    /**
     * set Right ClickListener
     *
     * @param listener listener
     */
    public void setRightTextOnClickListener(OnClickListener listener) {
        if (mRightTextView != null)
            mRightTextView.setOnClickListener(listener);

    }

    public void setLeftTextOnClickListener(OnClickListener listener) {
        if (mLeftTextView != null)
            mLeftTextView.setOnClickListener(listener);
    }

    public void setLogoOnClickListener(OnClickListener listener) {
        if (logoView != null)
            logoView.setOnClickListener(listener);

    }

    public void setSearchDrawableRight(Drawable icon) {
        searchDrawable = icon;
        if (mSearchView != null) {
            mSearchView.setCompoundDrawablesWithIntrinsicBounds(null, null, searchDrawable, null);
        }
    }

    public void setSearchDrawableLeft(Drawable icon) {
        searchDrawable = icon;
        if (mSearchView != null) {
            mSearchView.setCompoundDrawablesWithIntrinsicBounds(searchDrawable, null, null, null);
        }
    }

    public void setSearchText(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            if (mSearchView == null) {
                mSearchView = new EditText(getContext());
                mSearchView.setSingleLine();
                mSearchView.setEllipsize(TextUtils.TruncateAt.END);
                mSearchView.setClickable(true);
                if (mSearchHintTextColor != 0) {
                    mSearchView.setHintTextColor(mSearchHintTextColor);
                }
                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                mSearchView.setLayoutParams(layoutParams);
                titleView.addView(mSearchView, layoutParams);
            }
        }
        if (mSearchView != null) {
            mSearchView.setHint(title);
        }
        mSearchHintText = title;
    }

    public void setSearchHintTextColor(@ColorInt int color) {
        mSearchHintTextColor = color;
        if (mSearchView != null) {
            mSearchView.setHintTextColor(mSearchHintTextColor);
        }
    }

    public void setSearchHintText(@StringRes int resId) {
        setSearchText(getContext().getText(resId));
    }

    public CharSequence getSearchHintText() {
        return mSearchHintText;
    }

    public void setBadgeCount(int count) {
        ensureBadge();
        mRightButtonViewBadge.setText(String.valueOf(count));
        if (count > 0) {
            mRightButtonViewBadge.setVisibility(VISIBLE);
        } else {
            mRightButtonViewBadge.setVisibility(GONE);
        }

    }

    private void ensureBadge() {
        if (mRightButtonViewBadge == null) {
            mRightButtonViewBadge = new TextView(getContext());
            mRightButtonViewBadge.setBackgroundColor(Color.RED);
            if (mRightButtonViewBadgeBg != null) {
                mRightButtonViewBadge.setBackground(mRightButtonViewBadgeBg);
            }
            mRightButtonViewBadge.setTextColor(Color.WHITE);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mRightButtonViewBadge.setPadding(5, 5, 5, 5);
            mRightButtonViewBadge.setLayoutParams(layoutParams);
            titleView.addView(mRightButtonViewBadge);
        }
    }

    private void ensureRightButtonView() {
        if (mRightButtonView == null) {
            mRightButtonView = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            mRightButtonView.setId(R.id.m_right_button);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mRightButtonView.setLayoutParams(layoutParams);
            titleView.addView(mRightButtonView);
        }
    }

    private void ensureCheckBoxView() {
        if (checkBox == null) {
            checkBox = new CheckBox(getContext(), null);
            checkBox.setId(R.id.m_right_checkbox);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            if (mRightButtonView == null && mRightTextView == null) {
                layoutParams.setMargins(5, 0, mRightTextMarginRight, 0);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            } else if (mRightTextView != null) {
                layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.m_right_text);
            } else {
                layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.m_right_button);
            }
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            checkBox.setLayoutParams(layoutParams);
            checkBox.setButtonDrawable(mRightCheckBoxButton);
            titleView.addView(checkBox);
        }
    }

    public void setRightButtonIcon(@Nullable Drawable icon) {
        if (icon != null) {
            ensureRightButtonView();
        }
        if (mRightButtonView != null) {
            mRightButtonView.setImageDrawable(icon);
        }
    }

    public void setRightButtonIcon(@DrawableRes int resId) {
        setRightButtonIcon(ResourcesCompat.getDrawable(getContext().getResources(), resId, null));
    }

    public void setNavigationIcon(@DrawableRes int resId) {
        setNavButtonIcon(ResourcesCompat.getDrawable(getContext().getResources(), resId, null));
    }

    public void setNavButtonIcon(@Nullable Drawable icon) {
        if (icon != null) {
            ensureNavButtonView();
        }
        if (mNavButtonView != null) {
            mNavButtonView.setImageDrawable(icon);
        }
    }

    private void ensureNavButtonView() {
        if (mNavButtonView == null) {
            mNavButtonView = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            mNavButtonView.setId(R.id.m_nav_button);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            mNavButtonView.setLayoutParams(layoutParams);
            titleView.addView(mNavButtonView);
        }
    }

    public void setLogoIcon(@DrawableRes int resId) {
        setLogoIcon(ResourcesCompat.getDrawable(getContext().getResources(), resId, null));
    }

    public void setLogoIcon(@Nullable Drawable icon) {
        if (icon != null) {
            ensureLogoView();
        }
        if (logoView != null) {
            logoView.setImageDrawable(icon);
        }
    }

    private void ensureLogoView() {
        if (logoView == null) {
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//            if (!mLogoShape) {
////                logoView = new CircleImageView(getContext());
//                logoView = new ImageView(getContext(), null, R.attr.toolbarNavigationButtonStyle);
//                layoutParams = new LayoutParams(100, 100);
//            } else {
//            }
            logoView = new ImageButton(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            logoView.setId(R.id.m_logo);
            if (mLeftTextView != null) {
                layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.m_left_text);
            } else if (mNavButtonView != null) {
                layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.m_nav_button);
            } else {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParams.setMarginStart(0);
            logoView.setLayoutParams(layoutParams);
            titleView.addView(logoView);
        }
    }

    public void setLeftText(@StringRes int resId) {
        setLeftText(getContext().getText(resId));
    }

    public void setLeftTextSize(float size) {
        mLeftTextSize = size;
        if (mLeftTextView != null) {
            mLeftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
    }

    public void setLeftText(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            if (mLeftTextView == null) {
                mLeftTextView = new TextView(getContext());
                mLeftTextView.setSingleLine();
                mLeftTextView.setGravity(Gravity.CENTER);
                mLeftTextView.setId(R.id.m_left_text);
                mLeftTextView.setEllipsize(TextUtils.TruncateAt.END);
                mLeftTextView.setMaxEms(4);
                mLeftTextView.setPadding(10, 0, 10, 0);
                if (mLeftTextColor != 0) {
                    mLeftTextView.setTextColor(mLeftTextColor);
                } else {
                    mLeftTextView.setTextColor(Color.WHITE);
                }
                if (mLeftTextSize != 0) {
                    mLeftTextView.setTextSize(mLeftTextSize);
                }
                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                layoutParams.alignWithParent = true;
                layoutParams.setMargins(mLeftTextMarginLeft, 0, 5, 0);
                if (mNavButtonView == null) {
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                } else {
                    layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.m_nav_button);
                }
                mLeftTextView.setLayoutParams(layoutParams);
                titleView.addView(mLeftTextView);
            }
        }
        if (mLeftTextView != null) {
            mLeftTextView.setText(title);
        }
        mLeftText = title;
    }

    public void setLeftTextMarginLeft(float dpMargin) {
        mLeftTextMarginLeft = pxFromDp(dpMargin);
        if (mLeftTextView != null) {
            LayoutParams layoutParams = (LayoutParams) mLeftTextView.getLayoutParams();
            layoutParams.setMargins(mLeftTextMarginLeft, 0, 5, 0);
        }
    }

    public void setRightTextMarginRight(float dpMargin) {
        mRightTextMarginRight = pxFromDp(dpMargin);
        if (mRightTextView != null) {
            LayoutParams layoutParams = (LayoutParams) mRightTextView.getLayoutParams();
            layoutParams.setMargins(5, 0, mRightTextMarginRight, 0);
        }
    }

    public CharSequence getLeftText() {
        return mLeftText;
    }

    public void setLeftTextColor(@ColorInt int color) {
        mLeftTextColor = color;
        if (mLeftTextView != null) {
            mLeftTextView.setTextColor(color);
        }
    }

    public void setRightTextSize(float size) {
        mRightTextSize = size;
        if (mRightTextView != null) {
            mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
    }

    public void setRightText(@StringRes int resId) {
        setRightText(getContext().getText(resId));
    }

    public CharSequence getRightText() {
        return mRightText;
    }

    public void setRightText(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            if (mRightTextView == null) {
                mRightTextView = new TextView(getContext());
                mRightTextView.setSingleLine();
                mRightTextView.setGravity(Gravity.CENTER);
                mRightTextView.setEllipsize(TextUtils.TruncateAt.END);
                mRightTextView.setId(R.id.m_right_text);
                mRightTextView.setMaxEms(4);
                mRightTextView.setPadding(10, 0, 10, 0);
                if (mRightTextColor != 0) {
                    mRightTextView.setTextColor(mRightTextColor);
                } else {
                    mRightTextView.setTextColor(Color.WHITE);
                }
                if (mRightTextSize != 0) {
                    mRightTextView.setTextSize(mRightTextSize);
                }
                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                layoutParams.alignWithParent = true;
                if (mRightButtonView == null) {
                    layoutParams.setMargins(5, 0, mRightTextMarginRight, 0);
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                } else {
                    layoutParams.addRule(RelativeLayout.LEFT_OF, R.id.m_right_button);
                }
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                mRightTextView.setLayoutParams(layoutParams);
                titleView.addView(mRightTextView);
            }
        }
        if (mRightTextView != null) {
            mRightTextView.setText(title);
        }
        mRightText = title;
    }

    public void setRightTextColor(@ColorInt int color) {
        mRightTextColor = color;
        if (mRightTextView != null) {
            mRightTextView.setTextColor(color);
        }
    }

    public void setTitleTextColor(@ColorInt int color) {
        mTitleTextColor = color;
        if (mTitleTextView != null) {
            mTitleTextView.setTextColor(color);
        }
    }

    public void setTitleSize(float size) {
        mTitleSize = size;
        if (mTitleTextView != null) {
            mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        }
    }

    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            if (mTitleTextView == null) {
                mTitleTextView = new TextView(getContext());
                mTitleTextView.setSingleLine();
                mTitleTextView.setGravity(Gravity.CENTER);
                mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                mTitleTextView.setMaxWidth(pxFromDp(200));
                mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                if (mTitleTextColor != 0) {
                    mTitleTextView.setTextColor(mTitleTextColor);
                } else {
                    mTitleTextView.setTextColor(Color.WHITE);
                }
                if (mTitleSize != 0) {
                    mTitleTextView.setTextSize(mTitleSize);
                }
                LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                mTitleTextView.setLayoutParams(layoutParams);
                titleView.addView(mTitleTextView);
            }
        }
        if (mTitleTextView != null) {
            mTitleTextView.setText(title);
        }
        if (mCustomView != null) {
            mCustomView.setVisibility(GONE);
        }
        if (mTitleTextView != null) {
            mTitleTextView.setVisibility(VISIBLE);
        }
        mTitleText = title;
    }

    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
    }

    public CharSequence getTitle() {
        return mTitleText;
    }

    private int dpFromPx(final float px) {
        return (int) (px / getResources().getDisplayMetrics().density);
    }

    private int pxFromDp(final float dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    public ImageButton getmRightButtonView() {
        return mRightButtonView;
    }


    public TextView getmTitleTextView() {
        return mTitleTextView;
    }

    public void setmTitleTextView(TextView mTitleTextView) {
        this.mTitleTextView = mTitleTextView;
    }

    public TextView getmLeftTextView() {
        return mLeftTextView;
    }

    public void setmLeftTextView(TextView mLeftTextView) {
        this.mLeftTextView = mLeftTextView;
    }

    public TextView getmRightTextView() {
        return mRightTextView;
    }

    public void setmRightTextView(TextView mRightTextView) {
        this.mRightTextView = mRightTextView;
    }

    public void showCustomView() {
        if (mCustomView != null) {
            mCustomView.setVisibility(VISIBLE);
            mTitleTextView.setVisibility(GONE);
        }
    }

    public void showLeftTextView() {
        if (mLeftTextView != null)
            mLeftTextView.setVisibility(VISIBLE);
    }

    public void hideLeftTextView() {
        if (mLeftTextView != null)
            mLeftTextView.setVisibility(GONE);
    }

    public void showRightTextView() {
        if (mRightTextView != null)
            mRightTextView.setVisibility(VISIBLE);
    }

    public void hideRightTextView() {
        if (mRightTextView != null)
            mRightTextView.setVisibility(GONE);
    }

    public void hideRightButton() {
        if (mRightButtonView != null)
            mRightButtonView.setVisibility(GONE);
    }

    public void showNavButtonView() {
        if (mNavButtonView != null) {
            mNavButtonView.setVisibility(VISIBLE);
        }
    }

    public void hideNavButtonView() {
        if (mNavButtonView != null) {
            mNavButtonView.setVisibility(GONE);
        }
    }

    public void hideLogo() {
        if (logoView != null) {
            logoView.setVisibility(GONE);
        }
    }

    public void showLogo() {
        if (logoView != null) {
            logoView.setVisibility(VISIBLE);
        }
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public boolean getCheckBoxIsChecked() {
        return checkBox != null && checkBox.isChecked();
    }

    public void setChecked(boolean checked) {
        if (checkBox != null) {
            checkBox.setChecked(checked);
        }
    }


    private int getStatueBarHeight() {
        /**
         * 获取状态栏高度——方法1
         * */
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

        }
        return statusBarHeight1 == -1 ? getStatueBarHeight2() : statusBarHeight1;
    }

    private int getStatueBarHeight2() {
        /**
         * 获取状态栏高度——方法2
         * */
        int statusBarHeight2 = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight2 = getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight2;
    }


}
