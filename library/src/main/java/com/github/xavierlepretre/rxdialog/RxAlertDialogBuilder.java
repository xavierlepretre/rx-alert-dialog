package com.github.xavierlepretre.rxdialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import rx.Observable;

abstract public class RxAlertDialogBuilder<BuilderType extends RxAlertDialogBuilder<BuilderType>>
{
    @NonNull private final Context context;
    @Nullable private String title;
    @Nullable @StringRes private Integer titleRes;
    @Nullable private String message;
    @Nullable @StringRes private Integer messageRes;
    @Nullable @DrawableRes private Integer iconRes;
    @Nullable private Drawable icon;
    @Nullable private String positiveButton;
    @Nullable @StringRes private Integer positiveButtonRes;
    @Nullable private String negativeButton;
    @Nullable @StringRes private Integer negativeButtonRes;
    @Nullable private String neutralButton;
    @Nullable @StringRes private Integer neutralButtonRes;
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

    @Nullable protected String getTitle()
    {
        return title;
    }

    @NonNull public BuilderType title(@StringRes @Nullable Integer titleRes)
    {
        this.titleRes = titleRes;
        return self();
    }

    @Nullable @StringRes public Integer getTitleRes()
    {
        return titleRes;
    }

    @NonNull public BuilderType message(@Nullable String message)
    {
        this.message = message;
        return self();
    }

    @Nullable public String getMessage()
    {
        return message;
    }

    @NonNull public BuilderType message(@StringRes @Nullable Integer messageRes)
    {
        this.messageRes = messageRes;
        return self();
    }

    @Nullable @StringRes public Integer getMessageRes()
    {
        return messageRes;
    }

    @NonNull public BuilderType icon(@DrawableRes @Nullable Integer icon)
    {
        this.iconRes = icon;
        return self();
    }

    @Nullable @DrawableRes public Integer getIconRes()
    {
        return iconRes;
    }

    @NonNull public BuilderType icon(@Nullable Drawable icon)
    {
        this.icon = icon;
        return self();
    }

    @Nullable public Drawable getIcon()
    {
        return icon;
    }

    @NonNull public BuilderType positiveButton(@Nullable String positiveButton)
    {
        this.positiveButton = positiveButton;
        return self();
    }

    @Nullable protected String getPositiveButton()
    {
        return positiveButton;
    }

    @NonNull public BuilderType positiveButton(@StringRes @Nullable Integer positiveButtonRes)
    {
        this.positiveButtonRes = positiveButtonRes;
        return self();
    }

    @Nullable @StringRes public Integer getPositiveButtonRes()
    {
        return positiveButtonRes;
    }

    @NonNull public BuilderType negativeButton(@Nullable String negativeButton)
    {
        this.negativeButton = negativeButton;
        return self();
    }

    @Nullable protected String getNegativeButton()
    {
        return negativeButton;
    }

    @NonNull public BuilderType negativeButton(@StringRes @Nullable Integer negativeButtonRes)
    {
        this.negativeButtonRes = negativeButtonRes;
        return self();
    }

    @Nullable @StringRes public Integer getNegativeButtonRes()
    {
        return negativeButtonRes;
    }

    @NonNull public BuilderType neutralButton(@Nullable String neutralButton)
    {
        this.neutralButton = neutralButton;
        return self();
    }

    @Nullable public String getNeutralButton()
    {
        return neutralButton;
    }

    @NonNull public BuilderType neutralButton(@StringRes @Nullable Integer neutralButtonRes)
    {
        this.neutralButtonRes = neutralButtonRes;
        return self();
    }

    @Nullable @StringRes public Integer getNeutralButtonRes()
    {
        return neutralButtonRes;
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
