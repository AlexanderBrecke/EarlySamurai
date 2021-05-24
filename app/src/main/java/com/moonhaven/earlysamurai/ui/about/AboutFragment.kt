package com.moonhaven.earlysamurai.ui.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.MeetingActivity
import com.moonhaven.earlysamurai.R.*
import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.enums.IdeaStatus
import com.moonhaven.earlysamurai.ui.splash.SplashActivity
import com.moonhaven.earlysamurai.viewmodels.IdeasViewModel
import kotlinx.android.synthetic.main.fragment_about.view.*

// Logic class for the user fragment
class AboutFragment:Fragment() {

    // Setup values for the view model, user object, arbitrary value for sold ideas and all views
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

    private lateinit var miniPitchButton: Button
    private lateinit var bookButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize the view model and the fragment view
        viewModel = ViewModelProvider(this).get(IdeasViewModel::class.java)
        val root = inflater.inflate(layout.fragment_about, container, false)

        // Get the user object from the bundle
        user = arguments?.getParcelable("user")

        // Initialize views
        aboutTitleTextView = root.about_user_text_view
        profileImageView = root.profile_picture
        credibilityTextView = root.credibility_text_view
        userInfoTextView = root.user_info_text_view
        ideasSoldInfoTextView = root.ideas_sold_info_textView
        ideasCategoryTextView = root.ideas_category_textView
        ideasAvailableTextView = root.ideas_available_textView
        quoteTextView = root.quote_textView

        miniPitchButton = root.mini_pitch_button
        bookButton = root.book_meeting_button

        // Return the fragment view
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show the back arrow in the header bar
        (activity as MainActivity).showBackArrowInHeaderBar()

        // Set click listeners for buttons
        setClickListeners()

        // Make sure we have a user object
        user?.let {
            // Bind the observers for view models and fetch information
            bindObservers()
            viewModel.getUserIdeas(it.getId())
        }
    }

    // Set click listeners on mini pitch and book meeting buttons
    private fun setClickListeners(){
        miniPitchButton.setOnClickListener{
            Log.d("FOO", "Pressed the 'see mini pitch button'")
        }

        bookButton.setOnClickListener {
            Log.d("FOO", "Pressed the 'book meeting button'")
//            (activity as MainActivity).startNewActivity(MeetingActivity())
            (activity as MainActivity).startNewActivity(SplashActivity())
        }
    }

    // Bind the view model observers
    private fun bindObservers(){
        // Observe the live data for user ideas
        viewModel.userIdeasLiveData.observe(viewLifecycleOwner, {
            // set the value for how many sold ideas the user have, then fill information to the views
            soldIdeas = getSoldIdeasCount(it)
            fillOutUserInformation()
        })
    }

    // Function to set the values of views with information from the user
    @SuppressLint("SetTextI18n")
    private fun fillOutUserInformation(){
        aboutTitleTextView.text = "${getString(string.about_user)} ${user?.getFirstName()}"
        credibilityTextView.text = "${getString(string.credibility_user)} ${user?.getCredibility()}"
        userInfoTextView.text = "${getString(string.name_user)} ${user?.getFirstName()}"
        ideasSoldInfoTextView.text = "${getString(string.sold_user_start)} $soldIdeas ${getString(string.ideas_user)}"

        // loop in case we have more than one category
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

    // Function to get the number of sold ideas by the user
    private fun getSoldIdeasCount(allIdeas:List<IdeaObject>):Int{
        var count = 0
        for(idea in allIdeas){
            if(idea.getStatus() == IdeaStatus.Sold) count ++
        }
        return count
    }
}