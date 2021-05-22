package com.moonhaven.earlysamurai.ui.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.moonhaven.earlysamurai.R

// Custom user card logic
class CustomUserCard(context:Context):CardView(context) {

    // Value for the text view
    private val userText:TextView

    // Set the entire view to inflate
    private val view: View = LayoutInflater.from(context).inflate(R.layout.custom_user_card,this)


    init {
        // Initialize the text view
        userText = view.findViewById(R.id.user_card_text)
    }

    // Function to set the text of the text view
    fun setUserText(textToSet:String){
        userText.text = textToSet
    }
}