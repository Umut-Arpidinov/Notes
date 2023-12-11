package com.example.notesapp.presentation.event

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesapp.base.BaseFragment
import com.example.notesapp.base.ViewModelFactory
import com.example.notesapp.data.local.database.enitites.Event
import com.example.notesapp.databinding.FragmentCreateEventBinding
import java.util.Date
import javax.inject.Inject

class CreateEventFragment @Inject constructor(
    viewModelFactory: ViewModelFactory,
) : BaseFragment<FragmentCreateEventBinding>() {


    private val eventViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EventViewModel::class.java]
    }

    override fun getViewBinding(): FragmentCreateEventBinding =
        FragmentCreateEventBinding.inflate(layoutInflater)

    override fun setUpViews() = with(binding) {
        super.setUpViews()
        setUpDate()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun setUpListener() {
        super.setUpListener()
        createNotificationChannel()
        binding.btnSaveEvent.setOnClickListener {
            scheduleNotification()
        }
    }

    override fun observeData() {
        super.observeData()
    }


    private fun setUpDate() = with(binding) {
        dpDate.minDate = System.currentTimeMillis()
        tpTime.setIs24HourView(true)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun scheduleNotification() {
        val intent = Intent(requireContext(), NotificationReceiver::class.java)
        val title = binding.etTitle.text.toString()
        val message = binding.etMessage.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )

        val event = Event(title = title, message = message, date = time)

        eventViewModel.saveEvent(event)

        showAlert(time, title, message)

    }

    private fun showAlert(time: Long, title: String, message: String) {

        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(requireContext())
        val timeFormat = android.text.format.DateFormat.getTimeFormat(requireContext())

        AlertDialog.Builder(requireContext())
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title +
                        " Message: " + message +
                        " At: " + dateFormat.format(date) + " " + timeFormat.format(date)
            )
            .setPositiveButton("Okay") { _, _ ->
                findNavController().popBackStack()
            }
            .show()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun createNotificationChannel() {
        val name = "Notes app channel"
        val desc = "Channel for events"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(chanelId, name, importance)
        } else {
            println("This block is happening")
            TODO("VERSION.SDK_INT < O")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel.description = desc
        }
        val notificationManager =
            requireContext().getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getTime(): Long {
        binding.apply {
            val minute = tpTime.minute
            val hour = tpTime.hour
            val day = dpDate.dayOfMonth
            val month = dpDate.month
            val year = dpDate.year
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day, hour, minute)
            return calendar.timeInMillis
        }
    }

}