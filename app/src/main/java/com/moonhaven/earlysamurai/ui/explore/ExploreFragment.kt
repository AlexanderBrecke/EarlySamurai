package com.moonhaven.earlysamurai.ui.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moonhaven.earlysamurai.MainActivity
import com.moonhaven.earlysamurai.R
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.interfaces.IRecyclerViewEventListener

class ExploreFragment : Fragment(), IRecyclerViewEventListener {

    private lateinit var viewModel: ExploreViewModel

    private lateinit var titleView: TextView
    private lateinit var recyclerView: RecyclerView

    private lateinit var recyclerAdapter:UserListAdapter
    private lateinit var recyclerLayoutManager:RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ExploreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)

        titleView = root.findViewById(R.id.explore_title)
        recyclerView = root.findViewById(R.id.explore_recycler_view)

        (activity as MainActivity).setCorrectLogo(getString(R.string.explore_tab))
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        recyclerAdapter = UserListAdapter(viewModel.getUsers(),this)

        initializeRecyclerView()
        bindObservers()
        viewModel.getUsers()

    }

    private fun bindObservers(){
        viewModel.usersLiveData.observe(viewLifecycleOwner, {

            viewModel.getUsers()
            activity?.runOnUiThread{

                recyclerAdapter.updateData(it)

                // Here we would update the user adapter for the recycler view

//                textView.text = "There are currently '${it.count()}' users in the database."
            }
        })
    }

    private fun initializeRecyclerView(){
        recyclerLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = recyclerLayoutManager

        recyclerAdapter = UserListAdapter(listOf(),this)
        recyclerView.adapter = recyclerAdapter

    }

    override fun onCellClickListener(userList: List<UserObject>, position: Int) {
        Log.d("FOO", "Pressed user with id: ${userList[position].getId()}")
    }
}