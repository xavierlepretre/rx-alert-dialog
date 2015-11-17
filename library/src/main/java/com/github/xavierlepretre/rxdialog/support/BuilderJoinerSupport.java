package com.github.xavierlepretre.rxdialog.support;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION_CODES;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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

    @NonNull @Override public BuilderJoinerSupport setTitle(@StringRes int titleId)
    {
        return (BuilderJoinerSupport) super.setTitle(titleId);
    }

    @NonNull @Override
    public BuilderJoinerSupport setMessage(@Nullable CharSequence message)
    {
        return (BuilderJoinerSupport) super.setMessage(message);
    }

    @NonNull @Override public BuilderJoinerSupport setMessage(@StringRes int messageId)
    {
        return (BuilderJoinerSupport) super.setMessage(messageId);
    }

    @NonNull @Override public BuilderJoinerSupport setIcon(@DrawableRes int iconId)
    {
        return (BuilderJoinerSupport) super.setIcon(iconId);
    }

    @NonNull @Override public BuilderJoinerSupport setIcon(@Nullable Drawable icon)
    {
        return (BuilderJoinerSupport) super.setIcon(icon);
    }

    @NonNull @Override public BuilderJoinerSupport setIconAttribute(@AttrRes int iconAttribute)
    {
        return (BuilderJoinerSupport) super.setIconAttribute(iconAttribute);
    }

    @NonNull @Override
    public BuilderJoinerSupport setPositiveButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerSupport) super.setPositiveButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerSupport setPositiveButton(
            @StringRes int textId,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerSupport) super.setPositiveButton(textId, listener);
    }

    @NonNull @Override
    public BuilderJoinerSupport setNegativeButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerSupport) super.setNegativeButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerSupport setNegativeButton(
            @StringRes int textId,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerSupport) super.setNegativeButton(textId, listener);
    }

    @NonNull @Override
    public BuilderJoinerSupport setNeutralButton(
            @Nullable CharSequence text,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerSupport) super.setNeutralButton(text, listener);
    }

    @NonNull @Override
    public BuilderJoinerSupport setNeutralButton(
            @StringRes int textId,
            @Nullable OnClickListener listener)
    {
        return (BuilderJoinerSupport) super.setNeutralButton(textId, listener);
    }

    @NonNull @Override
    public BuilderJoinerSupport setCancelable(boolean cancelable)
    {
        return (BuilderJoinerSupport) super.setCancelable(cancelable);
    }
}
