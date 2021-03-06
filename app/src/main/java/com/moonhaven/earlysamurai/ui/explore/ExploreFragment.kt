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
import com.moonhaven.earlysamurai.adapters.UserListAdapter
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.interfaces.IRecyclerViewEventListener
import com.moonhaven.earlysamurai.viewmodels.UserViewModel

// Logic for the explore fragment
class ExploreFragment : Fragment(), IRecyclerViewEventListener {
    private lateinit var viewModel: UserViewModel

    private lateinit var titleView: TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: UserListAdapter
    private lateinit var recyclerLayoutManager:RecyclerView.LayoutManager


    // Initialize view model and views
    // Make sure logo is correct
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        titleView = root.findViewById(R.id.explore_title)
        recyclerView = root.findViewById(R.id.explore_recycler_view)

        (activity as MainActivity).setCorrectLogo(getString(R.string.explore_tab))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        bindObservers()
        viewModel.getUsers()

    }

    // Bind the view model observers
    // Make it run on ui thread as calls are being done to database
    // Update data of the recycler adapter with data from database
    private fun bindObservers(){
        viewModel.usersLiveData.observe(viewLifecycleOwner, {
            activity?.runOnUiThread{
                recyclerAdapter.updateData(it)
            }
        })
    }

    // Initialize and bind the layout manager and adapter to the recycler view.
    private fun initializeRecyclerView(){
        recyclerLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerAdapter = UserListAdapter(listOf(),this)
        recyclerView.adapter = recyclerAdapter

    }

    // Implement the interface function for pressing a card in the recycler view
    // Create a bundle to pass the user to the next fragment then navigate and pass bundle
    override fun onCellClickListener(userList: List<UserObject>, position: Int) {
        var bundle = Bundle()
        bundle.putParcelable("user",userList[position])
        (activity as MainActivity).findNavController(R.id.nav_host_fragment).navigate(R.id.navigation_user_info,bundle)
    }
}