package com.moonhaven.earlysamurai.ui.deals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R

class DealsFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_deals, container, false)
        val textView: TextView = root.findViewById(R.id.text_deals)
        textView.text = getText(R.string.deals_tab)
        (activity as MainActivity).setCorrectLogo(getString(R.string.deals_tab))
        return root
    }
}