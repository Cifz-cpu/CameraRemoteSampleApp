package com.hy.libs.image;

import java.util.List;

public interface MultiImgsScListener {

    /**
     * 图片文件夹返回
     *
     * @param imageFloders
     */
    public void scanImageFolder(List<ImageFloder> imageFloders);

    /**
     * 单个图片文件下的图片返回
     *
     * @param imageNames
     */
    public void scanImagesOfAFolder(List<String> imageNames);

}
