package com.hello.natives.dial;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;

public class DialModule extends ReactContextBaseJavaModule {

	private ReactApplicationContext mReactApplicationContext;

	public DialModule(ReactApplicationContext reactContext) {
		super(reactContext);
		mReactApplicationContext = reactContext;
	}

	@ReactMethod
	public void dial(String phone) {
		startAction(Intent.ACTION_DIAL, phone);			
	}

	@ReactMethod
	public void call(String phone) {
		startAction(Intent.ACTION_CALL, phone);			
	}

	@Override
	public String getName() {
		return "DialAndroid";
	}

	private void startAction(String actionType, String phone) {
		Uri number = Uri.parse("tel:" + phone);
		Intent intent = new Intent(actionType, number);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		mReactApplicationContext.startActivity(intent);
	}
}