package com.github.xavierlepretre.rxdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import rx.Observable;

abstract public class RxAlertDialogBuilder<BuilderType extends RxAlertDialogBuilder<BuilderType>>
{
    @NonNull private final Context context;
    @Nullable private String title;
    @Nullable private String message;
    @Nullable private String positiveButton;
    @Nullable private String negativeButton;
    @Nullable private String neutralButton;
    @Nullable private Boolean cancellable;
    @Nullable private Boolean canceledOnTouchOutside;

    public RxAlertDialogBuilder(@NonNull Context context)
    {
        this.context = context;
    }

    @NonNull protected Context getContext()
    {
        return context;
    }

    @NonNull public BuilderType title(@Nullable String title)
    {
        this.title = title;
        return self();
    }

    @NonNull public BuilderType titleRes(@StringRes int titleRes)
    {
        this.title = context.getString(titleRes);
        return self();
    }

    @Nullable protected String getTitle()
    {
        return title;
    }

    @NonNull public BuilderType message(@Nullable String message)
    {
        this.message = message;
        return self();
    }

    @NonNull public BuilderType messageRes(@StringRes int messageRes)
    {
        this.message = context.getString(messageRes);
        return self();
    }

    @Nullable public String getMessage()
    {
        return message;
    }

    @NonNull public BuilderType positiveButton(@Nullable String positiveButton)
    {
        this.positiveButton = positiveButton;
        return self();
    }

    @NonNull public BuilderType positiveButtonRes(@StringRes int positiveButtonRes)
    {
        this.positiveButton = context.getString(positiveButtonRes);
        return self();
    }

    @Nullable protected String getPositiveButton()
    {
        return positiveButton;
    }

    @NonNull public BuilderType negativeButton(@Nullable String negativeButton)
    {
        this.negativeButton = negativeButton;
        return self();
    }

    @NonNull public BuilderType negativeButtonRes(@StringRes int negativeButtonRes)
    {
        this.negativeButton = context.getString(negativeButtonRes);
        return self();
    }

    @Nullable protected String getNegativeButton()
    {
        return negativeButton;
    }

    @NonNull public BuilderType neutralButton(@Nullable String neutralButton)
    {
        this.neutralButton = neutralButton;
        return self();
    }

    @NonNull public BuilderType neutralButtonRes(@StringRes int neutralButtonRes)
    {
        this.neutralButton = context.getString(neutralButtonRes);
        return self();
    }

    @Nullable public String getNeutralButton()
    {
        return neutralButton;
    }

    @NonNull public BuilderType cancellable(@Nullable Boolean cancellable)
    {
        this.cancellable = cancellable;
        return self();
    }

    @Nullable public Boolean getCancellable()
    {
        return cancellable;
    }

    @NonNull public BuilderType canceledOnTouchOutside(@Nullable Boolean canceledOnTouchOutside)
    {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
        return self();
    }

    @Nullable public Boolean getCanceledOnTouchOutside()
    {
        return canceledOnTouchOutside;
    }

    @NonNull abstract protected BuilderType self();

    @NonNull abstract public Observable<AlertDialogEvent> create();

    @NonNull abstract public Observable<AlertDialogEvent> show();
}
