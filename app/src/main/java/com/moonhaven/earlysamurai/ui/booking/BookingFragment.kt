package com.moonhaven.earlysamurai.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R
import com.moonhaven.earlysamurai.ui.loaders.BookingLoaderScreenActivity
import kotlinx.android.synthetic.main.fragment_book.view.*

class BookingFragment:Fragment() {

    private lateinit var bookingButton:Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_book, container, false)
        bookingButton = root.book_button
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners(){
        bookingButton.setOnClickListener {
            (activity as MainActivity).startNewActivity(BookingLoaderScreenActivity())
            // Here we would normally have logic to send the information from the input fields as well.
            // But since this is a preview these are omitted
        }
    }
}