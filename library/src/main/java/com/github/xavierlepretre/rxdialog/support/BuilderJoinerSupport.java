package com.github.xavierlepretre.rxdialog.support;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.github.xavierlepretre.rxdialog.BuilderJoiner;

public class BuilderJoinerSupport extends AlertDialog.Builder
    implements BuilderJoiner
{
    public BuilderJoinerSupport(Context context)
    {
        super(context);
    }

    @TargetApi(VERSION_CODES.HONEYCOMB)
    public BuilderJoinerSupport(Context context, int themeResId)
    {
        super(context, themeResId);
    }

    @NonNull @Override
    public BuilderJoinerSupport setTitle(@Nullable CharSequence title)
    {
        return (BuilderJoinerSupport) super.setTitle(title);
    }

    @NonNull @Override
    public BuilderJoinerSupport setMessage(@Nullable CharSequence message)
    {
        return (BuilderJoinerSupport) super.setMessage(message);
    }

    @NonNull @Override
    public BuilderJoinerSupport setPositiveButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerSupport) super.setPositiveButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerSupport setNegativeButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerSupport) super.setNegativeButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerSupport setNeutralButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerSupport) super.setNeutralButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerSupport setCancelable(boolean cancelable)
    {
        return (BuilderJoinerSupport) super.setCancelable(cancelable);
    }
}
