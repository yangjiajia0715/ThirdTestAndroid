package com.yang.thirdapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * SelectFileActivity
 * Created by yangjiajia on 2017/7/24.
 */

public class SelectFileActivity extends ListActivity {
    private static final String TAG = "SelectFileActivity";
    private List<String> list;
    private ArrayAdapter<String> adapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, SelectFileActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName() + "onCreate: getTaskId =" + getTaskId());

        ListView listView = getListView();
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        list = new ArrayList<>();
        list.add("跳转分享文件");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

//        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraPhoto));
//        startActivityForResult(takePhotoIntent, REQUEST_TAKE_PHOTO);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0:
//                SharedPreferences sp = getPreferences(MODE_PRIVATE);
//                String uriStr = sp.getString("uri", "");
//                Log.d(TAG, "onListItemClick: uriStr=" + uriStr);
//                if (TextUtils.isEmpty(uriStr)) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 0);
//                } else {//测试撤销 管用  revokeUriPermission();
//                    Uri uri = Uri.parse(uriStr);
//                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//                    Log.d(TAG, "onListItemClick: cursor=" + cursor);
//                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case 0:
                Uri uri = data.getData();
                String type = getContentResolver().getType(uri);
                Log.d(TAG, "onActivityResult: uri=" + uri);
                Log.d(TAG, "onActivityResult: type=" + type);
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                Log.d(TAG, "onActivityResult: cursor=" + cursor);
                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        /*
                         * Get the column indexes of the data in the Cursor,
                         * move to the first row in the Cursor, get the data,
                         * and display it.
                         */
                        int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                        int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);

                        long cursorLong = cursor.getLong(sizeIndex);
                        String string = cursor.getString(nameIndex);

                        Log.d(TAG, "onActivityResult: formatFileSize=" + Formatter.formatFileSize(this, cursorLong));

                        list.add("选择的文件名=" + string + " Size=" + Formatter.formatShortFileSize(this, cursorLong));
                    }
                    adapter.notifyDataSetChanged();
                }

                if (cursor != null) {
                    cursor.close();
                }

                SharedPreferences sp = getPreferences(MODE_PRIVATE);
                sp.edit().putString("uri", uri.toString()).apply();
                break;
        }
    }
}
