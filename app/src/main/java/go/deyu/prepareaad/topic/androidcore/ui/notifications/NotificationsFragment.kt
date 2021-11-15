package go.deyu.prepareaad.topic.androidcore.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import go.deyu.prepareaad.databinding.FragmentNotificationsBinding
import go.deyu.prepareaad.notification.NotificationUtil
import javax.inject.Inject

@AndroidEntryPoint
class NotificationsFragment : Fragment() {


    private lateinit var viewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupView()
        return root
    }

    private fun setupView() {
        binding.apply{
            requireActivity().run {
                button.setOnClickListener {viewModel.notifyNotification()}
                button2.setOnClickListener {viewModel.notifyNotification(NotificationUtil.NotificationType.BUTTON)}
                button3.setOnClickListener {viewModel.notifyNotification(NotificationUtil.NotificationType.REPLY)}
                button4.setOnClickListener {viewModel.notifyNotification(NotificationUtil.NotificationType.PROGRESS(0))}
                button5.setOnClickListener {viewModel.notifyNotification(NotificationUtil.NotificationType.MESSAGE)}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}