package com.github.xavierlepretre.rxdialog;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.github.xavierlepretre.rxdialog.android.AlertDialogDialogEvent;
import com.github.xavierlepretre.rxdialog.support.AlertDialogSupportDialogEvent;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * This builder lets us have all similar calls in one place.
 * It will help reduce the number of future omissions.
 */
public class AlertDialogBuilderWrapper
{
    @Nullable private final android.app.AlertDialog.Builder android;
    @Nullable private final android.support.v7.app.AlertDialog.Builder support;
    @NonNull private final RxAlertDialogBuilder rxBuilder;
    @NonNull private final Subscriber<? super AlertDialogEvent> subscriber;

    public AlertDialogBuilderWrapper(
            @SuppressWarnings("NullableProblems") @NonNull android.app.AlertDialog.Builder android,
            @NonNull RxAlertDialogBuilder rxBuilder,
            @NonNull Subscriber<? super AlertDialogEvent> subscriber)
    {
        this.android = android;
        this.support = null;
        this.rxBuilder = rxBuilder;
        this.subscriber = subscriber;
    }

    public AlertDialogBuilderWrapper(
            @SuppressWarnings("NullableProblems") @NonNull android.support.v7.app.AlertDialog.Builder support,
            @NonNull RxAlertDialogBuilder rxBuilder,
            @NonNull Subscriber<? super AlertDialogEvent> subscriber)
    {
        this.android = null;
        this.support = support;
        this.rxBuilder = rxBuilder;
        this.subscriber = subscriber;
    }

    public void create()
    {
        if (android != null)
        {
            android.setTitle(rxBuilder.getTitle());
        }
        else if (support != null)
        {
            support.setTitle(rxBuilder.getTitle());
        }
        if (android != null)
        {
            android.setMessage(rxBuilder.getMessage());
        }
        else if (support != null)
        {
            support.setMessage(rxBuilder.getMessage());
        }

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
        {
            @Override public void onClick(DialogInterface dialog, int which)
            {
                subscriber.onNext(new AlertDialogButtonEvent(which));
                subscriber.onCompleted();
            }
        };
        if (android != null)
        {
            android.setPositiveButton(rxBuilder.getPositiveButton(), listener);
        }
        else if (support != null)
        {
            support.setPositiveButton(rxBuilder.getPositiveButton(), listener);
        }

        if (android != null)
        {
            android.setNegativeButton(rxBuilder.getNegativeButton(), listener);
        }
        else if (support != null)
        {
            support.setNegativeButton(rxBuilder.getNegativeButton(), listener);
        }

        if (android != null)
        {
            android.setNeutralButton(rxBuilder.getNeutralButton(), listener);
        }
        else if (support != null)
        {
            support.setNeutralButton(rxBuilder.getNeutralButton(), listener);
        }

        if (android != null && rxBuilder.getCancellable() != null)
        {
            android.setCancelable(rxBuilder.getCancellable());
        }
        else if (support != null && rxBuilder.getCancellable() != null)
        {
            support.setCancelable(rxBuilder.getCancellable());
        }

        final android.app.AlertDialog androidDialog;
        final android.support.v7.app.AlertDialog supportDialog;
        if (android != null)
        {
            androidDialog = android.create();
            supportDialog = null;
        }
        else if (support != null)
        {
            androidDialog = null;
            supportDialog = support.create();
        }
        else
        {
            androidDialog = null;
            supportDialog = null;
        }

        subscriber.add(Subscriptions.create(new Action0()
        {
            @Override public void call()
            {
                if (!subscriber.isUnsubscribed())
                {
                    if (androidDialog != null)
                    {
                        androidDialog.dismiss();
                    }
                    else if (supportDialog != null)
                    {
                        supportDialog.dismiss();
                    }
                }
            }
        }));

        if (rxBuilder.getCanceledOnTouchOutside() != null)
        {
            if (androidDialog != null)
            {
                androidDialog.setCanceledOnTouchOutside(rxBuilder.getCanceledOnTouchOutside());
            }
            else if (supportDialog != null)
            {
                supportDialog.setCanceledOnTouchOutside(rxBuilder.getCanceledOnTouchOutside());
            }
        }

        DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener()
        {
            @Override public void onDismiss(DialogInterface dialog)
            {
                subscriber.onCompleted();
            }
        };
        if (androidDialog != null)
        {
            androidDialog.setOnDismissListener(dismissListener);
        }
        else if (supportDialog != null)
        {
            supportDialog.setOnDismissListener(dismissListener);
        }

        if (androidDialog != null)
        {
            subscriber.onNext(new AlertDialogDialogEvent(androidDialog));
        }
        else if (supportDialog != null)
        {
            subscriber.onNext(new AlertDialogSupportDialogEvent(supportDialog));
        }
    }
}
