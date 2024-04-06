package com.example.fragmentassignment.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentassignment.adapter.SingerAdapter
import com.example.fragmentassignment.databinding.FragmentSingerBinding
import com.example.fragmentassignment.model.Album
import org.json.JSONArray

class SingerFragment : Fragment() {
    private var albumList = mutableListOf<Album>()
    private var _binding: FragmentSingerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSingerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showListOfSinger()
    }

    private fun showListOfSinger() {
        albumList.clear()
        val jsonString = context?.assets?.open("singer.json")?.bufferedReader().use { it?.readText() }
        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val singer = jsonObject.getString("singer")
            val jsonArray = jsonObject.getJSONArray("songs")
            val songList = mutableListOf<String>()
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.get(i).toString()
                songList.add(item)
            }
            val album = Album(singer, songList)
            albumList.add(album)
        }
        for(i in albumList){
            Log.d("list", "${i.getSinger()}, ${albumList.size}")

        }
        val adapter = context?.let { SingerAdapter(albumList, it) }
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvSinger.layoutManager = linearLayoutManager
        binding.rvSinger.adapter = adapter
    }
}

class FragmentSingerBinding {
    companion object {
        fun inflate(
            inflater: LayoutInflater,
            container: ViewGroup?,
            b: Boolean
        ): FragmentSingerBinding? {
            TODO("Not yet implemented")
        }
    }

}
