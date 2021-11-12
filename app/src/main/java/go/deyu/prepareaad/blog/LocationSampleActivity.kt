package go.deyu.prepareaad.blog

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationSampleActivity : AppCompatActivity() {
    lateinit var locationClient: FusedLocationProviderClient
    val myLocation = MutableLiveData<Location>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationClient = LocationServices.getFusedLocationProviderClient(this)
        lifecycleScope.launchWhenResumed {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    this@LocationSampleActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) -> {
                    val tv = TextView(this@LocationSampleActivity)
                    tv.setText("還沒拿到資料")
                    myLocation.observe(this@LocationSampleActivity, {
                        tv.setText("My Location is lat : ${it.latitude} long : ${it.longitude}")
                    })
                    setContentView(tv)
                    requestLastLocation()

                }
                else -> {
                    requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
                }
            }
        }
    }

    private fun requestLastLocation() {
        CoroutineScope(Job() + Dispatchers.Main + CoroutineExceptionHandler { coroutineContext, throwable ->
            Toast.makeText(this, "幹有問題", Toast.LENGTH_LONG).show()
        }).launch {
            myLocation.value = awaitLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun awaitLastLocation(): Location =
        suspendCancellableCoroutine { continuation ->
            locationClient.lastLocation.addOnSuccessListener { location ->
                continuation.resume(location)
            }.addOnFailureListener {
                continuation.resumeWithException(it)
            }
        }

}