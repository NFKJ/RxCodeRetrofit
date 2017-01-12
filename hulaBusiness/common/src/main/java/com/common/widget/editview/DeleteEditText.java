package com.common.widget.editview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.common.R;
import com.common.utils.KeyBoardUtils;
import com.common.utils.SizeUtils;

public class DeleteEditText extends EditText implements View.OnFocusChangeListener, TextWatcher
{
    private Context context;

    /**
     * 删除按钮的引用 
     */  
    private Drawable mClearDrawable;
    /** 
     * 控件是否有焦点 
     */  
    private boolean hasFoucs;

    /**
     * 删除按钮图标大小
     */
    private int deleLogoSize;

    public DeleteEditText(Context context) {
        super(context);
        this.context = context;
        init(context);
    }
  
    public DeleteEditText(Context context, AttributeSet attrs) {
        // 这里构造方法也很重要，不加这个很多属性不能再XML里面定义  
        this(context, attrs, 0);
        init(context);
    }  
  
    public DeleteEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DeleteEditView, defStyle, 0);
        deleLogoSize = a.getDimensionPixelSize(R.styleable.DeleteEditView_del_logo_size, SizeUtils.dp2px(context, 18));
        a.recycle();

        init(context);
    }  
  
    private void init(Context context)
    {
        this.context = context;

        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片  
        mClearDrawable = getCompoundDrawables()[2];  
        if (mClearDrawable == null) {  
            // throw new  
            // NullPointerException("You can add drawableRight attribute in XML");
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M)
                mClearDrawable = context.getResources().getDrawable(R.mipmap.icon_close_red);
            else
                mClearDrawable = context.getDrawable(R.mipmap.icon_close_red);
        }

        assert mClearDrawable != null;
        mClearDrawable.setBounds(0, 0, deleLogoSize, deleLogoSize);

        // 默认设置隐藏图标
        setClearIconVisible(false);  
        // 设置焦点改变的监听  
        setOnFocusChangeListener(this);  
        // 设置输入框里面内容发生改变的监听  
        addTextChangedListener(this);  
    }

    /** 
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 - 
     * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑 
     */  
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {  
  
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));  
  
                if (touchable) {  
                    this.setText("");  
                }  
            }  
        }  
  
        return super.onTouchEvent(event);  
    }  
  
    /** 
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏 
     */  
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;

        if (hasFocus) {
            setBackgroundResource(R.drawable.deltext_focus_edit);
            setClearIconVisible(getText().length() > 0);
        } else {
            setBackgroundResource(R.drawable.deltext_normal_edit);
            setClearIconVisible(false);
            KeyBoardUtils.hideKeyBoard(v);
        }
    }  
  
    /** 
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去 
     *  
     * @param visible 是否隐藏
     */  
    protected void setClearIconVisible(boolean visible) {  
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);  
    }  
  
    /** 
     * 当输入框里面内容发生变化的时候回调的方法 
     */  
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {  
            setClearIconVisible(s.length() > 0);  
        }  
    }  
  
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  
    }  
  
    @Override
    public void afterTextChanged(Editable s) {
  
    }  
}