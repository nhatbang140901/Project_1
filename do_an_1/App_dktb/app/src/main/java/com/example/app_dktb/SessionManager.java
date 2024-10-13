package com.example.app_dktb;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    private static String TAG = SessionManager.class.getName();

    SharedPreferences preferences;
    Context context;
    SharedPreferences.Editor editor;
    private static final String NAME = "android_session";
    private static final String KEY_LOGIN = "islogin";

    public SessionManager(Context context){
        Log.i(TAG, "SessionManager:eqwwwww "+ TAG);
        this.context = context;
        preferences = context.getSharedPreferences(NAME,MODE_PRIVATE);
        editor = preferences.edit();
    }
    public void SetLogin(boolean isLogin){
        editor.putBoolean(KEY_LOGIN,isLogin);
        editor.commit();
    }
    public boolean Check(){
        return preferences.getBoolean(KEY_LOGIN,false);
    }
}

