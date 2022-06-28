package com.rrat.logdemoapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.rrat.logdemoapp.application.LogApplication
import com.rrat.logdemoapp.data.Log
import com.rrat.logdemoapp.data.Logger
import com.rrat.logdemoapp.databinding.FragmentLogsBinding
import com.rrat.logdemoapp.databinding.ItemLogBinding
import com.rrat.logdemoapp.navigator.AppNavigator
import com.rrat.logdemoapp.util.DateFormatter


class LogsFragment : Fragment() {

    private lateinit var binding: FragmentLogsBinding
    private lateinit var logger: Logger
    private lateinit var dateFormatter: DateFormatter
    private lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        i(TAG, "ON CREATE")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogsBinding.inflate(layoutInflater, container, false)
        binding.rvLogs.apply {
            setHasFixedSize(true)
            adapter = LogsViewAdapter(listOf(), dateFormatter)
        }
        i(TAG, "ON CREATE VIEW")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        i(TAG, "ON VIEW CREATED")
        logger.getAllLogs { logs-> binding.rvLogs.adapter = LogsViewAdapter(logs, dateFormatter) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        i(TAG, "ON ATTACH")
        val application = (context.applicationContext as LogApplication)
        navigator = application
            .serviceLocator
            .provideNavigator(requireActivity())

        logger = application.serviceLocator.provideLogger()
        dateFormatter = application.serviceLocator.provideDateFormatter()
    }

    override fun onResume() {
        super.onResume()
        i(TAG, "ON RESUME")
    }

    companion object{
        const val TAG = "LOGS_FRAGMENT"
    }

}

private class LogsViewAdapter(
    private val logs: List<Log>,
    private val daterFormatter: DateFormatter) : RecyclerView.Adapter<LogsViewAdapter.LogsViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LogsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        val log: Log = logs[position]
        holder.tvId.text = log.id.toString()
        holder.tvLog.text = log.msg
        holder.tvDate.text = daterFormatter.formatDate(log.timestamp)
    }

    override fun getItemCount(): Int {
        return logs.size
    }

    class LogsViewHolder(view: ItemLogBinding): RecyclerView.ViewHolder(view.root)
    {
        val tvId = view.tvId
        val tvLog = view.tvLog
        val tvDate = view.tvDate
    }

}