package com.moonhaven.earlysamurai.ui.logo

import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.header_bar.view.*

class HeaderBar(view: View) {

    private val logoImageCenter:ImageView = view.logo_image_mid
    private val logoText:ImageView = view.logo_text
    private var logoImageRight:ImageView = view.logo_image_right

    init {

        hideMiddleLogo()


    }

    fun hideMiddleLogo(){
        logoImageCenter.visibility = View.INVISIBLE
        logoText.visibility = View.INVISIBLE
    }

    fun showMiddleLogo(){
        logoImageCenter.visibility = View.VISIBLE
        logoText.visibility = View.VISIBLE
    }

    fun hideRightLogo(){
        logoImageRight.visibility = View.INVISIBLE
    }

    fun showRightLogo(){
        logoImageRight.visibility = View.VISIBLE
    }

    fun setRightLogoImage(imageToSetId:Int){

        logoImageRight.setImageResource(imageToSetId)

//        logoImageRight.setImageDrawable(imageToSet)
    }



}