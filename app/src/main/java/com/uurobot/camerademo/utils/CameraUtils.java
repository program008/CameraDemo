/**
 * Copyright (C) 2017 Baidu Inc. All rights reserved.
 */

package com.uurobot.camerademo.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Environment;

/**
 * CameraUtils
 */
public class CameraUtils {

	public static final String TAG = CameraUtils.class.getSimpleName();

	public static final String rootPath = Environment.getExternalStorageDirectory().toString();

	public static final String TEMP_TEMP = "/temp/temp/";

	public static final String TEMP_IMG_FILE = rootPath + TEMP_TEMP;

	public static void releaseCamera(Camera camera) {
		try {
			camera.release();
		}
		catch (RuntimeException e2) {
			e2.printStackTrace();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		finally {
		}
	}

	/**
	 * Check if this device has a camera
	 */
	private boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		}
		else {
			// no camera on this device
			return false;
		}
	}

	/**
	 * A safe way to get an instance of the Camera object.
	 */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		}
		catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}
}
