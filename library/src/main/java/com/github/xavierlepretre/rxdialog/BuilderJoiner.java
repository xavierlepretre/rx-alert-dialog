package com.github.xavierlepretre.rxdialog;

import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

/**
 * This is a pretend interface that repeats the existing methods on
 * AlertDialog.Builder in Android and Support.
 */
public interface BuilderJoiner
{
    @NonNull BuilderJoiner setTitle(@Nullable CharSequence title);

    @NonNull BuilderJoiner setTitle(@StringRes int titleRes);

    @NonNull BuilderJoiner setMessage(@Nullable CharSequence message);

    @NonNull BuilderJoiner setMessage(@StringRes int messageRes);

    @NonNull BuilderJoiner setIcon(@DrawableRes int icon);

    @NonNull BuilderJoiner setIcon(@Nullable Drawable icon);

    @NonNull BuilderJoiner setIconAttribute(@AttrRes int iconAttribute);

    @NonNull BuilderJoiner setPositiveButton(
            @Nullable CharSequence text,
            @Nullable final OnClickListener listener);

    @NonNull BuilderJoiner setPositiveButton(
            @StringRes int textRes,
            @Nullable final OnClickListener listener);

    @NonNull BuilderJoiner setNegativeButton(
            @Nullable CharSequence text,
            @Nullable final OnClickListener listener);

    @NonNull BuilderJoiner setNegativeButton(
            @StringRes int textRes,
            @Nullable final OnClickListener listener);

    @NonNull BuilderJoiner setNeutralButton(
            @Nullable CharSequence text,
            @Nullable final OnClickListener listener);

    @NonNull BuilderJoiner setNeutralButton(
            @StringRes int textRes,
            @Nullable final OnClickListener listener);

    @NonNull BuilderJoiner setCancelable(boolean cancelable);

    @NonNull Dialog create();
}
