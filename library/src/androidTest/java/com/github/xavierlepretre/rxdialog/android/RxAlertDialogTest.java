package com.github.xavierlepretre.rxdialog.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.github.xavierlepretre.TestHelper;
import com.github.xavierlepretre.rxdialog.AlertDialogButtonEvent;
import com.github.xavierlepretre.rxdialog.AlertDialogEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.ReplaySubject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class RxAlertDialogTest
{
    @Rule
    public ActivityTestRule<Activity> mActivityRule = new ActivityTestRule<>(Activity.class);

    @Test
    public void build_showsDialogWith3Buttons() throws Exception
    {
        final BehaviorSubject<AlertDialogEvent> subject = BehaviorSubject.create();
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(TestHelper.getActivityInstance())
                .title("Attention")
                .message("Message1")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .neutralButton("Later")
                .build()
                .subscribe(
                        new Action1<AlertDialogEvent>()
                        {
                            @Override public void call(AlertDialogEvent alertDialogEvent)
                            {
                                subject.onNext(alertDialogEvent);
                                gotDialogSignal.countDown();
                            }
                        });
        gotDialogSignal.await(15, TimeUnit.SECONDS);

        assertThat(gotDialogSignal.getCount()).isEqualTo(0).as("The dialog should have been shown");
        assertThat(subject.getValue()).isInstanceOf(AlertDialogDialogEvent.class);
        onView(withText("Attention")).check(matches(isDisplayed()));
        onView(withText("Message1")).check(matches(isDisplayed()));
        onView(withText("OK")).check(matches(isDisplayed()));
        onView(withText("Cancel")).check(matches(isDisplayed()));
        onView(withText("Later")).check(matches(isDisplayed()));

        assertThat(subscription.isUnsubscribed()).isFalse().as("No button has been clicked");
        subscription.unsubscribe();
    }

    @Test
    public void build_showsDialogWith2Buttons() throws Exception
    {
        final BehaviorSubject<AlertDialogEvent> subject = BehaviorSubject.create();
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(TestHelper.getActivityInstance())
                .title("Attention")
                .message("Message1")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .build()
                .subscribe(
                        new Action1<AlertDialogEvent>()
                        {
                            @Override public void call(AlertDialogEvent alertDialogEvent)
                            {
                                subject.onNext(alertDialogEvent);
                                gotDialogSignal.countDown();
                            }
                        });
        gotDialogSignal.await(15, TimeUnit.SECONDS);

        assertThat(gotDialogSignal.getCount()).isEqualTo(0).as("The dialog should have been shown");
        assertThat(subject.getValue()).isInstanceOf(AlertDialogDialogEvent.class);
        onView(withText("Attention")).check(matches(isDisplayed()));
        onView(withText("Message1")).check(matches(isDisplayed()));
        onView(withText("OK")).check(matches(isDisplayed()));
        onView(withText("Cancel")).check(matches(isDisplayed()));

        assertThat(subscription.isUnsubscribed()).isFalse().as("No button has been clicked");
        subscription.unsubscribe();
    }

    @Test
    public void build_showsDialogWith1Button() throws Exception
    {
        final BehaviorSubject<AlertDialogEvent> subject = BehaviorSubject.create();
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(TestHelper.getActivityInstance())
                .title("Attention")
                .message("Message1")
                .negativeButton("Cancel") // Notice there is no positive button.
                .build()
                .subscribe(
                        new Action1<AlertDialogEvent>()
                        {
                            @Override public void call(AlertDialogEvent alertDialogEvent)
                            {
                                subject.onNext(alertDialogEvent);
                                gotDialogSignal.countDown();
                            }
                        });
        gotDialogSignal.await(15, TimeUnit.SECONDS);

        assertThat(gotDialogSignal.getCount()).isEqualTo(0).as("The dialog should have been shown");
        assertThat(subject.getValue()).isInstanceOf(AlertDialogDialogEvent.class);
        onView(withText("Attention")).check(matches(isDisplayed()));
        onView(withText("Message1")).check(matches(isDisplayed()));
        onView(withText("OK")).check(doesNotExist()); // Notice the "Not" here.
        onView(withText("Cancel")).check(matches(isDisplayed()));

        assertThat(subscription.isUnsubscribed()).isFalse().as("No button has been clicked");
        subscription.unsubscribe();
    }

    @Test
    public void dismissDialog_sendsCompleted() throws Exception
    {
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        final CountDownLatch gotCompletedSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(TestHelper.getActivityInstance())
                .title("Attention")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .build()
                .doOnUnsubscribe(new Action0()
                {
                    @Override public void call()
                    {
                        gotCompletedSignal.countDown();
                    }
                })
                .subscribe(
                        new Action1<AlertDialogEvent>()
                        {
                            @Override public void call(AlertDialogEvent alertDialogEvent)
                            {
                                ((AlertDialogDialogEvent) alertDialogEvent).getAlertDialog().dismiss();
                                gotDialogSignal.countDown();
                            }
                        });
        gotDialogSignal.await(15, TimeUnit.SECONDS);
        assertThat(gotDialogSignal.getCount()).isEqualTo(0).as("The dialog should have been shown");

        gotCompletedSignal.await(15, TimeUnit.SECONDS);
        assertThat(gotCompletedSignal.getCount()).isEqualTo(0).as("The dismiss should have send onCompleted");
        assertThat(subscription.isUnsubscribed()).as("The dismiss should have unsubscribed");
    }

    @Test
    public void unsubscribe_willDismissDialog() throws Exception
    {
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(TestHelper.getActivityInstance())
                .title("Attention")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .build()
                .subscribe(
                        new Action1<AlertDialogEvent>()
                        {
                            @Override public void call(AlertDialogEvent alertDialogEvent)
                            {
                                ((AlertDialogDialogEvent) alertDialogEvent).getAlertDialog().dismiss();
                                gotDialogSignal.countDown();
                            }
                        });
        gotDialogSignal.await(15, TimeUnit.SECONDS);
        assertThat(gotDialogSignal.getCount()).isEqualTo(0).as("The dialog should have been shown");

        subscription.unsubscribe();
        Thread.sleep(1000);

        onView(withText("Attention")).check(doesNotExist());
    }
}