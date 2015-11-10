package com.github.xavierlepretre.rxdialog;

import org.junit.Before;
import org.junit.Test;

import android.content.Context;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlertDialogBuilderTest
{
    private Context context;
    private RxAlertDialogBuilder builder;

    @Before
    public void setUp()
    {
        this.context = mock(Context.class);
        this.builder = new DummyBuilder(context);
    }

    @Test
    public void setTitleString_setsString_redRemainsNull() throws Exception
    {
        builder.title("title1");
        assertThat(builder.getTitle()).isEqualTo("title1");
        assertThat(builder.getTitleRes()).isNull();

        builder.title((String) null);
        assertThat(builder.getTitle()).isNull();
        assertThat(builder.getTitleRes()).isNull();
    }

    @Test
    public void setTitleRes_setsRes_stringRemainsNull() throws Exception
    {
        when(context.getString(android.R.string.ok)).thenReturn("title1");

        builder.title(android.R.string.ok);
        assertThat(builder.getTitle()).isNull();
        assertThat(builder.getTitleRes()).isEqualTo(android.R.string.ok);

        builder.title((Integer) null);
        assertThat(builder.getTitle()).isNull();
        assertThat(builder.getTitleRes()).isNull();
    }

    @Test
    public void setTitleAndRes_remainSeparate() throws Exception
    {
        when(context.getString(android.R.string.ok)).thenReturn("title1");

        builder.title("title2");
        builder.title(android.R.string.ok);
        assertThat(builder.getTitle()).isEqualTo("title2");
        assertThat(builder.getTitleRes()).isEqualTo(android.R.string.ok);
    }

    @Test
    public void setMessageString_setsString_redRemainsNull() throws Exception
    {
        builder.message("message1");
        assertThat(builder.getMessage()).isEqualTo("message1");
        assertThat(builder.getMessageRes()).isNull();

        builder.message((String) null);
        assertThat(builder.getMessage()).isNull();
        assertThat(builder.getMessageRes()).isNull();
    }

    @Test
    public void setMessageRes_setsRes_stringRemainsNull() throws Exception
    {
        when(context.getString(android.R.string.ok)).thenReturn("message1");

        builder.message(android.R.string.ok);
        assertThat(builder.getMessage()).isNull();
        assertThat(builder.getMessageRes()).isEqualTo(android.R.string.ok);

        builder.message((Integer) null);
        assertThat(builder.getMessage()).isNull();
        assertThat(builder.getMessageRes()).isNull();
    }

    @Test
    public void setMessageAndRes_remainSeparate() throws Exception
    {
        when(context.getString(android.R.string.ok)).thenReturn("message1");

        builder.message("message2");
        builder.message(android.R.string.ok);
        assertThat(builder.getMessage()).isEqualTo("message2");
        assertThat(builder.getMessageRes()).isEqualTo(android.R.string.ok);
    }

    @Test
    public void setPositiveButtonString_setsString_redRemainsNull() throws Exception
    {
        builder.positiveButton("button1");
        assertThat(builder.getPositiveButton()).isEqualTo("button1");
        assertThat(builder.getPositiveButtonRes()).isNull();

        builder.positiveButton((String) null);
        assertThat(builder.getPositiveButton()).isNull();
        assertThat(builder.getPositiveButtonRes()).isNull();
    }

    @Test
    public void setPositiveButtonRes_setsRes_stringRemainsNull() throws Exception
    {
        when(context.getString(android.R.string.ok)).thenReturn("button1");

        builder.positiveButton(android.R.string.ok);
        assertThat(builder.getPositiveButton()).isNull();
        assertThat(builder.getPositiveButtonRes()).isEqualTo(android.R.string.ok);

        builder.positiveButton((Integer) null);
        assertThat(builder.getPositiveButton()).isNull();
        assertThat(builder.getPositiveButtonRes()).isNull();
    }

    @Test
    public void setPositiveButtonAndRes_remainSeparate() throws Exception
    {
        when(context.getString(android.R.string.ok)).thenReturn("button1");

        builder.positiveButton("button2");
        builder.positiveButton(android.R.string.ok);
        assertThat(builder.getPositiveButton()).isEqualTo("button2");
        assertThat(builder.getPositiveButtonRes()).isEqualTo(android.R.string.ok);
    }

    @Test
    public void setNegativeButtonString_setsString_redRemainsNull() throws Exception
    {
        builder.negativeButton("button2");
        assertThat(builder.getNegativeButton()).isEqualTo("button2");
        assertThat(builder.getNegativeButtonRes()).isNull();

        builder.negativeButton((String) null);
        assertThat(builder.getNegativeButton()).isNull();
        assertThat(builder.getNegativeButtonRes()).isNull();
    }

    @Test
    public void setNegativeButtonRes_setsRes_stringRemainsNull() throws Exception
    {
        when(context.getString(android.R.string.yes)).thenReturn("button2");

        builder.negativeButton(android.R.string.yes);
        assertThat(builder.getNegativeButton()).isNull();
        assertThat(builder.getNegativeButtonRes()).isEqualTo(android.R.string.yes);

        builder.negativeButton((Integer) null);
        assertThat(builder.getNegativeButton()).isNull();
        assertThat(builder.getNegativeButtonRes()).isNull();
    }

    @Test
    public void setNegativeButtonAndRes_remainSeparate() throws Exception
    {
        when(context.getString(android.R.string.yes)).thenReturn("button2");

        builder.negativeButton("button3");
        builder.negativeButton(android.R.string.yes);
        assertThat(builder.getNegativeButton()).isEqualTo("button3");
        assertThat(builder.getNegativeButtonRes()).isEqualTo(android.R.string.yes);
    }

    @Test
    public void setNeutralButtonString_setsString_redRemainsNull() throws Exception
    {
        builder.neutralButton("button3");
        assertThat(builder.getNeutralButton()).isEqualTo("button3");
        assertThat(builder.getNeutralButtonRes()).isNull();

        builder.neutralButton((String) null);
        assertThat(builder.getNeutralButton()).isNull();
        assertThat(builder.getNeutralButtonRes()).isNull();
    }

    @Test
    public void setNeutralButtonRes_setsRes_stringRemainsNull() throws Exception
    {
        when(context.getString(android.R.string.yes)).thenReturn("button3");

        builder.neutralButton(android.R.string.yes);
        assertThat(builder.getNeutralButton()).isNull();
        assertThat(builder.getNeutralButtonRes()).isEqualTo(android.R.string.yes);

        builder.neutralButton((Integer) null);
        assertThat(builder.getNeutralButton()).isNull();
        assertThat(builder.getNeutralButtonRes()).isNull();
    }

    @Test
    public void setNeutralButtonRes_remainSeparate() throws Exception
    {
        when(context.getString(android.R.string.yes)).thenReturn("button3");

        builder.neutralButton("button4");
        builder.neutralButton(android.R.string.yes);
        assertThat(builder.getNeutralButton()).isEqualTo("button4");
        assertThat(builder.getNeutralButtonRes()).isEqualTo(android.R.string.yes);
    }

    @Test
    public void setCancellable_sets() throws Exception
    {
        builder.cancellable(true);
        assertThat(builder.getCancellable()).isTrue();
        builder.cancellable(false);
        assertThat(builder.getCancellable()).isFalse();
        builder.cancellable(null);
        assertThat(builder.getCancellable()).isNull();
    }

    @Test
    public void setCanceledOnTouchOutside_sets() throws Exception
    {
        builder.canceledOnTouchOutside(true);
        assertThat(builder.getCanceledOnTouchOutside()).isTrue();
        builder.canceledOnTouchOutside(false);
        assertThat(builder.getCanceledOnTouchOutside()).isFalse();
        builder.canceledOnTouchOutside(null);
        assertThat(builder.getCanceledOnTouchOutside()).isNull();
    }
}