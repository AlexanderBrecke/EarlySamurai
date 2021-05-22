package com.moonhaven.earlysamurai.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.interfaces.IRecyclerViewEventListener
import com.moonhaven.earlysamurai.viewmodels.UserViewModel

// Logic for the explore fragment
class ExploreFragment : Fragment(), IRecyclerViewEventListener {

    // Setup values for view model and views
    private lateinit var viewModel: UserViewModel

    private lateinit var titleView: TextView
    private lateinit var recyclerView: RecyclerView

    // Setup value for the recycler adapter and manager
    private lateinit var recyclerAdapter:UserListAdapter
    private lateinit var recyclerLayoutManager:RecyclerView.LayoutManager


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Initialize the view model and the fragment view
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)

        // Initialize the views
        titleView = root.findViewById(R.id.explore_title)
        recyclerView = root.findViewById(R.id.explore_recycler_view)

        // Make sure the logo is correct one then return the fragment view
        (activity as MainActivity).setCorrectLogo(getString(R.string.explore_tab))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize recycler view, bind the observers for view models and fetch information
        initializeRecyclerView()
        bindObservers()
        viewModel.getUsers()

    }

    // Bind the view model observers
    private fun bindObservers(){
        // Observe the users live data in the view model
        viewModel.usersLiveData.observe(viewLifecycleOwner, {
            // Make it run on ui thread as calls are being done to room database
            activity?.runOnUiThread{
                // Update the data of the user list adapter being used to show all users in the recycler view
                recyclerAdapter.updateData(it)
            }
        })
    }

    // Initialize and bind the layout manager and adapter to the recycler view.
    private fun initializeRecyclerView(){
        // Simply use a linear layout manager
        recyclerLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerLayoutManager

        // Using the User list adapter, pass it a list and use this for the event listener
        recyclerAdapter = UserListAdapter(listOf(),this)
        recyclerView.adapter = recyclerAdapter

    }

    // Implement the interface function for pressing a card in the recycler view
    override fun onCellClickListener(userList: List<UserObject>, position: Int) {
        // Create a bundle to pass the user to the next fragment
        var bundle = Bundle()
        bundle.putParcelable("user",userList[position])
        // Navigate to the next fragment and pass the bundle so we have access to the user information
        (activity as MainActivity).findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_user_info,bundle)
    }
}