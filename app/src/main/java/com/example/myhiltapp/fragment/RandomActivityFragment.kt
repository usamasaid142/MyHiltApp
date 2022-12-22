package com.example.myhiltapp.fragment

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Display
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myhiltapp.MainActivity
import com.example.myhiltapp.R
import com.example.myhiltapp.databinding.RandomActivityfragmentBinding
import com.example.myhiltapp.model.ResponseActivity
import com.example.myhiltapp.viewmodel.FavActivityViewmodel
import com.example.myhiltapp.viewmodel.RandomActivityviewmodel
import kotlin.random.Random

class RandomActivityFragment : Fragment() {

   private lateinit var binding:RandomActivityfragmentBinding

   private lateinit var randomActivityviewmodel: RandomActivityviewmodel
   private lateinit var viewmodel:FavActivityViewmodel
   private lateinit var responseActivity:ResponseActivity
   private var dialog:Dialog?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= RandomActivityfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       viewmodel=(activity as MainActivity).mfavviewmodel
        randomActivityviewmodel=ViewModelProvider(this).get(RandomActivityviewmodel::class.java)
        randomActivityObsreve()
        binding.swiperefresh.setOnRefreshListener {
            randomActivityviewmodel.getAllRandomActivity()
            saveRandomActivity()
        }

        saveRandomActivity()

    }

    private fun saveRandomActivity() {
       binding.ivFav.setImageDrawable(
           ContextCompat.getDrawable(
               requireContext(),R.drawable.ic_unselected_fav
           )
       )

        var addtofavorite=false
        binding.ivFav.setOnClickListener {
            if (addtofavorite){
                Toast.makeText(requireContext(),"you already saved it ",Toast.LENGTH_SHORT).show()
            } else{

                viewmodel.insert(responseActivity)
                addtofavorite=true
                binding.ivFav.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),R.drawable.ic_favorite
                    )
                )

                Toast.makeText(requireContext(),"added to favorite ",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun randomActivityObsreve() {
        randomActivityviewmodel.response.observe(viewLifecycleOwner) {

            it?.let {
                responseActivity=it
                if (binding.swiperefresh.isRefreshing) {
                    binding.swiperefresh.isRefreshing = false
                }
                binding.activity.text=it.activity
                binding.accessibility.text=it.accessibility.toString()
                binding.participants.text=it.participants.toString()
                binding.type.text=it.type
                binding.price.text=it.price.toString()
                if (it.link.isNullOrBlank()){
                    binding.btnSeemoredetails.visibility=View.GONE
                }else{
                    binding.btnSeemoredetails.visibility=View.VISIBLE
                    showActivityDetails(it)
                }

                color()


            }

        }

        randomActivityviewmodel.loadrandomActivity.observe(viewLifecycleOwner) {

            it?.let {
                if (it && !binding.swiperefresh.isRefreshing){

                    showDialog()
                }else{

                    hideDialog()
                }
            }

        }

        randomActivityviewmodel.randomActivityerror.observe(viewLifecycleOwner) {


            Log.e("tag","error${it}")

            if (binding.swiperefresh.isRefreshing){
                binding.swiperefresh.isRefreshing=false
            }

        }
        randomActivityviewmodel.getAllRandomActivity()

    }

    private fun hideDialog() {
       dialog?.dismiss()
    }

    private fun showDialog() {
        dialog= Dialog(requireActivity())
        dialog?.let {
        it.setContentView(androidx.vectordrawable.R.layout.custom_dialog)
            it.show()
        }
    }

    private fun color() {
        val random = java.util.Random()
        val color=Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256))
        binding.ivLink.setBackgroundColor(color)
    }

    private fun showActivityDetails(responseActivity: ResponseActivity) {
        binding.btnSeemoredetails.setOnClickListener {

            val action=RandomActivityFragmentDirections.actionRandomActivityFragmentToActivityDetailsFragment(responseActivity)
            findNavController().navigate(action)
        }
    }

}