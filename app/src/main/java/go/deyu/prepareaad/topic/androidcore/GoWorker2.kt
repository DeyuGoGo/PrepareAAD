package go.deyu.prepareaad.topic.androidcore

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import timber.log.Timber

class GoWorker2(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    companion object{
        const val KEY_DATA = "key_data"

        fun createInputData(string: String): Data {
            val builder = Data.Builder()
            builder.putString(KEY_DATA, string)
            return builder.build()
        }

    }


    override suspend fun doWork(): Result {
        val inputString = inputData.getString(KEY_DATA)
        delay(100)
        Timber.d("doWork2 inputString $inputString")

        return Result.success()
    }
}