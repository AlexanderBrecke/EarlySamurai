package com.moonhaven.earlysamurai.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.interfaces.IRecyclerViewEventListener
import com.moonhaven.earlysamurai.ui.custom.CustomUserCard

// Logic for the adapter to show users in recycler view
class UserListAdapter(
    private var dataset:List<UserObject>,
    private val recyclerEventListener: IRecyclerViewEventListener):RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    // Create the view holder using the custom user card as list item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItem = CustomUserCard(parent.context)
        return ViewHolder(listItem)
    }

    // Get current user form dataset
    // Set the text and add a click listener to the card
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userObject = dataset[position]

        holder.listItem.setUserText("${userObject.getFirstName()}, ${userObject.getCity()} - CV")
        holder.listItem.setOnClickListener {
            recyclerEventListener.onCellClickListener(dataset,position)
        }
    }

    // Function to get item count
    // Simply return the size of dataset
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Function to update data
    // Get new data and set dataset to this
    // Then notify dataset changed
    fun updateData(newData:List<UserObject>){
        dataset = newData
        notifyDataSetChanged()
    }

    // Class to set the custom user card as the view holder
    inner class ViewHolder(val listItem: CustomUserCard):RecyclerView.ViewHolder(listItem)

}