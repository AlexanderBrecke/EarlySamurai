package com.moonhaven.earlysamurai.ui.explore

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moonhaven.earlysamurai.database.UserObject
import com.moonhaven.earlysamurai.interfaces.IRecyclerViewEventListener
import com.moonhaven.earlysamurai.ui.custom.CustomUserCard

// Logic for the adapter to show users in recycler view
class UserListAdapter(
    private var dataset:List<UserObject>,
    private val recyclerEventListener: IRecyclerViewEventListener):RecyclerView.Adapter<UserListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Initialize the list item for our view holder. Make it an instance of the custom user card.
        val listItem = CustomUserCard(parent.context)

        // Return the view holder and give it the list item initialized
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

    // Class to set the custom user card as the view holder
    inner class ViewHolder(val listItem: CustomUserCard):RecyclerView.ViewHolder(listItem)

}