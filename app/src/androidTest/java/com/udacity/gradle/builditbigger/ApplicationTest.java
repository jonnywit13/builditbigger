package com.udacity.gradle.builditbigger;

import org.junit.Test;
import java.util.concurrent.CountDownLatch;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class ApplicationTest {

    private String mResult = null;
    private CountDownLatch countDown = null;

    @Test
    public void verifyFetchJokeTaskResult() {
        countDown = new CountDownLatch(1);

        OnJokeTaskCompleted taskCompleted = new OnJokeTaskCompleted() {
            @Override
            public void onJokeTaskCompleted(String string) {
                return;
            }
        };

        FetchJokeTask jokeTask = new FetchJokeTask(taskCompleted){
            @Override
            public void onPostExecute(String result) {
                super.onPostExecute(result);
                mResult = result;
                countDown.countDown();
            }
        };
        jokeTask.execute();

        try {
            countDown.await();
        } catch (InterruptedException e) {
            //Log.v("TESTLOG", "Test Failed");
        }

        assertNotNull(mResult);
        assertEquals(false, mResult=="");

    }

}