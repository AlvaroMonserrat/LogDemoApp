package com.rrat.logdemoapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.rrat.logdemoapp.R
import com.rrat.logdemoapp.application.LogApplication
import com.rrat.logdemoapp.data.Logger
import com.rrat.logdemoapp.databinding.FragmentMenuBinding
import com.rrat.logdemoapp.navigator.AppNavigator
import com.rrat.logdemoapp.navigator.AppNavigatorImpl
import com.rrat.logdemoapp.navigator.SCREENS


class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    private lateinit var logger: Logger
    private lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "ON CREATE")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i(TAG, "ON CREATE VIEW")
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //SET ON CLICK LISTENER
        Log.i(TAG, "ON VIEW CREATED")
        binding.btnSubmit.setOnClickListener { onSubmit(it) }
        binding.btnLog.setOnClickListener { onLog(it) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "ON ATTACH")

        val application = (context.applicationContext as LogApplication)
        navigator = application
            .serviceLocator
            .provideNavigator(requireActivity())

        logger = application.serviceLocator.provideLogger()
    }


    private fun onSubmit(view: View)
    {
        val log = binding.etLog.text.toString()
        if (log.isBlank())
        {
            Toast.makeText(context,
                "Log field cannot be empty.",
                Toast.LENGTH_SHORT)
                .show()

            return
        }
        logger.addLog(log)
        Snackbar.make(view,"Log was submitted.", Snackbar.LENGTH_SHORT ).show()
    }

    private fun onLog(view: View)
    {
        navigator.navigateTo(SCREENS.LOGS)
    }

    companion object{
        const val TAG = "MENU_FRAGMENT"
    }
}