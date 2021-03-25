package com.example.sgrticket.adapters


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sgrticket.models.Ticket

import java.lang.reflect.Array


class AllTicketsAdapter(val tickets: List<Ticket>) : RecyclerView.Adapter<AllTicketsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return 0//tickets.size()
        TODO("Not yet implemented")
    }

}


