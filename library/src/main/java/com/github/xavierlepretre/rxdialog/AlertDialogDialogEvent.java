package com.github.xavierlepretre.rxdialog;

import android.app.Dialog;
import android.support.annotation.NonNull;

/**
 * Event carrying the created Dialog.
 */
public class AlertDialogDialogEvent implements AlertDialogEvent
{
    @NonNull private final Dialog dialog;

    public AlertDialogDialogEvent(@NonNull Dialog dialog)
    {
        this.dialog = dialog;
    }

    @NonNull public Dialog getDialog()
    {
        return dialog;
    }
}
