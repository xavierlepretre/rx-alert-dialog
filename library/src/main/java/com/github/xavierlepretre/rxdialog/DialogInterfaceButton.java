package com.github.xavierlepretre.rxdialog;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static android.content.DialogInterface.BUTTON_POSITIVE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Identifies a "which" button pressed on a {@link android.content.DialogInterface}.
 */
@Target({FIELD, PARAMETER, LOCAL_VARIABLE, METHOD})
@IntDef({BUTTON_POSITIVE, BUTTON_NEUTRAL, BUTTON_NEGATIVE})
@Retention(SOURCE)
public @interface DialogInterfaceButton
{
}
