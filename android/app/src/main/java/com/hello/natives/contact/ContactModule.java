package com.hello.natives.contact;

import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.net.Uri;
import android.database.Cursor;
import android.content.ContentResolver;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;

import com.hello.activityresult.ActivityResultHandler;
import com.hello.MainActivity;

public class ContactModule extends BaseJavaModule implements ActivityResultHandler {

	final private int REQUEST_CODE = 1;

	private Activity mActivity;
	private Callback mCallback;

	public ContactModule(MainActivity currentActivity) {
		super();
		mActivity = currentActivity;
		currentActivity.getActivityResultManager().put(REQUEST_CODE, this);
	}

	@ReactMethod
	public void selectContact(Callback cb) {
		mCallback = cb;
		Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		mActivity.startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	public String getName() {
		return "ContactAndroid";
	}

	@Override
	public void handleActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
            Uri contactData = data.getData();
            Cursor cur = mActivity.managedQuery(contactData, null, null, null, null);
            ContentResolver contactResolver = mActivity.getContentResolver();

            if (cur.moveToFirst()) {
                String id = cur.getString(cur.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                String name = "";
                String phone = "";

                Cursor phoneCur = contactResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);

                if (phoneCur.moveToFirst()) {
                    name = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    phone = phoneCur.getString(phoneCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                	mCallback.invoke(name, phone);
                }

                id = null;
                name = null;
                phone = null;
                phoneCur = null;
            }
            contactResolver = null;
            cur = null;
        }
	}
}