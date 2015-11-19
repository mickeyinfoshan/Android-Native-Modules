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
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.Activity;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;  

public class DialogModule extends BaseJavaModule {
	private Activity mActivity;
	private Boolean isCancel = false;

	public DialogModule(Activity currentActivity) {
		super();
		mActivity = currentActivity;
	}

	private AlertDialog.Builder createBuilder() {
		return new AlertDialog.Builder(mActivity);
	}

	@ReactMethod
	public void alert(ReadableMap options, final Callback cb) {
		AlertDialog.Builder builder = createBuilder();
		String title = getString(options, "title", "");
		String content = getString(options, "content", "");
		String okText = getString(options, "okText", "OK");
		isCancel = false;
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
	public void confirm(ReadableMap options, final Callback positiveCallback, final Callback negativeCallback) {
		AlertDialog.Builder builder = createBuilder();
		String title = getString(options, "title", "");
		String content = getString(options, "content", "");
		String okText = getString(options, "okText", "OK");
		String cancelText = getString(options, "cancelText", "Cancel");
		isCancel = false;
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
                   	   	isCancel = true;
                    	dialog.dismiss();
                       	negativeCallback.invoke();
                   }
               });
        builder.create().show();

	}

	@ReactMethod
	public void date(ReadableMap options, final Callback getDateHandler) {
		Calendar calendar = Calendar.getInstance();
		Date defaultDate = new Date();
		long[] dateOptions = getDateOptions(options);
		defaultDate.setTime(dateOptions[0]);
		calendar.setTime(defaultDate);
		isCancel = false;
		DatePickerDialog.OnDateSetListener datePickerListener 
            = new DatePickerDialog.OnDateSetListener() {
        			public void onDateSet(DatePicker view, int selectedYear,
            						  int selectedMonth, int selectedDay) {
        				if(view.isShown() && !isCancel){
        					getDateHandler.invoke(selectedYear, selectedMonth, selectedDay);
        				}        				           
        			}
		};
		DatePickerDialog dialog = new DatePickerDialog(mActivity, datePickerListener,
						calendar.get(Calendar.YEAR),  
                        calendar.get(Calendar.MONTH),  
                        calendar.get(Calendar.DAY_OF_MONTH));

		if(options.hasKey("title")) {
			dialog.setTitle(options.getString("title"));
		}
		if(options.hasKey("okText")) {
			dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, options.getString("okText"), dialog);
		}
		
		if(options.hasKey("cancelText")) {
			dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, options.getString("cancelText"), new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int which) {
       				if (which == DialogInterface.BUTTON_NEGATIVE) {
          				isCancel = true;
       				}
    			}
  			});
		}

		long minDate = dateOptions[1];
		long maxDate = dateOptions[2];

		if(minDate > 0) {
			dialog.getDatePicker().setMinDate(minDate);
		}

		if(maxDate > 0) {
			dialog.getDatePicker().setMaxDate(maxDate);
		}

		dialog.show();	
	}

	@ReactMethod
	public void time(ReadableMap options, final Callback getTimeHandler) {
		Calendar calendar = Calendar.getInstance();
		Date defaultDate = new Date();
		long[] dateOptions = getDateOptions(options);
		defaultDate.setTime(dateOptions[0]);
		calendar.setTime(defaultDate);
		isCancel = false;
		TimePickerDialog.OnTimeSetListener timePickerListener 
            = new TimePickerDialog.OnTimeSetListener() {
        			public void onTimeSet(TimePicker view, int hours,
            						  int minutes) {
        				if(view.isShown() && !isCancel){
        					getTimeHandler.invoke(hours, minutes);
        				}        				           
        			}
		};

		Boolean is24HourView = false;
		if(options.hasKey("is24HourView")) {
			is24HourView = options.getBoolean("is24HourView");
		}

		TimePickerDialog dialog = new TimePickerDialog(mActivity, timePickerListener,
						calendar.get(Calendar.HOUR_OF_DAY),  
                        calendar.get(Calendar.MINUTE), is24HourView);

		if(options.hasKey("title")) {
			dialog.setTitle(options.getString("title"));
		}
		if(options.hasKey("okText")) {
			dialog.setButton(TimePickerDialog.BUTTON_POSITIVE, options.getString("okText"), dialog);
		}

		if(options.hasKey("cancelText")) {
			dialog.setButton(TimePickerDialog.BUTTON_NEGATIVE, options.getString("cancelText"), new DialogInterface.OnClickListener() {
    			public void onClick(DialogInterface dialog, int which) {
       				if (which == DialogInterface.BUTTON_NEGATIVE) {
          				isCancel = true;
       				}
    			}
  			});
		}
		dialog.show();	
	}

	

	@Override
	public String getName() {
		return "DialogAndroid";
	}
	
	private String getString(ReadableMap options, String key, String defaultStr) {
		if(options.hasKey(key)) {
			return options.getString(key);
		}
		return defaultStr;
	}

	private long[] getDateOptions(ReadableMap options) {
		long minDate = -1;
		long maxDate = -1;
		long defaultDate = new Date().getTime() / 1000;
		Boolean hasMin = false;
		Boolean hasMax = false;
		Boolean hasDefault = false;
		if(options.hasKey("defaultDate")) {
			defaultDate = options.getInt("defaultDate");
			hasDefault = true;
		}
		if(options.hasKey("minDate")) {
			minDate = options.getInt("minDate");
			hasMin = true;
		}
		if(options.hasKey("maxDate")) {
			maxDate = options.getInt("maxDate");
			hasMax = true;
		}

		if(!hasDefault && !hasMin && hasMax && defaultDate > maxDate) {
			defaultDate = maxDate - 1;
		}

		if(!hasDefault && hasMin && !hasMax && defaultDate < minDate) {
			defaultDate = minDate + 1;
		}

		if(hasMin && hasDefault && defaultDate <= minDate) {
			minDate = -1;
		}

		if(hasMax && hasDefault && defaultDate >= maxDate) {
			maxDate = -1;
		}

		if(hasMin && hasMax && minDate >= maxDate) {
			minDate = -1;
			maxDate = -1;
		}

		long [] opts = new long[3];
		opts[0] = defaultDate * 1000;
		opts[1] = minDate * 1000;
		opts[2] = maxDate * 1000;
		return opts;
	}
}