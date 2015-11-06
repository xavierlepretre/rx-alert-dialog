package com.github.xavierlepretre.rxdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * This builder lets us have all similar calls in one place.
 * It will help reduce the number of future omissions.
 */
public class AlertDialogBuilderWrapper
{
    @NonNull private final BuilderJoiner joiner;
    @NonNull private final RxAlertDialogBuilder rxBuilder;

    public AlertDialogBuilderWrapper(
            @NonNull BuilderJoiner joiner,
            @NonNull RxAlertDialogBuilder rxBuilder)
    {
        this.joiner = joiner;
        this.rxBuilder = rxBuilder;
    }

    public void create(@NonNull final Subscriber<? super AlertDialogEvent> subscriber)
    {
        joiner.setTitle(rxBuilder.getTitle());
        joiner.setMessage(rxBuilder.getMessage());

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
        {
            @Override public void onClick(DialogInterface dialog, int which)
            {
                subscriber.onNext(new AlertDialogButtonEvent(which));
                subscriber.onCompleted();
            }
        };
        joiner.setPositiveButton(rxBuilder.getPositiveButton(), listener);
        joiner.setNegativeButton(rxBuilder.getNegativeButton(), listener);
        joiner.setNeutralButton(rxBuilder.getNeutralButton(), listener);

        if (rxBuilder.getCancellable() != null)
        {
            joiner.setCancelable(rxBuilder.getCancellable());
        }

        final Dialog created = joiner.create();

        subscriber.add(Subscriptions.create(new Action0()
        {
            @Override public void call()
            {
                if (!subscriber.isUnsubscribed())
                {
                    created.dismiss();
                }
            }
        }));

        if (rxBuilder.getCanceledOnTouchOutside() != null)
        {
            created.setCanceledOnTouchOutside(rxBuilder.getCanceledOnTouchOutside());
        }

        DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener()
        {
            @Override public void onDismiss(DialogInterface dialog)
            {
                subscriber.onCompleted();
            }
        };
        created.setOnDismissListener(dismissListener);

        subscriber.onNext(new AlertDialogDialogEvent(created));
    }
}
