package com.github.xavierlepretre.rxdialog.android;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.github.xavierlepretre.rxdialog.AlertDialogEvent;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class RxAlertDialogTest
{
    @Rule
    public ActivityTestRule<Activity> activityRule = new ActivityTestRule<>(Activity.class);

    @Test
    public void create_doesNotShowButCanShow() throws Exception
    {
        final BehaviorSubject<AlertDialogEvent> subject = BehaviorSubject.create();
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(activityRule.getActivity())
                .title("Attention")
                .message("Message1")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .neutralButton("Later")
                .create()
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

        assertThat(gotDialogSignal.getCount()).isEqualTo(0).as("The dialog should have been created");
        onView(withText("Attention")).check(doesNotExist());

        assertThat(subject.getValue()).isInstanceOf(AlertDialogDialogEvent.class);
        final CountDownLatch shownDialogSignal = new CountDownLatch(1);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable()
        {
            @Override public void run()
            {
                ((AlertDialogDialogEvent) subject.getValue()).getAlertDialog().show();
                shownDialogSignal.countDown();
            }
        });
        shownDialogSignal.await(15, TimeUnit.SECONDS);
        assertThat(shownDialogSignal.getCount()).isEqualTo(0).as("We should have shown the dialog");

        onView(withText("Attention")).check(matches(isDisplayed()));
        onView(withText("Message1")).check(matches(isDisplayed()));
        onView(withText("OK")).check(matches(isDisplayed()));
        onView(withText("Cancel")).check(matches(isDisplayed()));
        onView(withText("Later")).check(matches(isDisplayed()));

        assertThat(subscription.isUnsubscribed()).isFalse().as("No button has been clicked");
        subscription.unsubscribe();
    }

    @Test
    public void show_showsDialogWith3Buttons() throws Exception
    {
        final BehaviorSubject<AlertDialogEvent> subject = BehaviorSubject.create();
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(activityRule.getActivity())
                .title("Attention")
                .message("Message1")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .neutralButton("Later")
                .show()
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
    public void show_showsDialogWith2Buttons() throws Exception
    {
        final BehaviorSubject<AlertDialogEvent> subject = BehaviorSubject.create();
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(activityRule.getActivity())
                .title("Attention")
                .message("Message1")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .show()
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
    public void show_showsDialogWith1Button() throws Exception
    {
        final BehaviorSubject<AlertDialogEvent> subject = BehaviorSubject.create();
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(activityRule.getActivity())
                .title("Attention")
                .message("Message1")
                .negativeButton("Cancel") // Notice there is no positive button.
                .show()
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
        Subscription subscription = new RxAlertDialog.Builder(activityRule.getActivity())
                .title("Attention")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .show()
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
        Subscription subscription = new RxAlertDialog.Builder(activityRule.getActivity())
                .title("Attention")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .show()
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

    @Test
    public void ifSetTrue_pressBack_canDismiss() throws Exception
    {
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(activityRule.getActivity())
                .title("Attention")
                .message("Message1")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .neutralButton("Later")
                .cancellable(true)
                .show()
                .subscribe(
                        new Action1<AlertDialogEvent>()
                        {
                            @Override public void call(AlertDialogEvent alertDialogEvent)
                            {
                                gotDialogSignal.countDown();
                            }
                        });
        gotDialogSignal.await(15, TimeUnit.SECONDS);

        assertThat(gotDialogSignal.getCount()).isEqualTo(0).as("The dialog should have been created");
        onView(withText("Attention")).check(matches(isDisplayed()));

        pressBack();
        onView(withText("Attention")).check(doesNotExist());

        assertThat(subscription.isUnsubscribed()).isTrue().as("Dismiss should unsubscribe");
    }

    @Test
    public void ifSetFalse_pressBack_willNotDismiss() throws Exception
    {
        final CountDownLatch gotDialogSignal = new CountDownLatch(1);
        Subscription subscription = new RxAlertDialog.Builder(activityRule.getActivity())
                .title("Attention")
                .message("Message1")
                .positiveButton("OK")
                .negativeButton("Cancel")
                .neutralButton("Later")
                .cancellable(false)
                .show()
                .subscribe(
                        new Action1<AlertDialogEvent>()
                        {
                            @Override public void call(AlertDialogEvent alertDialogEvent)
                            {
                                gotDialogSignal.countDown();
                            }
                        });
        gotDialogSignal.await(15, TimeUnit.SECONDS);

        assertThat(gotDialogSignal.getCount()).isEqualTo(0).as("The dialog should have been created");
        onView(withText("Attention")).check(matches(isDisplayed()));

        pressBack();
        onView(withText("Attention")).check(matches(isDisplayed()));

        assertThat(subscription.isUnsubscribed()).isFalse().as("It should not have dismissed");
        subscription.unsubscribe();
    }
}