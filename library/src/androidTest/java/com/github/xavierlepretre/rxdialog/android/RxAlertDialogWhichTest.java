package com.github.xavierlepretre.rxdialog.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.test.rule.ActivityTestRule;
import com.github.xavierlepretre.TestHelper;
import com.github.xavierlepretre.rxdialog.AlertDialogButtonEvent;
import com.github.xavierlepretre.rxdialog.AlertDialogEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.ReplaySubject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class RxAlertDialogWhichTest
{
    @Rule
    public ActivityTestRule<Activity> mActivityRule = new ActivityTestRule<>(Activity.class);

    @Parameterized.Parameter
    public Map.Entry<Integer, String> button;

    @Parameterized.Parameters
    public static Collection<Map.Entry<Integer, String>> getButtons()
    {
        Map<Integer, String> whichOnes = new HashMap<>();
        whichOnes.put(DialogInterface.BUTTON_POSITIVE, "PosButton");
        whichOnes.put(DialogInterface.BUTTON_NEGATIVE, "NegButton");
        whichOnes.put(DialogInterface.BUTTON_NEUTRAL, "NeuButton");
        return whichOnes.entrySet();
    }

    @Test
    public void click_sendsWhichKey() throws Exception
    {
        final ReplaySubject<AlertDialogEvent> subject = ReplaySubject.create();
        final CountDownLatch dialogSignal = new CountDownLatch(1);
        final CountDownLatch buttonSignal = new CountDownLatch(2);
        Subscription subscription = new RxAlertDialog.Builder(TestHelper.getActivityInstance())
                .title("Irrelevant")
                .positiveButton("PosButton")
                .negativeButton("NegButton")
                .neutralButton("NeuButton")
                .build()
                .subscribe(
                        new Action1<AlertDialogEvent>()
                        {
                            @Override public void call(AlertDialogEvent alertDialogEvent)
                            {
                                subject.onNext(alertDialogEvent);
                                dialogSignal.countDown();
                                buttonSignal.countDown();
                            }
                        });
        dialogSignal.await(15, TimeUnit.SECONDS);

        System.out.println("Clicking which " + button.getKey());
        onView(withText(button.getValue())).perform(click());

        buttonSignal.await(15, TimeUnit.SECONDS);
        assertThat(buttonSignal.getCount()).isEqualTo(0).as("The dialog should have been shown");
        assertThat(subject.getValues()[0]).isInstanceOf(AlertDialogDialogEvent.class);
        assertThat(subject.getValues()[1]).isInstanceOf(AlertDialogButtonEvent.class);
        assertThat(((AlertDialogButtonEvent) subject.getValues()[1]).getWhich()).isEqualTo(button.getKey());
        assertThat(subscription.isUnsubscribed()).as("Clicking the  button " + button.getKey() + " should have completed the observable");
    }
}