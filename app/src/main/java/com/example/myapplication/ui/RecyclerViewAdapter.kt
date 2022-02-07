package com.example.myapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.FarmIssue

class RecyclerViewAdapter(private val farmIssueList: List<FarmIssue>, val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return CustomViewHolder(view)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.editAddress.text = farmIssueList[position].editAddress
        holder.editNum.text = farmIssueList[position].editNum
        holder.imgUri.text = farmIssueList[position].imgUrl
    }

    override fun getItemCount(): Int {
        return farmIssueList.size
    }


    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val editAddress: TextView = itemView.findViewById(R.id.editAddress)
        val editNum: TextView = itemView.findViewById(R.id.editNum)
        val imgUri: TextView = itemView.findViewById(R.id.imgUrl)
    }

    }