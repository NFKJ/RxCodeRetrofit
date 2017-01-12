package com.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils
{

    /**
     * 不使用占位图
     * 加载String的图片地址
     * @param imageView imageview
     * @param url 图片地址
     */
    public static void displayNoPlace(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url).crossFade().into(imageView);
    }

    /**
     * 不使用占位图
     * 加载String的图片地址
     * @param imageView imageview
     * @param url 图片资源名
     */
    public static void displayNoPlace(ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url).crossFade().into(imageView);
    }

    /**
     * 加载String的图片地址
     * @param imageView imageview
     * @param url 图片地址
     */
    public static void display(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.bg_square_ing)
//                .error(R.drawable.bg_square_error)
                .crossFade().into(imageView);
    }

    public static void displayNoplaceholde(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .error(R.drawable.bg_square_error)
                .crossFade().into(imageView);
    }

    /**
     * 加载File的图片地址
     * @param imageView imageview
     * @param url 图片文件地址
     */
    public static void display(ImageView imageView, File url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.bg_square_ing)
//                .error(R.drawable.bg_square_error)
                .crossFade().into(imageView);
    }

    /**
     * 加载int的图片地址
     * @param imageView imageview
     * @param url 图片资源文件名
     */
    public static void display(ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.bg_square_ing)
//                .error(R.drawable.bg_square_error)
                .crossFade().into(imageView);
    }

    /**
     * 地址转换成文件形式加载
     * 加载设备本地图片
     * @param imageView imageview
     * @param url 本地图片存储地址
     */
    public static void displayPhoto(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(new File(url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.bg_square_ing)
//                .error(R.drawable.bg_square_error)
                .into(imageView);
    }

    /**
     * 地址转换成文件形式加载
     * 加载设备本地大图
     * @param imageView imageview
     * @param url 本地图片存储地址
     */
    public static void displayBigPhoto(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(new File(url)).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.bg_square_ing)
//                .error(R.drawable.bg_square_error)
                .into(imageView);
    }

    /**
     * 加载String的圆形图片
     * @param imageView imageview
     * @param url 图片地址
     */
    public static void displayRound(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.bg_square_ing)
//                .error(R.drawable.bg_square_error)
                .centerCrop().transform(new GlideRoundTransformUtil(imageView.getContext())).into(imageView);
    }

    /**
     * 加载file的圆形图片
     * @param imageView imageview
     * @param file 图片地址
     */
    public static void displayRound(ImageView imageView, File file) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.bg_square_ing)
//                .error(R.drawable.bg_square_error)
                .centerCrop().transform(new GlideRoundTransformUtil(imageView.getContext())).into(imageView);
    }

    /**
     * 加载int的圆形图片
     * @param imageView imageview
     * @param url 图片资源名
     */
    public static void displayRound(ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.bg_square_ing)
//                .error(R.drawable.bg_square_error)
                .centerCrop().transform(new GlideRoundTransformUtil(imageView.getContext())).into(imageView);
    }

    /**
     * 清除缓存
     * @param context 上下文
     */
    public static void cleanMemory(Context context){
        Glide.get(context).clearMemory();
    }
}
