package com.github.xavierlepretre;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.runner.lifecycle.Stage.RESUMED;
import static org.fest.assertions.api.Assertions.assertThat;

public class TestHelper
{
    @NonNull public static Activity getActivityInstance() throws InterruptedException
    {
        final Activity[] currentActivity = new Activity[1];
        final CountDownLatch activityReadySignal = new CountDownLatch(1);
        getInstrumentation().runOnMainSync(new Runnable()
        {
            public void run()
            {
                Collection<Activity> resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext())
                {
                    activityReadySignal.countDown();
                    currentActivity[0] = resumedActivities.iterator().next();
                }
            }
        });

        activityReadySignal.await(10, TimeUnit.SECONDS);
        assertThat(activityReadySignal.getCount()).isEqualTo(0).as("The activity should have been ready by now");
        return currentActivity[0];
    }
}
