package com.common.widget.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;
import com.common.utils.ViewUtils;

@SuppressWarnings("deprecation")
public class WidgeButton extends LinearLayout
{
    private ImageView m_ivBtnBackground;
    private TextView m_tvBtnText;
    private View m_vItem;
    private ImageView m_ivLeftDivider;
    private ImageView m_ivRightDivider;
    private int m_nBGResId;
    private String m_divider;
    private WidgeButtonCategory m_type = WidgeButtonCategory.image;

    public WidgeButton(Context context)
    {
        super(context);
        init(context);
    }

    @SuppressWarnings("ResourceType")
    @SuppressLint("Recycle")
    public WidgeButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WidgeButton,
                R.styleable.WidgeButtonTheme_WidgeButtonStyle, R.style.Widget_WidgeButton);

        m_divider = array.getString(R.styleable.WidgeButton_shanliaodivider);
        if (m_divider.equals("left"))
        {
            hideLeftDivider();
        }
        else if (m_divider.equals("right"))
        {
            hideRightDivider();
        }

        m_nBGResId = array.getResourceId(R.styleable.WidgeButton_src, -1);
        setBackgroundResource(m_nBGResId);
    }

    private void init(Context context)
    {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(getLayout(), this, true);
        m_ivBtnBackground = (ImageView) findViewById(R.id.ivBtnImg);
        m_tvBtnText = (TextView) findViewById(R.id.tvBtnText);
        m_vItem = findViewById(R.id.llActionBarLayout);
        m_ivLeftDivider = (ImageView) findViewById(R.id.ivLeftDivider);
        m_ivRightDivider = (ImageView) findViewById(R.id.ivRightDivider);
    }

    /**
     * Subclasses should use a modified version of
     * /drawable/layout/shanliao_btn_item.xml
     */
    protected int getLayout()
    {
        return R.layout.layout_base_widge_btn;
    }

    public WidgeButton(Context context, AttributeSet attrs, ImageView imageView)
    {
        this(context, attrs);
        m_ivBtnBackground = imageView;
    }

    public WidgeButton(Context context, int resId, boolean bool)
    {
        this(context);
        m_ivBtnBackground.setBackgroundResource(resId);
    }

    public WidgeButton(Context context, Drawable drawable)
    {
        this(context);
        m_ivBtnBackground.setBackgroundDrawable(drawable);
    }

    public WidgeButton(Context context, Drawable drawable, int width, int height)
    {
        this(context);
        ViewGroup.LayoutParams params = m_ivBtnBackground.getLayoutParams();
        params.width = width;
        params.height = height;
        m_ivBtnBackground.setBackgroundDrawable(drawable);
    }

    public int getBackgroundHeight()
    {
        if (m_ivBtnBackground != null)
        {
            ViewGroup.LayoutParams params = m_ivBtnBackground.getLayoutParams();
            return params.height;
        }
        return 0;
    }

    public WidgeButton(Context context, int text)
    {
        this(context);
        m_tvBtnText.setText(text);
        setType(WidgeButtonCategory.text);
    }

    public void setWidgeButtonName(String name)
    {
        m_tvBtnText.setText(name);
        setType(WidgeButtonCategory.text);
    }

    public TextView getTextView()
    {
        return m_tvBtnText;
    }

    public ImageView getBgImageView()
    {
        return m_ivBtnBackground;
    }

    public void setBackgroundResource(int resId)
    {
        m_ivBtnBackground.setBackgroundResource(resId);
    }

    @SuppressWarnings("deprecation")
    public void setBackgroundDrawable(Drawable drawable)
    {
        m_ivBtnBackground.setBackgroundDrawable(drawable);
    }

    public void setOnClickListener(OnClickListener paramOnClickListener)
    {

        if (m_vItem == null)
            return;

        m_vItem.setOnClickListener(paramOnClickListener);
    }

    public void setId(int id)
    {

        if (m_vItem == null)
            return;

        m_vItem.setId(id);
    }

    public int getId()
    {
        return m_vItem == null ? -1 : m_vItem.getId();
    }

    public void setTag(Object objTag)
    {
        if (m_vItem == null)
            return;

        m_vItem.setTag(objTag);
    }

    public Object getTag()
    {
        return m_vItem == null ? null : m_vItem.getTag();
    }

    public void setType(WidgeButtonCategory type)
    {
        this.m_type = type;
        if (m_type.equals(WidgeButtonCategory.text))
            ViewUtils.switchViewVisibility(m_ivBtnBackground, m_tvBtnText);
    }

    public WidgeButtonCategory getWidgeButtonCategory()
    {
        return m_type;
    }

    public void hideLeftDivider()
    {
        m_ivLeftDivider.setVisibility(View.GONE);
    }

    public void hideRightDivider()
    {
        m_ivRightDivider.setVisibility(View.GONE);
    }

    public enum WidgeButtonCategory
    {
        image("image"), text("text");

        // private String type;

        WidgeButtonCategory(String type)
        {
            // this.type = type;
        }

        @Override
        public String toString()
        {
            return super.toString(); // To change body of overridden methods use
                                     // File | Settings | File Templates.
        }
    }

    public ImageView getM_ivLeftDivider()
    {
        return m_ivLeftDivider;
    }

    public ImageView getM_ivRightDivider()
    {
        return m_ivRightDivider;
    }

}
