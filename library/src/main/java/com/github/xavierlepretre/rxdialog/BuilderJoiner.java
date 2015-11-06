package com.github.xavierlepretre.rxdialog;

import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * This is a pretend interface that repeats the existing methods on
 * AlertDialog.Builder in Android and Support.
 */
public interface BuilderJoiner
{
    @NonNull BuilderJoiner setTitle(@Nullable CharSequence title);

    @NonNull BuilderJoiner setMessage(@Nullable CharSequence message);

    @NonNull BuilderJoiner setPositiveButton(
            @Nullable CharSequence text,
            @Nullable final OnClickListener listener);

    @NonNull BuilderJoiner setNegativeButton(
            @Nullable CharSequence text,
            @Nullable final OnClickListener listener);

    @NonNull BuilderJoiner setNeutralButton(
            @Nullable CharSequence text,
            @Nullable final OnClickListener listener);

    @NonNull BuilderJoiner setCancelable(boolean cancelable);

    @NonNull Dialog create();
}
