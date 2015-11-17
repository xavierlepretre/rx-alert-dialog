package com.github.xavierlepretre.rxdialog.android;

import com.github.xavierlepretre.rxdialog.BuilderJoiner;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION_CODES;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

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

    @NonNull @Override public BuilderJoinerAndroid setTitle(@StringRes int titleId)
    {
        return (BuilderJoinerAndroid) super.setTitle(titleId);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setMessage(@Nullable CharSequence message)
    {
        return (BuilderJoinerAndroid) super.setMessage(message);
    }

    @NonNull @Override public BuilderJoinerAndroid setMessage(@StringRes int messageId)
    {
        return (BuilderJoinerAndroid) super.setMessage(messageId);
    }

    @NonNull @Override public BuilderJoinerAndroid setIcon(@DrawableRes int iconId)
    {
        return (BuilderJoinerAndroid) super.setIcon(iconId);
    }

    @NonNull @Override public BuilderJoinerAndroid setIcon(@Nullable Drawable icon)
    {
        return (BuilderJoinerAndroid) super.setIcon(icon);
    }

    @NonNull @Override public BuilderJoinerAndroid setIconAttribute(@AttrRes int iconAttribute)
    {
        return (BuilderJoinerAndroid) super.setIconAttribute(iconAttribute);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setPositiveButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerAndroid) super.setPositiveButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setPositiveButton(
            @StringRes int textId,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerAndroid) super.setPositiveButton(textId, listener);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setNegativeButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerAndroid) super.setNegativeButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setNegativeButton(
            @StringRes int textId,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerAndroid) super.setNegativeButton(textId, listener);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setNeutralButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerAndroid) super.setNeutralButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setNeutralButton(
            @StringRes int textId,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerAndroid) super.setNeutralButton(textId, listener);
    }

    @NonNull @Override
    public BuilderJoinerAndroid setCancelable(boolean cancelable)
    {
        return (BuilderJoinerAndroid) super.setCancelable(cancelable);
    }
}
