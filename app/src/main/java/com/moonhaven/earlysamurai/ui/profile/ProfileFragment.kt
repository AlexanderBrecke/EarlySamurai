package com.moonhaven.earlysamurai.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R

class ProfileFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val textView: TextView = root.findViewById(R.id.text_profile)
        textView.text = getText(R.string.profile_tab)
        (activity as MainActivity).setCorrectLogo(getString(R.string.profile_tab))
        return root
    }
}