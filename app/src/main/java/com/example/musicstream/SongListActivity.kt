package com.example.musicstream

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicstream.adapter.SongListAdapter
import com.example.musicstream.databinding.ActivitySongListBinding
import com.example.musicstream.models.CategoryModel

class SongListActivity : AppCompatActivity() {

    companion object{
        lateinit var category : CategoryModel
    }
    lateinit var  binding : ActivitySongListBinding
    lateinit var songListAdapter: SongListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameTextView.text = category.name

        Glide.with(binding.coverImageView).load(category.coverUrl)
            .apply(
                RequestOptions().transform(RoundedCorners(32))
            )
            .into(binding.coverImageView)

        setupSongListRecyclerView()

    }

    fun setupSongListRecyclerView(){
        songListAdapter = SongListAdapter(category.songs)
        binding.songListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.songListRecyclerView.adapter = songListAdapter
    }
}