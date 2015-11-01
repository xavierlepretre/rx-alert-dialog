package com.github.xavierlepretre.rxdialog.support;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import com.github.xavierlepretre.rxdialog.AlertDialogEvent;

/**
 * Event carrying the created AlertDialog.
 */
public class AlertDialogSupportDialogEvent implements AlertDialogEvent
{
    @NonNull private final AlertDialog alertDialog;

    public AlertDialogSupportDialogEvent(@NonNull AlertDialog alertDialog)
    {
        this.alertDialog = alertDialog;
    }

    @NonNull public AlertDialog getAlertDialog()
    {
        return alertDialog;
    }
}
