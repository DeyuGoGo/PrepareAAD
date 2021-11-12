package go.deyu.prepareaad.blog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.work.*
import go.deyu.prepareaad.work.DelayWorker
import timber.log.Timber
import java.util.concurrent.TimeUnit

class WorkerSampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(onClick = {
                    startWork()
                }) {
                    Text("Go Worker")
                }
                Button(onClick = {
                    startPeriodWork()
                }) {
                    Text("Go startPeriodWork")
                }
            }
        }
    }

    private fun startWork() {
        val c = WorkManager.getInstance(this@WorkerSampleActivity).beginUniqueWork(
            "DeyuWorker", ExistingWorkPolicy.REPLACE,
            OneTimeWorkRequest.from(DelayWorker::class.java)
        ).then(OneTimeWorkRequest.from(DelayWorker::class.java))
        Timber.d("startWork")
        c.workInfosLiveData.observe(this, {
            val firstWork = it.firstOrNull()
            firstWork?.run {
                when (this.state) {
                    WorkInfo.State.ENQUEUED -> Timber.d("firstWork: $this ENQUEUED")
                    WorkInfo.State.RUNNING -> Timber.d("firstWork: $this RUNNING")
                    WorkInfo.State.SUCCEEDED -> Timber.d("firstWork: $this SUCCEEDED")
                    WorkInfo.State.FAILED -> Timber.d("firstWork: $this FAILED")
                    WorkInfo.State.BLOCKED -> Timber.d("firstWork: $this BLOCKED")
                    WorkInfo.State.CANCELLED -> Timber.d("firstWork: $this CANCELLED")
                }
            }
        })
        c.enqueue()
    }

    private fun cancelPeriodWork(){
        WorkManager.getInstance(this).cancelAllWorkByTag("用來辨識，不要重複拉")
    }

    private fun startPeriodWork() {
        val operation = WorkManager.getInstance(this@WorkerSampleActivity).enqueueUniquePeriodicWork(
            "DeyuPeriodicWorker", ExistingPeriodicWorkPolicy.REPLACE,
            PeriodicWorkRequestBuilder<DelayWorker>(15, TimeUnit.MINUTES).addTag("用來辨識，不要重複拉")
                .build()
        )

        operation.state.observe(this,{
            Timber.d("startPeriodWork state $it")
        })


    }
}