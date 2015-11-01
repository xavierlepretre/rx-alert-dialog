package com.github.xavierlepretre.rxdialog;

/**
 * Event identifying a button pressed on an AlertDialog.
 */
public class AlertDialogButtonEvent implements AlertDialogEvent
{
    @DialogInterfaceButton private final int which;

    public AlertDialogButtonEvent(@DialogInterfaceButton int which)
    {
        this.which = which;
    }

    @DialogInterfaceButton public int getWhich()
    {
        return which;
    }
}
