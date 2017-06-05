package com.yang.thirdapp.db;

import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.UserDictionary;

/**
 * 用户合约类
 * 定义 authority,column,contentUri,需要确认的权限等
 * 参考{@link UserDictionary}
 * Created by yangjiajia on 2017/5/27.
 */

public final class UserContract {
    private static final String TAG = "UserContract";
    public static final String AUTHORITY = "com.testandroid.yang.provider.user";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static class Users implements BaseColumns {

        public static final String TABLE_NAME = "user";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

//        /**
//         * The MIME type of {@link #CONTENT_URI} providing a directory of words.
//         */
//        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.userword";
//
//        /**
//         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single word.
//         */
//        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.userword";

        public static String _ID = BaseColumns._ID;
        public static String USER_NAME = "user_name";
        public static String AGE = "age";
        public static String PHONE_NUMBER = "phone_number";

    }
}
