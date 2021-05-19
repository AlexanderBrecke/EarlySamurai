package com.moonhaven.earlysamurai.ui.logo

import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.header_bar.view.*

class HeaderBar(view: View) {

    private val logoImageCenter:ImageView = view.logo_image_mid
    private val logoText:ImageView = view.logo_text
    private var logoImageRight:ImageView = view.logo_image_right
    private var backArrow:ImageView = view.back_arrow

    init {
        hideMiddleLogo()
        hideBackArrow()
    }

    fun hideMiddleLogo(){
        logoImageCenter.visibility = View.GONE
        logoText.visibility = View.GONE
    }

    fun showMiddleLogo(){
        logoImageCenter.visibility = View.VISIBLE
        logoText.visibility = View.VISIBLE
    }

    fun hideRightLogo(){
        logoImageRight.visibility = View.GONE
    }

    fun showRightLogo(){
        logoImageRight.visibility = View.VISIBLE
    }

    fun showBackArrow(){
        backArrow.visibility = View.VISIBLE
    }

    fun hideBackArrow(){
        backArrow.visibility = View.GONE
    }

    fun runWhenBackPressed(onClick: () -> Unit){
        backArrow.setOnClickListener {
            onClick()
        }
    }

    fun setRightLogoImage(imageToSetId:Int){
        logoImageRight.setImageResource(imageToSetId)
    }



}