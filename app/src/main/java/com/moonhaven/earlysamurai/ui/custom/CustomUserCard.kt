package com.moonhaven.earlysamurai.ui.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.moonhaven.earlysamurai.R

class CustomUserCard(context:Context):CardView(context) {
    private val userText:TextView

    private val view: View = LayoutInflater.from(context).inflate(R.layout.custom_user_card,this)


    init {
        userText = view.findViewById(R.id.user_card_text)
    }

    fun setUserText(textToSet:String){
        userText.text = textToSet
    }
}