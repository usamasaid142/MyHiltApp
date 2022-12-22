package com.example.myhiltapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.myhiltapp.R
import com.example.myhiltapp.databinding.ActivityDetailsfragmentBinding


class ActivityDetailsFragment : Fragment() {

    private  var _binding:ActivityDetailsfragmentBinding?=null
    private val binding  get() = _binding!!

    private  val args: ActivityDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= ActivityDetailsfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {
            webViewClient= WebViewClient()
            loadUrl(args.randomactivity!!.link)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}