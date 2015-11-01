package com.github.xavierlepretre.rxdialog.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import com.github.xavierlepretre.rxdialog.RxAlertDialogBuilder;
import com.github.xavierlepretre.rxdialog.AlertDialogButtonEvent;
import com.github.xavierlepretre.rxdialog.AlertDialogEvent;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

public class RxAlertDialog
{
    private RxAlertDialog()
    {
        throw new IllegalStateException("No instance");
    }

    public static class Builder extends RxAlertDialogBuilder<AlertDialog, Builder>
    {
        public Builder(@NonNull Context context)
        {
            super(context);
        }

        @NonNull @Override protected Builder self()
        {
            return this;
        }

        @NonNull public Observable<AlertDialogEvent> create()
        {
            return Observable.create(
                    new Observable.OnSubscribe<AlertDialogEvent>()
                    {
                        @Override
                        public void call(final Subscriber<? super AlertDialogEvent> subscriber)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle(getTitle());
                            builder.setMessage(getMessage());
                            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
                            {
                                @Override public void onClick(DialogInterface dialog, int which)
                                {
                                    subscriber.onNext(new AlertDialogButtonEvent(which));
                                    subscriber.onCompleted();
                                }
                            };
                            builder.setPositiveButton(getPositiveButton(), listener);
                            builder.setNegativeButton(getNegativeButton(), listener);
                            builder.setNeutralButton(getNeutralButton(), listener);
                            final AlertDialog dialog = builder.create();
                            subscriber.add(Subscriptions.create(new Action0()
                            {
                                @Override public void call()
                                {
                                    if (!subscriber.isUnsubscribed())
                                    {
                                        dialog.dismiss();
                                    }
                                }
                            }));
                            dialog.setOnDismissListener(new DialogInterface.OnDismissListener()
                            {
                                @Override public void onDismiss(DialogInterface dialog)
                                {
                                    subscriber.onCompleted();
                                }
                            });
                            subscriber.onNext(new AlertDialogDialogEvent(dialog));
                        }
                    })
                    .subscribeOn(AndroidSchedulers.mainThread());
        }

        @NonNull @Override public Observable<AlertDialogEvent> show()
        {
            return create()
                    .doOnNext(new Action1<AlertDialogEvent>()
                    {
                        @Override public void call(AlertDialogEvent dialogEvent)
                        {
                            if (dialogEvent instanceof AlertDialogDialogEvent)
                            {
                                ((AlertDialogDialogEvent) dialogEvent).getAlertDialog().show();
                            }
                        }
                    });
        }
    }
}
