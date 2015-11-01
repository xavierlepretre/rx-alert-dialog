package com.github.xavierlepretre.rxalertdialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.xavierlepretre.rxdialog.AlertDialogButtonEvent;
import com.github.xavierlepretre.rxdialog.AlertDialogEvent;
import com.github.xavierlepretre.rxdialog.android.RxAlertDialog;
import com.github.xavierlepretre.rxdialog.support.RxAlertDialogSupport;
import rx.Observer;
import rx.internal.util.SubscriptionList;

public class MainActivity extends AppCompatActivity
{
    @Bind(R.id.input_title) TextView titleView;
    @Bind(R.id.input_message) TextView messageView;
    @Bind(R.id.input_button_positive) TextView buttonPositive;
    @Bind(R.id.input_button_negative) TextView buttonNegative;
    @Bind(R.id.input_button_neutral) TextView buttonNeutral;
    @Bind(R.id.cancellable_spinner) AppCompatSpinner cancellableSpinner;
    @Bind(R.id.canceled_on_touch_outside_spinner) AppCompatSpinner canceledOnTouchOutsideSpinner;

    private SubscriptionList subscriptionList;
    private Observer<AlertDialogEvent> dialogEventObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        subscriptionList = new SubscriptionList();
        dialogEventObserver = new ButtonClickObserver(
                (ViewGroup) findViewById(R.id.content_main_linear),
                getLayoutInflater());

        ButterKnife.bind(this);

        cancellableSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                SpinnableBoolean.getAll()));

        canceledOnTouchOutsideSpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                SpinnableBoolean.getAll()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override protected void onDestroy()
    {
        subscriptionList.unsubscribe();
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.create_dialog_android)
    protected void onCreateAndroidClicked(@NonNull View ignored)
    {
        subscriptionList.add(new RxAlertDialog.Builder(this)
                .title(titleView.getText().toString())
                .message(messageView.getText().toString())
                .positiveButton(buttonPositive.getText().toString())
                .negativeButton(buttonNegative.getText().toString())
                .neutralButton(buttonNeutral.getText().toString())
                .cancellable(((SpinnableBoolean) cancellableSpinner.getSelectedItem()).getValue())
                .canceledOnTouchOutside(((SpinnableBoolean) canceledOnTouchOutsideSpinner.getSelectedItem()).getValue())
                .show()
                .subscribe(dialogEventObserver));
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.create_dialog_support)
    protected void onCreateSupportClicked(@NonNull View ignored)
    {
        subscriptionList.add(new RxAlertDialogSupport.Builder(this)
                .title(titleView.getText().toString())
                .message(messageView.getText().toString())
                .positiveButton(buttonPositive.getText().toString())
                .negativeButton(buttonNegative.getText().toString())
                .neutralButton(buttonNeutral.getText().toString())
                .cancellable(((SpinnableBoolean) cancellableSpinner.getSelectedItem()).getValue())
                .canceledOnTouchOutside(((SpinnableBoolean) canceledOnTouchOutsideSpinner.getSelectedItem()).getValue())
                .show()
                .subscribe(dialogEventObserver));
    }

    private static class ButtonClickObserver implements Observer<AlertDialogEvent>
    {
        @NonNull private final ViewGroup parentView;
        @NonNull private final LayoutInflater layoutInflater;

        private ButtonClickObserver(@NonNull ViewGroup parentView, @NonNull LayoutInflater layoutInflater)
        {
            this.parentView = parentView;
            this.layoutInflater = layoutInflater;
        }

        @Override public void onNext(AlertDialogEvent alertDialogEvent)
        {
            String message = alertDialogEvent.getClass().getSimpleName();
            if (alertDialogEvent instanceof AlertDialogButtonEvent)
            {
                message += " " + ((AlertDialogButtonEvent) alertDialogEvent).getWhich();
            }
            addMessage(message);
        }

        @Override public void onCompleted()
        {
            addMessage("Completed");
        }

        @Override public void onError(Throwable e)
        {
            Log.e(Constants.TAG, "Failed to observe dialog", e);
            addMessage(e.getMessage());
        }

        private void addMessage(@NonNull String message)
        {
            final TextView newView = (TextView) layoutInflater.inflate(R.layout.event_text, null);
            newView.setText(message);
            parentView.addView(newView);
        }
    }
}
