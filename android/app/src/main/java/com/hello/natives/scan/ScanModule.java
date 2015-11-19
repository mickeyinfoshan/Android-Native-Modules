package com.hello.natives.scan;

import android.content.Context;
import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.BaseJavaModule;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.hello.activityresult.ActivityResultHandler;
import com.hello.MainActivity;

public class ScanModule extends BaseJavaModule implements ActivityResultHandler{
	private IntentIntegrator mIntentIntegrator;
	private Callback mCallback;
	public ScanModule(MainActivity currentActivity) {
		super();
		mIntentIntegrator = new IntentIntegrator(currentActivity);
		mIntentIntegrator.setOrientationLocked(true);
		mIntentIntegrator.setCaptureActivity(YeeuuCaptureActivity.class);
		currentActivity.getActivityResultManager().put(IntentIntegrator.REQUEST_CODE, this);
	}

	@ReactMethod
	public void scan(String str, Callback cb) {
		mCallback = cb;
		mIntentIntegrator.setPrompt(str);
		mIntentIntegrator.initiateScan();
	}

	@Override
	public String getName() {
		return "ScanAndroid";
	}

	@Override
	public void handleActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (result != null) {
			String content = result.getContents();
			if(content != null) {
				mCallback.invoke(content);
			}
		}
	}
	
}