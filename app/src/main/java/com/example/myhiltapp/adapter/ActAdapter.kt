package com.example.myhiltapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myhiltapp.databinding.ItemLayoutRandomBinding
import com.example.myhiltapp.fragment.FavouriteFragmentDirections
import com.example.myhiltapp.model.ResponseActivity


class ActAdapter(private val action:Action):ListAdapter<ResponseActivity,ActAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view=ItemLayoutRandomBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favactv=getItem(position)
        holder.binding.osama=favactv
//        holder.binding.apply {
//            activity.text=favactv.activity
//            accessibility.text=favactv.accessibility.toString()
//            type.text=favactv.type
//            price.text=favactv.price.toString()
//            participants.text=favactv.participants.toString()
//        }

        holder.itemView.setOnClickListener {
            if (favactv.link.isNotBlank()){
                val action=FavouriteFragmentDirections.actionFavouriteFragmentToActivityDetailsFragment(favactv)
                it.findNavController().navigate(action)
            }else{
                Toast.makeText(it.context,"the link is not blank", Toast.LENGTH_SHORT).show()
            }

        }

        holder.binding.ivFav.setOnClickListener {
            action.delete(favactv)
        }

    }


    class ViewHolder(itemBinding: ItemLayoutRandomBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: ItemLayoutRandomBinding = itemBinding
    }


    private class DiffCallback : DiffUtil.ItemCallback<ResponseActivity>() {
        override fun areItemsTheSame(oldItem: ResponseActivity, newItem: ResponseActivity): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: ResponseActivity, newItem: ResponseActivity): Boolean {
            return true
        }
    }


    interface Action{
        fun delete(responseActivity: ResponseActivity)
    }


}