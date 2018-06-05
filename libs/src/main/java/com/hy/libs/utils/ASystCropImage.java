package com.hy.libs.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

/**
 * 调用Androiud系统实现图片剪裁
 * 
 * @author mao
 */
public class ASystCropImage {

	/**
	 * 调用系统拍照功能
	 * 
	 * @param filePath
	 *            照片返回保存路径
	 * @return Intent
	 */
	public static Intent takePhoto(String filePath) {

		Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(filePath));
		return cameraintent;
	}

	/**
	 * 浏览系统图片
	 * 
	 * @return Intent
	 */
	public static Intent scanSysImages() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		return intent;
	}

	/**
	 * 剪裁大图片
	 * 
	 * @param resourceUri
	 *            源文件的Uri
	 * @param dataUri
	 *            返回文件的Uri
	 * @param aspectX
	 *            X横比例
	 * @param aspectY
	 *            Y纵比列
	 * @param outputX
	 *            输出X大小 Px
	 * @param outputY
	 *            输出Y大小 Px
	 * @return Intent
	 */
	public static Intent cropBigImage(Uri resourceUri, Uri dataUri, int aspectX, int aspectY, int outputX, int outputY) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(resourceUri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", aspectX);
		intent.putExtra("aspectY", aspectY);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, dataUri);
		intent.putExtra("return-data", false);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true);
		return intent;
	}

	/**
	 * 小图剪裁
	 * 
	 * @param resourceUri
	 * @param aspectX
	 * @param aspectY
	 * @param outputX
	 * @param outputY
	 * @return
	 */
	public static Intent cropSmallImage(Uri resourceUri, int aspectX, int aspectY, int outputX, int outputY) {

		Intent intent = new Intent();
		intent.setAction("com.android.camera.action.CROP");
		intent.setDataAndType(resourceUri, "image/*");// mUri是已经选择的图片Uri
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", aspectX);// 裁剪框比例
		intent.putExtra("aspectY", aspectY);
		intent.putExtra("outputX", outputX);// 输出图片大小
		intent.putExtra("outputY", outputY);
		intent.putExtra("return-data", true);
		return intent;
	}

	/**
	 * 小图剪裁时，获取Bitmap数据
	 * 
	 * @param data
	 * @return
	 */
	public static Bitmap getCropSmallImageData(Intent data) {

		Bundle bundle = data.getExtras();
		Bitmap bitmap = bundle.getParcelable("data");
		return bitmap;

	}
}
