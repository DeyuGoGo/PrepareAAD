package go.deyu.prepareaad.topic.androidcore

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import timber.log.Timber

class GoWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    companion object{
        const val KEY_IMAGE_URI = "key_uri"

        fun createInputData(string: String): Data {
            val builder = Data.Builder()
            builder.putString(KEY_IMAGE_URI, string)
            return builder.build()
        }

    }


    override suspend fun doWork(): Result {
        delay(100)
        Timber.d("doWork")
        return Result.success()
    }
}