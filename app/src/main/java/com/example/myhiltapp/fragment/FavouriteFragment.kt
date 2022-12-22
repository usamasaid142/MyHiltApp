package com.example.myhiltapp.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhiltapp.MainActivity
import com.example.myhiltapp.R
import com.example.myhiltapp.adapter.ActAdapter
import com.example.myhiltapp.databinding.FavouritefragmentBinding
import com.example.myhiltapp.model.ResponseActivity
import com.example.myhiltapp.viewmodel.FavActivityViewmodel
import com.example.runningapp.alertdialog.Alert


class FavouriteFragment : Fragment(),ActAdapter.Action{

    private lateinit var binding:FavouritefragmentBinding
    private lateinit var viewmodel:FavActivityViewmodel
    private lateinit var favadabpter:ActAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FavouritefragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel=(activity as MainActivity).mfavviewmodel

        setupresyclerview()
    }

    private fun setupresyclerview() {
        favadabpter= ActAdapter(this)
        binding.rvRandomactivity.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=favadabpter
            setHasFixedSize(true)
        }
        viewmodel.allactivities.observe(viewLifecycleOwner) {

            it?.let {
                favadabpter.submitList(it)
                updateui(it)
            }

        }

    }

    private fun updateui(it: List<ResponseActivity>) {

        if(it.isNullOrEmpty()){

            binding.rvRandomactivity.visibility=View.GONE
            binding.tvActivity.visibility=View.VISIBLE

        }else{

            binding.rvRandomactivity.visibility=View.VISIBLE
            binding.tvActivity.visibility=View.GONE
        }

    }

    override fun delete(responseActivity: ResponseActivity) {
      //  viewmodel.delete(responseActivity)
        Alert.showMessage(android.R.drawable.ic_dialog_alert,
      requireContext(),R.string.msg,R.string.del,R.string.ok,DialogInterface.OnClickListener { dialog, which ->
                viewmodel.delete(responseActivity)
                dialog.dismiss()
            },R.string.no,DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            },true
        )




     //   deleteFavActivity(responseActivity)
    }

//    fun deleteFavActivity(favActivity: ResponseActivity) {
//        val builder = AlertDialog.Builder(requireActivity())
//        builder.setTitle("Delete Activity")
//
//        builder.setMessage("Are you sure wants to delete this activity?")
//
//        builder.setIcon(
//            android.R.drawable.ic_dialog_alert)
//
//        builder.setPositiveButton(
//            "YES"
//        ) { dialogInterface, _ ->
//            viewmodel.delete(favActivity)
//            Toast.makeText(
//                activity,
//                " You delete your activity",
//                Toast.LENGTH_SHORT
//            ).show()
//            dialogInterface.dismiss()
//        }
//
//        builder.setNegativeButton(
//            "NO"
//        ) { dialogInterface, _ ->
//
//            dialogInterface.dismiss()
//        }
//
//        val alertDialog: AlertDialog = builder.create()
//        alertDialog.setCancelable(false)
//        alertDialog.show()
//
//    }


}