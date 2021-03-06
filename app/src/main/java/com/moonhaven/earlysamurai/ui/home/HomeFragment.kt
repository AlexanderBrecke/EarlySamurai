package com.moonhaven.earlysamurai.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R

class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        textView.text = getString(R.string.home_tab)
        (activity as MainActivity).setCorrectLogo(getString(R.string.home_tab))
        return root

    }
}