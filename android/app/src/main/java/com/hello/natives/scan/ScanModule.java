package com.hello.natives.scan;

import android.content.Context;
import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.BaseJavaModule;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanModule extends BaseJavaModule {
	//private Context mContext;
	private IntentIntegrator mIntentIntegrator;
	public ScanModule(Activity currentActivity) {
		super();
		mIntentIntegrator = new IntentIntegrator((Activity) currentActivity);
		mIntentIntegrator.setOrientationLocked(true);
		mIntentIntegrator.setCaptureActivity(YeeuuCaptureActivity.class);
	}

	@ReactMethod
	public void scan(Callback cb, String str) {
		mIntentIntegrator.setPrompt(str);
		mIntentIntegrator.initiateScan();
	}

	@Override
	public String getName() {
		return "ScanAndroid";
	}

	
}