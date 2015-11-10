package com.github.xavierlepretre.rxdialog.support;

import com.github.xavierlepretre.rxdialog.AlertDialogBuilderOnSubscribe;
import com.github.xavierlepretre.rxdialog.AlertDialogDialogEvent;
import com.github.xavierlepretre.rxdialog.AlertDialogEvent;
import com.github.xavierlepretre.rxdialog.RxAlertDialogBuilder;

import android.content.Context;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class RxAlertDialogSupport
{
    private RxAlertDialogSupport()
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
            return Observable.create(new AlertDialogBuilderOnSubscribe(
                    new BuilderJoinerSupport(getContext()),
                    Builder.this))
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
                                ((AlertDialogDialogEvent) dialogEvent).getDialog().show();
                            }
                        }
                    });
        }
    }
}
