package go.deyu.prepareaad.topic.androidcore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import go.deyu.prepareaad.R

class TopicAndroidCoreActivity : AppCompatActivity() {

    val viewModel :TopicAndroidCoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_android_core)
    }
}