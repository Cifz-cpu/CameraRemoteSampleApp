package com.hy.libs.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * 类描述：通过资源ID获取对应资源文件
 * 创建人：毛华磊
 * 创建时间：2017/3/2 17:04
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ResourceUtils {

    /**
     * 图片资源获取
     *
     * @param context
     * @param id
     * @return
     */
    public static Drawable getDrawable(Context context, int id) {
        return context.getResources().getDrawable(id);
    }

    /**
     * 字符串获取
     *
     * @param context
     * @param id
     * @return
     */
    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    /**
     * 颜色获取
     *
     * @param context
     * @param id
     * @return
     */
    public static int getColor(Context context, int id) {
        return context.getResources().getColor(id);
    }


    /**
     * 字符串数组
     *
     * @param context
     * @param id
     * @return
     */
    public static String[] getStringArray(Context context, int id) {
        return context.getResources().getStringArray(id);
    }

}