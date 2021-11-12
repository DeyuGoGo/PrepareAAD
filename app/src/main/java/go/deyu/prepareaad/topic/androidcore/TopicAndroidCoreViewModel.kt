package go.deyu.prepareaad.topic.androidcore

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class TopicAndroidCoreViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {

    private var workInfo: LiveData<WorkInfo>
    val uuid = UUID.randomUUID()
    val ob = Observer<WorkInfo> { t ->
        t?.run {
            when (this.state) {
                WorkInfo.State.ENQUEUED -> Timber.d("firstWork: $this ENQUEUED")
                WorkInfo.State.RUNNING -> Timber.d("firstWork: $this RUNNING")
                WorkInfo.State.SUCCEEDED -> Timber.d("firstWork: $this SUCCEEDED")
                WorkInfo.State.FAILED -> Timber.d("firstWork: $this FAILED")
                WorkInfo.State.BLOCKED -> Timber.d("firstWork: $this BLOCKED")
                WorkInfo.State.CANCELLED -> Timber.d("firstWork: $this CANCELLED")
            }
        }
    }

    init {
        workInfo = workManager.getWorkInfoByIdLiveData(uuid)

        workInfo.observeForever(ob)
    }

    fun startWork() {
        val s = PeriodicWorkRequestBuilder<GoWorker>(
            15L,
            TimeUnit.MINUTES
        ).setInputData(GoWorker.createInputData("你好r")).build()
        workManager.enqueue(s)
    }

    override fun onCleared() {
        super.onCleared()
        workInfo.removeObserver(ob)
    }
}