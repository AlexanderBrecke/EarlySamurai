package com.moonhaven.earlysamurai.interfaces

import com.moonhaven.earlysamurai.database.UserObject

// Interface for event listeners in recycler view
interface IRecyclerViewEventListener {
    // Function for when item is clicked
    fun onCellClickListener(userList:List<UserObject>, position: Int)
}