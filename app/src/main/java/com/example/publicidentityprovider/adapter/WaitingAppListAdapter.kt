package com.example.publicidentityprovider.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.publicidentityprovider.details.AppInfo
import com.example.publicidentityprovider.R

class WaitingAppListAdapter (private val data : List<AppInfo>,
                            private val context : Context,
                            private val onItemClickListener: View.OnClickListener) :
                            RecyclerView.Adapter<WaitingAppListAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // retrieve the item at the specified position
        val currentItem = data[position]
        // put the data
        holder!!.waitingAppName.text = currentItem.appName
        holder!!.waitingAppDate.text = currentItem.date
        holder.waitingAppScope.text = currentItem.scopes.toString()
        holder.itemView.tag = position
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val waitingAppName : TextView = itemView.findViewById(R.id.waiting_activity_app_name)
        val waitingAppDate : TextView = itemView.findViewById(R.id.waiting_activity_app_date)
        val waitingAppScope : TextView =  itemView.findViewById(R.id.waiting_activity_scope)
    }

    // called when a new viewholder is required to display a row
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        // create the row from a layout inflater
        val rowView = LayoutInflater
            .from(context)
            .inflate(R.layout.items_for_activity_lists, parent, false)

        // attach the onclicklistener
        rowView.setOnClickListener(onItemClickListener)
        // create a ViewHolder using this rowview
        val viewHolder = ViewHolder(rowView)
        // return this ViewHolder. The system will make sure view holders
        // are used and recycled
        return viewHolder
    }


    override fun getItemCount(): Int {
        return data.size
    }
}