package com.github.xavierlepretre.rxdialog.android;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import com.github.xavierlepretre.rxdialog.AlertDialogEvent;

/**
 * Event carrying the created AlertDialog.
 */
public class AlertDialogDialogEvent implements AlertDialogEvent
{
    @NonNull private final AlertDialog alertDialog;

    public AlertDialogDialogEvent(@NonNull AlertDialog alertDialog)
    {
        this.alertDialog = alertDialog;
    }

    @NonNull public AlertDialog getAlertDialog()
    {
        return alertDialog;
    }
}
