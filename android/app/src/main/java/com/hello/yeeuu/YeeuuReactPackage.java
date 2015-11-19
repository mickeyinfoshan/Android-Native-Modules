package com.hello.yeeuu;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.ReactPackage;
import java.util.List;
import java.util.ArrayList;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;

import android.app.Activity;

import com.hello.natives.shake.ShakeModule;
import com.hello.natives.scan.ScanModule;
import com.hello.natives.vibrate.VibrateModule;
import com.hello.natives.dialog.DialogModule;
import com.hello.natives.dial.DialModule;
import com.hello.natives.ring.RingModule;

import com.hello.MainActivity;

public class YeeuuReactPackage implements ReactPackage {

  private MainActivity mActivity;

  public YeeuuReactPackage(MainActivity currentActivity) {
    super();
    mActivity = currentActivity;
  }

  @Override
  public List<NativeModule> createNativeModules(
                              ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<>();

    modules.add(new ShakeModule(reactContext));
    modules.add(new ScanModule(mActivity));
    modules.add(new VibrateModule(reactContext));
    modules.add(new DialogModule(mActivity));
    modules.add(new DialModule(reactContext));
    modules.add(new RingModule(reactContext));
    return modules;
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return new ArrayList<>(0);
  }

  @Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return new ArrayList<>(0);
  }
}