package com.example.sgrticket.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sgrticket.R
import com.example.sgrticket.models.Ticket

import java.lang.reflect.Array
import java.time.Clock.tick

class AllTicketsAdapter(val tickets: ArrayList<Ticket>) : RecyclerView.Adapter<AllTicketsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPassengerName: TextView = view.findViewById(R.id.cname)
        val txtSourceFrom: TextView = view.findViewById(R.id.source)
        val txtDestinationTo: TextView = view.findViewById(R.id.destination)
        val txtTktNumber: TextView = view.findViewById(R.id.ticketnumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ticketrow, parent, false)

        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = tickets[position]
        holder.txtTktNumber.text = currentItem.ticketnumber
        holder.txtPassengerName.text = currentItem.name
        holder.txtSourceFrom.text = currentItem.source
        holder.txtDestinationTo.text = currentItem.destination

    }

    override fun getItemCount() = tickets.size


}
