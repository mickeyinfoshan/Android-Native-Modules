package com.hello.yeeuu;

import com.facebook.react.bridge.ReactApplicationContext;
import com.hello.natives.ShakeModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.ReactPackage;
import java.util.List;
import java.util.ArrayList;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;

public class YeeuuReactPackage implements ReactPackage {

  @Override
  public List<NativeModule> createNativeModules(
                              ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<>();

    modules.add(new ShakeModule(reactContext));

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