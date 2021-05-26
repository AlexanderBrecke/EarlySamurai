package com.moonhaven.earlysamurai.ui.custom

import android.opengl.Visibility
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.custom_about_info_card.view.*

class CustomInfoCard(private val view:View){

    // Value for the text view
    private val infoTextView: TextView = view.info_text_view

    // Function to set the text of the text view
    fun setInfoText(textToSet:String){
        infoTextView.text = textToSet
    }

    // Function to set the visibility of cards
    fun setVisibility(visibility:Int){
        view.visibility = visibility
    }
}