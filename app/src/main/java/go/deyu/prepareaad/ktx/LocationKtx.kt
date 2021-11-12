package go.deyu.prepareaad.ktx

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


@SuppressLint("MissingPermission")
suspend fun FusedLocationProviderClient.awaitLastLocation(): Location =
    suspendCancellableCoroutine { continuation ->
        lastLocation.addOnSuccessListener { location ->
            continuation.resume(location)
        }.addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }

@SuppressLint("MissingPermission")
fun FusedLocationProviderClient.locationFlow(): Flow<Location> = callbackFlow {
    val callback = object :LocationCallback(){
        override fun onLocationResult(result: LocationResult) {
            Timber.d("onLocationResult $result")
            for(location in result.locations){
                try{
                    trySend(location)
                }catch (e:Exception){}
            }
        }
    }

    requestLocationUpdates(
        createLocationRequest(),
         callback,
        Looper.getMainLooper()
    )

    awaitClose {
        removeLocationUpdates(callback)
    }
}

fun createLocationRequest(): LocationRequest =
    LocationRequest.create().setInterval(2000).setFastestInterval(1000).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)


