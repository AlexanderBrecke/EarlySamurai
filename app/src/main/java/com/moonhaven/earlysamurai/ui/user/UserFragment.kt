package com.moonhaven.earlysamurai.ui.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R
import com.moonhaven.earlysamurai.R.*
import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.enums.IdeaStatus
import com.moonhaven.earlysamurai.viewmodels.IdeasViewModel
import kotlinx.android.synthetic.main.fragment_user.view.*


class UserFragment:Fragment() {

    private lateinit var viewModel:IdeasViewModel

    private var user: UserObject? = null

    private var soldIdeas:Int = 0
    

    private lateinit var aboutTitleTextView:TextView
    private lateinit var profileImageView:ImageView
    private lateinit var credibilityTextView: TextView
    private lateinit var userInfoTextView: TextView
    private lateinit var ideasSoldInfoTextView: TextView
    private lateinit var ideasCategoryTextView: TextView
    private lateinit var ideasAvailableTextView: TextView
    private lateinit var quoteTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(IdeasViewModel::class.java)
        val root = inflater.inflate(layout.fragment_user, container, false)

        user = arguments?.getParcelable("user")

        aboutTitleTextView = root.about_user_text_view
        profileImageView = root.profile_picture
        credibilityTextView = root.credibility_text_view
        userInfoTextView = root.user_info_text_view
        ideasSoldInfoTextView = root.ideas_sold_info_textView
        ideasCategoryTextView = root.ideas_category_textView
        ideasAvailableTextView = root.ideas_available_textView
        quoteTextView = root.quote_textView



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).showBackArrowInHeaderBar()
        user?.let {
            viewModel.getUserIdeas(it.getId())
            bindObservers()
        }


    }

    private fun bindObservers(){
        viewModel.userIdeasLiveData.observe(viewLifecycleOwner, {
            soldIdeas = getSoldIdeasCount(it)
            fillOutUserInformation()
        })
    }

    @SuppressLint("SetTextI18n")
    private fun fillOutUserInformation(){
        aboutTitleTextView.text = "${getString(string.about_user)} ${user?.getFirstName()}"
        credibilityTextView.text = "${getString(string.credibility_user)} ${user?.getCredibility()}"
        userInfoTextView.text = "${getString(string.name_user)} ${user?.getFirstName()}"
        ideasSoldInfoTextView.text = "${getString(string.sold_user_start)} $soldIdeas ${getString(string.ideas_user)}"

        var categories:String = ""
        user?.let {
            var i = 0
            val loopSize = it.getCategories().size
            while(i < loopSize){
                categories += it.getCategories()[i]
                if(i < loopSize-1) categories += ", "
                i++
            }
        }
        ideasCategoryTextView.text = "$categories ${getString(string.ideas_user)}"
        ideasAvailableTextView.text = "${getString(string.ideas_available_start)} ${viewModel.userIdeasLiveData.value?.size} ${getString(string.ideas_user)} ${getString(string.ideas_available_end)}"
        quoteTextView.text = user?.getQuote()
    }

    private fun getSoldIdeasCount(allIdeas:List<IdeaObject>):Int{
        var count = 0
        for(idea in allIdeas){
            if(idea.getStatus() == IdeaStatus.Sold) count ++
        }
        return count
    }
}