package com.common.widget.editview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

/**
 * 用于计数的EditText
 *
 * Created by liyaxing on 2015/9/21.
 */
public class CountEditText extends EditText{

    private TextView countView ;
    private TextWatcher textWatcher ;
    private int maxCount ;

    private String format = "%d" ;

    public CountEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init() ;
    }

    public CountEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init() ;
    }

    public CountEditText(Context context) {
        super(context);
        init() ;
    }

    private void init() {

        super.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(textWatcher!=null) textWatcher.beforeTextChanged(s,start,count,after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(textWatcher!=null) textWatcher.onTextChanged(s,start,before,count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(textWatcher!=null) textWatcher.afterTextChanged(s);
                String text = s.toString() ;
                if(TextUtils.isEmpty(text)){
                    setCount(0) ;
                    return ;
                }

                int len = text.length() ;
                setCount(len) ;
            }
        });

    }

    private void setCount(int count){
        if(countView != null)
            countView.setText(format.replace("%d", String.valueOf(count)) + "/" + maxCount);
    }

    public void setCountView(TextView countView,int maxCount){
        setCountView(countView, maxCount, null) ;
    }

    public void setCountView(TextView countView,int maxCount,String format){
        this.countView = countView ;
        this.maxCount = maxCount ;
        if(countView == null){
           throw new NullPointerException("The countView input into CountEditText cannot be null") ;
        }
        if(maxCount < 0){
            throw new IllegalArgumentException("The maxCount must >= 0") ;
        }
        super.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxCount)});
        setCount(maxCount) ;

        if(!TextUtils.isEmpty(format)){
        	this.format = format ;
        }
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        this.textWatcher = watcher ;
    }

    @SuppressLint("NewApi")
	@Override
    public void setFilters(InputFilter[] filters) {

        if(filters == null || filters.length <= 0) return;

        InputFilter[] newFilters = Arrays.copyOf(filters,filters.length + 1) ;
        newFilters[filters.length] = new InputFilter.LengthFilter(maxCount);

        super.setFilters(newFilters);

    }
}