package com.moonhaven.earlysamurai.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R
import com.moonhaven.earlysamurai.database.UserObject
import kotlinx.android.synthetic.main.fragment_user.view.*


class UserFragment:Fragment() {
    private var user: UserObject? = null
    

    private lateinit var aboutTitleTextView:TextView
    private lateinit var profileImageView:ImageView
    private lateinit var credibilityTextView: TextView
    private lateinit var userInfoTextView: TextView
    private lateinit var ideasInfoTextView: TextView
    private lateinit var ideasCategoryTextView: TextView
    private lateinit var quoteTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_user, container, false)

        user = arguments?.getParcelable("user")

        aboutTitleTextView = root.about_user_text_view
        profileImageView = root.profile_picture
        credibilityTextView = root.credibility_text_view
        userInfoTextView = root.user_info_text_view
        ideasInfoTextView = root.ideas_info_textView
        ideasCategoryTextView = root.ideas_category_textView
        quoteTextView = root.quote_textView



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).showBackArrowInHeaderBar()

        aboutTitleTextView.text = "${getString(R.string.about_user)} ${user?.getFirstName()}"
        credibilityTextView.text = "${getString(R.string.credibility_user)} ${user?.getCredibility()}"
        userInfoTextView.text = "${getString(R.string.name_user)} ${user?.getFirstName()}"

        var categories:String = ""
        user?.let {
            var i = 0
            val loopSize = it.getCategories().size
            while(i < loopSize){
                categories += it.getCategories()[i]
                if(i < loopSize-1) categories += ", \n"
                i++

            }
        }
        ideasCategoryTextView.text = "$categories ${getString(R.string.ideas_user)}"

    }
}