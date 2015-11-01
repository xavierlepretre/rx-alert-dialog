package com.github.xavierlepretre.rxdialog;

import android.content.Context;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlertDialogBuilderTest
{
    @Test
    public void setTitleString_sets() throws Exception
    {
        DummyBuilder builder = new DummyBuilder(mock(Context.class));
        builder.title("title1");
        assertThat(builder.getTitle()).isEqualTo("title1");
        builder.title(null);
        assertThat(builder.getTitle()).isNull();
    }

    @Test
    public void setTitleRes_setsString() throws Exception
    {
        Context context = mock(Context.class);
        DummyBuilder builder = new DummyBuilder(context);
        //noinspection ResourceType
        when(context.getString(1)).thenReturn("title1");
        //noinspection ResourceType
        builder.titleRes(1);
        assertThat(builder.getTitle()).isEqualTo("title1");
    }

    @Test
    public void setMessageString_sets() throws Exception
    {
        DummyBuilder builder = new DummyBuilder(mock(Context.class));
        builder.message("message1");
        assertThat(builder.getMessage()).isEqualTo("message1");
        builder.message(null);
        assertThat(builder.getMessage()).isNull();
    }

    @Test
    public void setMessageRes_setsString() throws Exception
    {
        Context context = mock(Context.class);
        DummyBuilder builder = new DummyBuilder(context);
        //noinspection ResourceType
        when(context.getString(1)).thenReturn("message1");
        //noinspection ResourceType
        builder.messageRes(1);
        assertThat(builder.getMessage()).isEqualTo("message1");
    }

    @Test
    public void setPositiveButtonString_sets() throws Exception
    {
        DummyBuilder builder = new DummyBuilder(mock(Context.class));
        builder.positiveButton("button1");
        assertThat(builder.getPositiveButton()).isEqualTo("button1");
        builder.positiveButton(null);
        assertThat(builder.getPositiveButton()).isNull();
    }

    @Test
    public void setPositiveButtonRes_setsString() throws Exception
    {
        Context context = mock(Context.class);
        DummyBuilder builder = new DummyBuilder(context);
        //noinspection ResourceType
        when(context.getString(1)).thenReturn("button1");
        //noinspection ResourceType
        builder.positiveButtonRes(1);
        assertThat(builder.getPositiveButton()).isEqualTo("button1");
    }

    @Test
    public void setNegativeButtonString_sets() throws Exception
    {
        DummyBuilder builder = new DummyBuilder(mock(Context.class));
        builder.negativeButton("button2");
        assertThat(builder.getNegativeButton()).isEqualTo("button2");
        builder.negativeButton(null);
        assertThat(builder.getNegativeButton()).isNull();
    }

    @Test
    public void setNegativeButtonRes_setsString() throws Exception
    {
        Context context = mock(Context.class);
        DummyBuilder builder = new DummyBuilder(context);
        //noinspection ResourceType
        when(context.getString(1)).thenReturn("button2");
        //noinspection ResourceType
        builder.negativeButtonRes(1);
        assertThat(builder.getNegativeButton()).isEqualTo("button2");
    }

    @Test
    public void setNeutralButtonString_sets() throws Exception
    {
        DummyBuilder builder = new DummyBuilder(mock(Context.class));
        builder.neutralButton("button3");
        assertThat(builder.getNeutralButton()).isEqualTo("button3");
        builder.neutralButton(null);
        assertThat(builder.getNeutralButton()).isNull();
    }

    @Test
    public void setNeutralButtonRes_setsString() throws Exception
    {
        Context context = mock(Context.class);
        DummyBuilder builder = new DummyBuilder(context);
        //noinspection ResourceType
        when(context.getString(1)).thenReturn("button3");
        //noinspection ResourceType
        builder.neutralButtonRes(1);
        assertThat(builder.getNeutralButton()).isEqualTo("button3");
    }

    @Test
    public void setCancellable_sets() throws Exception
    {
        DummyBuilder builder = new DummyBuilder(mock(Context.class));
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
        DummyBuilder builder = new DummyBuilder(mock(Context.class));
        builder.canceledOnTouchOutside(true);
        assertThat(builder.getCanceledOnTouchOutside()).isTrue();
        builder.canceledOnTouchOutside(false);
        assertThat(builder.getCanceledOnTouchOutside()).isFalse();
        builder.canceledOnTouchOutside(null);
        assertThat(builder.getCanceledOnTouchOutside()).isNull();
    }
}