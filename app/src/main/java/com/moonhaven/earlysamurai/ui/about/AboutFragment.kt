package com.moonhaven.earlysamurai.ui.about

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R.*
import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.enums.AboutFragmentState
import com.moonhaven.earlysamurai.enums.IdeaStatus
import com.moonhaven.earlysamurai.ui.custom.CustomInfoCard
import com.moonhaven.earlysamurai.ui.splash.SplashActivity
import com.moonhaven.earlysamurai.viewmodels.IdeasViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.view.*

// Logic class for the user fragment
class AboutFragment:Fragment() {

    // Setup values for the view model, user object, arbitrary value for sold ideas and all views
    private lateinit var viewModel:IdeasViewModel

    private var currentState:AboutFragmentState = AboutFragmentState.Info

    private var user: UserObject? = null
    private var soldIdeas:Int = 0

    private lateinit var aboutTitle:TextView
    private lateinit var profileImageView:ImageView
    private lateinit var credibilityTextView: TextView
    private lateinit var userInfo: CustomInfoCard
    private lateinit var ideasSoldInfo: CustomInfoCard
    private lateinit var ideasCategory: CustomInfoCard
    private lateinit var ideasAvailable: CustomInfoCard
    private lateinit var quote: CustomInfoCard

    private lateinit var miniPitchButton: Button
    private lateinit var bookButton: Button
    private lateinit var bookButton2:Button

    private lateinit var miniPitchTextView: TextView

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
        aboutTitle = root.about_user_text_view
        profileImageView = root.profile_picture
        credibilityTextView = root.credibility_text_view
        userInfo = CustomInfoCard(root.user_info)
        ideasSoldInfo = CustomInfoCard(root.ideas_sold_info)
        ideasCategory = CustomInfoCard(root.ideas_category_info)
        ideasAvailable = CustomInfoCard(root.ideas_available_info)
        quote = CustomInfoCard(root.quote_info)

        miniPitchButton = root.mini_pitch_button
        bookButton = root.book_meeting_button
        bookButton2 = root.book_meeting_button2

        miniPitchTextView = root.mini_pitch

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
            onStateChanged(currentState)
        }

        bookButton.setOnClickListener {
            (activity as MainActivity).startNewActivity(SplashActivity())
        }

        bookButton2.setOnClickListener {
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
        aboutTitle.text = "${getString(string.about_user)} ${user?.getFirstName()}"
        credibilityTextView.text = "${getString(string.credibility_user)} ${user?.getCredibility()}"
        userInfo.setInfoText("${getString(string.name_user)} ${user?.getFirstName()}")
        ideasSoldInfo.setInfoText("${getString(string.sold_user_start)} $soldIdeas ${getString(string.ideas_user)}")

//         loop in case we have more than one category
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
        ideasCategory.setInfoText("$categories ${getString(string.ideas_user)}")
        ideasAvailable.setInfoText("${getString(string.ideas_available_start)} ${viewModel.userIdeasLiveData.value?.size} ${getString(string.ideas_user)} ${getString(string.ideas_available_end)}")

        user?.let {
            if(it.getQuote() != null) quote.setInfoText(it.getQuote().toString())
            else quote.setVisibility(View.GONE)
        }
    }

    // Function to get the number of sold ideas by the user
    private fun getSoldIdeasCount(allIdeas:List<IdeaObject>):Int{
        var count = 0
        for(idea in allIdeas){
            if(idea.getStatus() == IdeaStatus.Sold) count ++
        }
        return count
    }

    private fun onStateChanged(state:AboutFragmentState){
        val infoViews = listOf(userInfo, ideasSoldInfo,ideasCategory, ideasAvailable,quote)

        currentState = if(state == AboutFragmentState.Info) AboutFragmentState.Pitch
        else AboutFragmentState.Info

        when (currentState){
            AboutFragmentState.Pitch -> {
                for(view in infoViews){
                    view.setVisibility(View.GONE)
                }
                credibilityTextView.visibility = View.GONE
                aboutTitle.text = getString(string.pitch)
                miniPitchButton.visibility = View.GONE
                bookButton.visibility = View.GONE
                bookButton2.visibility = View.VISIBLE

                miniPitchTextView.visibility = View.VISIBLE
                miniPitchTextView.text = user?.getPitch()

                (activity as MainActivity).setOtherBackArrowFunction {
                    onStateChanged(currentState)
                }
            }
            AboutFragmentState.Info -> {
                for (view in infoViews) view.setVisibility(View.VISIBLE)
                credibilityTextView.visibility = View.VISIBLE
                aboutTitle.text = "${getString(string.about_user)} ${user?.getFirstName()}"
                miniPitchButton.visibility = View.VISIBLE
                bookButton.visibility = View.VISIBLE
                bookButton2.visibility = View.GONE
                miniPitchTextView.visibility = View.GONE
                (activity as MainActivity).setRegularHeaderBackArrowFunction()
            }
        }
    }
}