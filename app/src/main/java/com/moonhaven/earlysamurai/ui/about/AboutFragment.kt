package com.moonhaven.earlysamurai.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R
import com.moonhaven.earlysamurai.R.*
import com.moonhaven.earlysamurai.database.IdeaObject
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.enums.AboutFragmentState
import com.moonhaven.earlysamurai.enums.IdeaStatus
import com.moonhaven.earlysamurai.ui.custom.CustomInfoCard
import com.moonhaven.earlysamurai.utilities.Utils
import com.moonhaven.earlysamurai.viewmodels.IdeasViewModel
import kotlinx.android.synthetic.main.fragment_about.view.*

// Logic class for the user fragment
class AboutFragment:Fragment() {

    // Setup values for the view model, user object, arbitrary value for sold ideas and all views
    private lateinit var viewModel:IdeasViewModel

    private lateinit var currentState:AboutFragmentState

    private var user: UserObject? = null
    private var soldIdeas:Int = 0
    private var hasQuote = false

    private lateinit var loader:ProgressBar

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


    // Initialize values above
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(IdeasViewModel::class.java)
        currentState = AboutFragmentState.Info
        user = arguments?.getParcelable("user")

        val root = inflater.inflate(layout.fragment_about, container, false)

        loader = root.loader

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

        return root
    }

    // Show back arrow, set click listeners, start loader
    // Make sure we have a user, bind observers to view model and fetch information
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).showBackArrowInHeaderBar()
        setClickListeners()
        isLoading(true)

        user?.let {
            bindObservers()
            viewModel.getUserIdeas(it.getId())
        }
    }

    // Function to show/hide all views and opposite for loader
    private fun isLoading(loading:Boolean){

        val visibility:Int

        val regularViews = listOf(aboutTitle,profileImageView,credibilityTextView, miniPitchButton, bookButton)
        val customCardViews = listOf(userInfo,ideasSoldInfo,ideasCategory,ideasAvailable)

        if(loading){
            loader.visibility = View.VISIBLE
            visibility = View.GONE
        }
        else{
            loader.visibility = View.GONE
            visibility = View.VISIBLE
        }

        for(view in regularViews) view.visibility = visibility
        for(view in customCardViews) view.setVisibility(visibility)

        if(loading) quote.setVisibility(visibility)
        else if(!loading && hasQuote) quote.setVisibility(visibility)
    }

    // Set click listeners on mini pitch and book meeting buttons
    private fun setClickListeners(){
        miniPitchButton.setOnClickListener{
            onStateChanged(currentState)
        }
        bookButton.setOnClickListener {
            (activity as MainActivity).findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_booking)
        }
        bookButton2.setOnClickListener {
            (activity as MainActivity).findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_booking)
            onStateChanged(currentState)
        }
    }

    // Bind the view model observers
    // Observe the live data for user ideas
    // Set the value for how many sold ideas the user have and fill information to views
    private fun bindObservers(){
        viewModel.userIdeasLiveData.observe(viewLifecycleOwner, {
            soldIdeas = Utils.getStatusIdeasCount(it, IdeaStatus.Sold)
            fillOutUserInformation()
        })
    }

    // Function to set the values of views with information from the user
    // Start with loader
    // Create values for all info
    // Fill info into the views
    // Lastly stop loading
    private fun fillOutUserInformation(){
        isLoading(true)

        val aboutTitleText = "${getString(string.about_user)} ${user?.getFirstName()}"
        val credibilityText = "${getString(string.credibility_user)} ${user?.getCredibility()}"
        val userInfoText = "${getString(string.name_user)} ${user?.getFirstName()}"
        val ideasSoldText = "${getString(string.sold_user_start)} $soldIdeas ${getString(string.ideas_user)}"
        var categories = ""
        var quoteText = ""
        user?.let {
            categories = Utils.getCategoriesString(it.getCategories())

            if(it.getQuote() != null){
                hasQuote = true
                quoteText = it.getQuote().toString()
            }
        }
        val categoryText = "$categories ${getString(string.ideas_user)}"
        val availableIdeas = Utils.getStatusIdeasCount(viewModel.userIdeasLiveData.value as List<IdeaObject>,IdeaStatus.ForSale)
        val ideasAvailableText = "${getString(string.ideas_available_start)} $availableIdeas ${getString(string.ideas_user)} ${getString(string.ideas_available_end)}"


        aboutTitle.text = aboutTitleText
        credibilityTextView.text = credibilityText
        userInfo.setInfoText(userInfoText)
        ideasSoldInfo.setInfoText(ideasSoldText)
        ideasCategory.setInfoText(categoryText)
        ideasAvailable.setInfoText(ideasAvailableText)
        quote.setInfoText(quoteText)

        isLoading(false)
    }


    // Function to change state of the fragment
    // Setup list of all views in fragment
    // change the state of the fragment
    // Then set views to be either visible or hidden depending on which state fragment is in
    private fun onStateChanged(state:AboutFragmentState){
        val customCardInfoViews = listOf(userInfo, ideasSoldInfo,ideasCategory, ideasAvailable,quote)
        val regularInfoViews = listOf(credibilityTextView, miniPitchButton,bookButton)
        val pitchViews = listOf(bookButton2, miniPitchTextView)

        currentState = if(state == AboutFragmentState.Info) AboutFragmentState.Pitch
        else AboutFragmentState.Info

        when (currentState){
            AboutFragmentState.Pitch -> {
                aboutTitle.text = getString(string.pitch)
                miniPitchTextView.text = user?.getPitch()
                for(view in customCardInfoViews) view.setVisibility(View.GONE)
                for(view in regularInfoViews) view.visibility = View.GONE
                for(view in pitchViews) view.visibility = View.VISIBLE
                (activity as MainActivity).setOtherBackArrowFunction {
                    onStateChanged(currentState)
                }
            }
            AboutFragmentState.Info -> {
                aboutTitle.text = "${getString(string.about_user)} ${user?.getFirstName()}"
                for (view in customCardInfoViews) view.setVisibility(View.VISIBLE)
                for(view in regularInfoViews) view.visibility = View.VISIBLE
                for(view in pitchViews) view.visibility = View.GONE
                (activity as MainActivity).setRegularHeaderBackArrowFunction()
            }
        }
    }


}