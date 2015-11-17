package com.hello.natives.dialog;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;

import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.Activity;

public class DialogModule extends BaseJavaModule {
	private  Activity mActivity;

	public DialogModule(Activity currentActivity) {
		super();
		mActivity = currentActivity;
	}

	private AlertDialog.Builder createBuilder() {
		return new AlertDialog.Builder(mActivity);
	}

	@ReactMethod
	public void alert(ReadableMap config, final Callback cb) {
		AlertDialog.Builder builder = createBuilder();
		String title = "";
		String content = "";
		String okText = "OK";
		if(config.hasKey("title")) {
			title = config.getString("title");
		}
		if(config.hasKey("content")) {
			content = config.getString("content");
		}
		if(config.hasKey("okText")) {
			okText = config.getString("okText");
		}
		builder.setTitle(title)
		 	   .setMessage(content)
		 	   .setPositiveButton(okText, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                       cb.invoke();
                   }
               });
        builder.create().show();
	}

	@ReactMethod
	public void confirm(ReadableMap config, final Callback positiveCallback, final Callback negativeCallback) {
		AlertDialog.Builder builder = createBuilder();
		String title = "";
		String content = "";
		String okText = "OK";
		String cancelText = "Cancel";
		if(config.hasKey("title")) {
			title = config.getString("title");
		}
		if(config.hasKey("content")) {
			content = config.getString("content");
		}
		if(config.hasKey("okText")) {
			okText = config.getString("okText");
		}
		if(config.hasKey("cancelText")) {
			cancelText = config.getString("cancelText");
		}
		builder.setTitle(title)
		 	   .setMessage(content)
		 	   .setPositiveButton(okText, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                       positiveCallback.invoke();
                   }
               })
               .setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       dialog.dismiss();
                       negativeCallback.invoke();
                   }
               });
        builder.create().show();

	}

	@Override
	public String getName() {
		return "DialogAndroid";
	}
	
}