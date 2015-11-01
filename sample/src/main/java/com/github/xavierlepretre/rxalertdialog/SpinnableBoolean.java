package com.github.xavierlepretre.rxalertdialog;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public enum SpinnableBoolean
{
    NULL(null, "null"),
    TRUE(true, "true"),
    FALSE(false, "false");

    @Nullable private final Boolean value;
    @NonNull private final String display;

    SpinnableBoolean(
            @Nullable Boolean value,
            @NonNull String display)
    {
        this.value = value;
        this.display = display;
    }

    @Nullable public Boolean getValue()
    {
        return value;
    }

    @Override public String toString()
    {
        return display;
    }

    @NonNull public static SpinnableBoolean[] getAll()
    {
        return new SpinnableBoolean[] {NULL, TRUE, FALSE};
    }
}
