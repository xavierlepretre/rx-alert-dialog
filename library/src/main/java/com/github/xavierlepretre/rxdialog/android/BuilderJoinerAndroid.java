package com.github.xavierlepretre.rxdialog.android;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.xavierlepretre.rxdialog.BuilderJoiner;

public class BuilderJoinerAndroid extends AlertDialog.Builder
    implements BuilderJoiner
{
    public BuilderJoinerAndroid(Context context)
    {
        super(context);
    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    public BuilderJoinerAndroid(Context context, int themeResId)
    {
        super(context, themeResId);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setTitle(@Nullable CharSequence title)
    {
        return (BuilderJoinerAndroid) super.setTitle(title);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setMessage(@Nullable CharSequence message)
    {
        return (BuilderJoinerAndroid) super.setMessage(message);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setPositiveButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerAndroid) super.setPositiveButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setNegativeButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerAndroid) super.setNegativeButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setNeutralButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerAndroid) super.setNeutralButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setCancelable(boolean cancelable)
    {
        return (BuilderJoinerAndroid) super.setCancelable(cancelable);
    }
}
