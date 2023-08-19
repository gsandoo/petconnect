package com.haneum.petconnect.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haneum.petconnect.MainActivity
import com.haneum.petconnect.databinding.FragmentCommunityBinding

class CommunityFragment() : Fragment() {

    private var _binding : FragmentCommunityBinding? = null
    private val binding get() = _binding!!
    private lateinit var writePostFragment: WritePostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCommunityBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        writePostFragment = WritePostFragment()

        binding.fbtWrite.setOnClickListener{
            val activity = activity as MainActivity?
            activity?.changeFragment(writePostFragment)
            activity?.setVisibility(View.GONE)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommunityFragment().apply {
                arguments = Bundle().apply {
                    putString("", param1)
                    putString("", param2)
                }
            }
    }
}