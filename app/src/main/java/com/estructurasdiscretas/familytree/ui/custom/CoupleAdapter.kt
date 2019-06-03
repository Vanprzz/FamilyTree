package com.estructurasdiscretas.familytree.ui.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estructurasdiscretas.familytree.R
import com.estructurasdiscretas.familytree.model.schema.Person
import kotlinx.android.synthetic.main.item_couple.view.*

class CoupleAdapter(
    private val context: Context,
    private val myDataSet: MutableList<Pair<Person?,Person?> >,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CoupleAdapter.CoupleHolder>() {

    class CoupleHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CoupleHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_couple, parent, false) as View

        return CoupleHolder(view)
    }

    override fun onBindViewHolder(holder: CoupleHolder, position: Int) {
        holder.view.contentA.visibility = View.VISIBLE
        holder.view.contentB.visibility = View.VISIBLE

        if( myDataSet[position].first == null ){
            holder.view.contentA.visibility = View.GONE
        }

        if( myDataSet[position].second == null ){
            holder.view.contentA.visibility = View.GONE
        }


        holder.view.tvContentA.text = myDataSet[position].first?.name
        holder.view.tvContentB.text = myDataSet[position].second?.name


        holder.view.cardviewCenter.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount() = myDataSet.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}