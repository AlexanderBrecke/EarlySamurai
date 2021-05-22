package com.moonhaven.earlysamurai.ui.custom

import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.header_bar.view.*

// Custom header bar logic
class CustomHeaderBar(view: View) {

    // Setup values for the views
    private val logoImageCenter:ImageView = view.logo_image_mid
    private val logoText:ImageView = view.logo_text
    private var logoImageRight:ImageView = view.logo_image_right
    private var backArrow:ImageView = view.back_arrow

    // When initialized, make sure only the correct logo is used
    init {
        hideMiddleLogo()
        hideBackArrow()
    }

    // Function to hide the middle logo
    fun hideMiddleLogo(){
        logoImageCenter.visibility = View.GONE
        logoText.visibility = View.GONE
    }

    // Function to show the middle logo
    fun showMiddleLogo(){
        logoImageCenter.visibility = View.VISIBLE
        logoText.visibility = View.VISIBLE
    }

    // Function to hide the right side logo
    fun hideRightLogo(){
        logoImageRight.visibility = View.GONE
    }

    // Function to show the right side logo
    fun showRightLogo(){
        logoImageRight.visibility = View.VISIBLE
    }

    // Function to set the image of the right side logo
    fun setRightLogoImage(imageToSetId:Int){
        logoImageRight.setImageResource(imageToSetId)
    }

    // Function to show the back arrow
    fun showBackArrow(){
        backArrow.visibility = View.VISIBLE
    }

    // Function to hide the back arrow
    fun hideBackArrow(){
        backArrow.visibility = View.GONE
    }

    // Set the function to run when pressing the back arrow
    fun runWhenBackPressed(onClick: () -> Unit){
        backArrow.setOnClickListener {
            onClick()
        }
    }

}