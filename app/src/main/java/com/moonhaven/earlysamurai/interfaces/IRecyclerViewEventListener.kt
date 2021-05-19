package com.moonhaven.earlysamurai.interfaces

import com.moonhaven.earlysamurai.database.UserObject

interface IRecyclerViewEventListener {
    fun onCellClickListener(userList:List<UserObject>, position: Int)
}