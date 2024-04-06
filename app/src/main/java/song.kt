package com.example.fragmentexercise.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentexercise.R
import com.example.fragmentexercise.adapter.SongAdapter
import com.example.fragmentexercise.databinding.FragmentSongsBinding

class song : Fragment() {
    private var _binding: FragmentSongsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSongsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSongList()
        backButton()
    }

    private fun backButton() {
        binding.backBtn.setOnClickListener {
            val navController = findNavController(requireActivity(), R.id.fragNav)
            navController.popBackStack()
        }
    }

    private fun showSongList() {
        val bundle = arguments
        val dataList = bundle?.getStringArrayList("songList")
        if (dataList != null) {
            for(i in dataList){
                Log.d("songs", i.toString())
            }
        }
        val adapter = dataList?.let { SongAdapter(it) }
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvSong.layoutManager = linearLayoutManager
        binding.rvSong.adapter = adapter
    }


}