package com.moonhaven.earlysamurai.ui.explore

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonhaven.earlysamurai.EarlySamuraiApplication
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.interfaces.IRecyclerViewEventListener
import com.moonhaven.earlysamurai.ui.custom.CustomUserCard


class UserListAdapter(
    private var dataset:List<UserObject>,
    private val recyclerEventListener: IRecyclerViewEventListener):RecyclerView.Adapter<UserListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val listItem = CustomUserCard(parent.context)

        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userObject = dataset[position]

        holder.listItem.setUserText("${userObject.getFirstName()}, ${userObject.getCity()} - CV")

        holder.listItem.setOnClickListener {
            recyclerEventListener.onCellClickListener(dataset,position)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun updateData(newData:List<UserObject>){
        dataset = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(val listItem: CustomUserCard):RecyclerView.ViewHolder(listItem)

}
