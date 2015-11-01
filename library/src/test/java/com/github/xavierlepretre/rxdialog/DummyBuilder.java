package com.github.xavierlepretre.rxdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import rx.Observable;

public class DummyBuilder extends RxAlertDialogBuilder<DummyBuilder>
{
    public DummyBuilder(@NonNull Context context)
    {
        super(context);
    }

    @NonNull @Override protected DummyBuilder self()
    {
        return this;
    }

    @NonNull @Override public Observable<AlertDialogEvent> create()
    {
        return Observable.empty();
    }

    @NonNull @Override public Observable<AlertDialogEvent> show()
    {
        return Observable.empty();
    }
}
