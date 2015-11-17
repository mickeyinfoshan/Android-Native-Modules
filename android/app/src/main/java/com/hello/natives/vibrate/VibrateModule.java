package com.hello.natives.vibrate;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.os.Vibrator;
import android.content.Context;

public class VibrateModule extends ReactContextBaseJavaModule {

	private ReactApplicationContext mReactApplicationContext;
	private Vibrator mVibrator;

	public VibrateModule(ReactApplicationContext reactContext) {
		super(reactContext);
		mVibrator = (Vibrator) reactContext.getSystemService(Context.VIBRATOR_SERVICE);
	}

	@ReactMethod
	public void vibrate(int millionseconds) {
		mVibrator.vibrate(millionseconds);			
	}

	@Override
	public String getName() {
		return "VibrateAndroid";
	}
}