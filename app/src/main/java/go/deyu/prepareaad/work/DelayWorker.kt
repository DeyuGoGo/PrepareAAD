package go.deyu.prepareaad.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DelayWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(
    appContext,
    params
) {
    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO){
            try {
                delaySomething()
                Result.success()
            }catch (e:Exception){
                Result.failure()
            }
        }
    }
    private suspend fun delaySomething(){
        for(i in 0..9){
            delay(1000)
        }
    }
}