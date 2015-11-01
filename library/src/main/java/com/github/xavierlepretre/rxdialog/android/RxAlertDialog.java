package com.github.xavierlepretre.rxdialog.android;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import com.github.xavierlepretre.rxdialog.AlertDialogBuilderWrapper;
import com.github.xavierlepretre.rxdialog.AlertDialogEvent;
import com.github.xavierlepretre.rxdialog.RxAlertDialogBuilder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RxAlertDialog
{
    private RxAlertDialog()
    {
        throw new IllegalStateException("No instance");
    }

    public static class Builder extends RxAlertDialogBuilder<Builder>
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
                            new AlertDialogBuilderWrapper(
                                    new AlertDialog.Builder(getContext()),
                                    Builder.this,
                                    subscriber)
                                    .create();
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
