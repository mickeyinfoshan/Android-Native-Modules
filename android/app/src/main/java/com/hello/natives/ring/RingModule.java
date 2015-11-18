package com.hello.natives.ring;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Context;
import android.net.Uri;
import android.media.RingtoneManager;
import android.media.Ringtone;

public class RingModule extends ReactContextBaseJavaModule {

	private ReactApplicationContext mReactApplicationContext;

	public RingModule(ReactApplicationContext reactContext) {
		super(reactContext);
		mReactApplicationContext = reactContext;
	}

	@ReactMethod
	public void beep() {
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone r = RingtoneManager.getRingtone(mReactApplicationContext, notification);
    	r.play();		
	}

	@Override
	public String getName() {
		return "RingAndroid";
	}
}