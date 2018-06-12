package com.uurobot.camerademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private android.widget.Button btncamera;

	private android.widget.Button btncamera2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}

	private void initView() {
		this.btncamera2 = (Button) findViewById(R.id.btn_camera2);
		this.btncamera = (Button) findViewById(R.id.btn_camera);

		btncamera.setOnClickListener(this);
		btncamera2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_camera:
				nextActivity(CameraActivity.class);
				break;
			case R.id.btn_camera2:
				break;
			default:
				break;
		}
	}

	private void nextActivity(Class clazz){
		Intent intent = new Intent(this,clazz);
		startActivity(intent);
		overridePendingTransition(R.anim.face_zoom_enter, R.anim.face_zoom_exit);
	}
}
