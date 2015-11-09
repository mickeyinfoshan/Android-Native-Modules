package com.hello.natives;

import com.facebook.react.common.ShakeDetector;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import android.hardware.SensorManager;
import android.content.Context;

public class ShakeModule extends ReactContextBaseJavaModule {
	private ReactApplicationContext mReactApplicationContext;
	private ShakeDetector mShakeDetector;
	private Callback cb;
	private boolean isStarted = false;

	public ShakeModule(ReactApplicationContext reactContext) {
		super(reactContext);
		mReactApplicationContext = reactContext;
		mShakeDetector = new ShakeDetector(new ShakeDetector.ShakeListener() {
     		@Override
      		public void onShake() {
        		sendEvent();
      		}
    	});
	}

	@ReactMethod
	public void start() {
		if(!isStarted){
			mShakeDetector.start((SensorManager) mReactApplicationContext.getSystemService(Context.SENSOR_SERVICE));
			isStarted = true;
		}			
	}

	@ReactMethod
	public void stop() {
		if(isStarted) {
			mShakeDetector.stop();
			isStarted = false;
		}
	}

	@Override
	public String getName() {
		return "ShakeAndroid";
	}

	private void sendEvent() {
		mReactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
      	.emit("shake", null);
	}
}