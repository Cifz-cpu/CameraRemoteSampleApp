package com.hy.libs.bitmap;

/**
 * 图片压缩处理接口回调
 *
 * @author mao
 */
public interface BitmapRevitionListener {

    // 压缩处理之前
    public void beforeBitmapRevition();

    // 压缩处理之后(判断comPath为空标识压缩失败;否则压缩成功)
    public void afterBitmapRevition(String compPath);
}
