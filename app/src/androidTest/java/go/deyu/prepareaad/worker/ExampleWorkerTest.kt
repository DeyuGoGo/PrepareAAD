package go.deyu.prepareaad.worker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import go.deyu.prepareaad.work.DelayWorker
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleWorkerTest {
    private var context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var worker: DelayWorker

    @Before
    fun setup() {
        worker = TestListenableWorkerBuilder<DelayWorker>(context).build()
    }

    @Test
    fun testDelayWorker() {

        runBlocking {
            worker.doWork()
        }

    }

    @Test
    fun testDelayWorkerGo() {
//        val worker = TestWorkerBuilder<DelayWorker>(
//            context,
//            newSingleThreadExecutor()
//        )

    }

    @Test
    fun testDelayWorkerPeriodic() {
        WorkManagerTestInitHelper.initializeTestWorkManager(context)
        val testDriver = WorkManagerTestInitHelper.getTestDriver(context)!!
        val workManager = WorkManager.getInstance(context)
        val request = PeriodicWorkRequestBuilder<DelayWorker>(24, TimeUnit.HOURS).build()
        workManager.enqueue(request).result.get()

        with(testDriver){
            setPeriodDelayMet(request.id)
            setAllConstraintsMet(request.id)
        }

        val workInfo = workManager.getWorkInfoById(request.id).get()

        assertEquals(workInfo.state , WorkInfo.State.RUNNING)

    }


    @After
    fun tearDown() {
        // =v=
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("go.deyu.composesample", appContext.packageName)
    }
}