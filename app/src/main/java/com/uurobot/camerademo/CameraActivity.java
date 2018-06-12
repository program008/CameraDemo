package com.uurobot.camerademo;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.uurobot.camerademo.utils.CameraPreview;
import com.uurobot.camerademo.utils.CameraUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.uurobot.camerademo.utils.CameraUtils.getCameraInstance;

public class CameraActivity extends Activity {

	private static final String TAG = "CameraActivity";

	private Camera mCamera;

	private CameraPreview mPreview;

	private ImageView ivShow;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);

		ivShow = findViewById(R.id.iv_show);
		// Create an instance of Camera
		mCamera = getCameraInstance();

		// Create our Preview view and set it as the content of our activity.
		mPreview = new CameraPreview(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mPreview);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			CharSequence faceTime = DateFormat.format("yyyy_MM_dd_HH_mm_ss", System.currentTimeMillis());
			final String fileName = faceTime + ".jpg";
			File pictureFile = new File(CameraUtils.rootPath +"/"+fileName);
			Log.e(TAG, CameraUtils.rootPath +"/"+fileName);
			try {
				FileOutputStream fos = new FileOutputStream(pictureFile);
				fos.write(data);
				fos.close();
			}
			catch (FileNotFoundException e) {
				Log.d(TAG, "File not found: " + e.getMessage());
			}
			catch (IOException e) {
				Log.d(TAG, "Error accessing file: " + e.getMessage());
			}
			ivShow.setImageBitmap(BitmapFactory.decodeByteArray(data,0,data.length));
		}
	};

	public void takePic(View view) {
		mCamera.takePicture(null, null, mPicture);
	}
}
