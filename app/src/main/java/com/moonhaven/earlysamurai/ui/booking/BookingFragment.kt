package com.moonhaven.earlysamurai.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R
import com.moonhaven.earlysamurai.ui.splash.SplashActivity
import kotlinx.android.synthetic.main.fragment_book.*
import kotlinx.android.synthetic.main.fragment_book.view.*

class BookingFragment:Fragment() {

    // Setup a booking button
    private lateinit var bookingButton:Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_book, container, false)

        // Initialize the booking button
        bookingButton = root.book_button

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    // Function to set click listener of booking button
    private fun setClickListeners(){
        bookingButton.setOnClickListener {
            // Go to the splash activity.
            (activity as MainActivity).startNewActivity(SplashActivity())

            // Here we would normally have logic to send the information from the input fields as well.
            // But since this is a preview of how the flow would work these are omitted
        }
    }
}