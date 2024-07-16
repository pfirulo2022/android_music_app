package com.example.musicstream

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicstream.adapter.CategoryAdapter
import com.example.musicstream.adapter.SectionSongListAdapter
import com.example.musicstream.databinding.ActivityMainBinding
import com.example.musicstream.models.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCategories()
        setupSection()

    }

    // Categories
    fun getCategories() {
        FirebaseFirestore.getInstance().collection("category")
            .get().addOnSuccessListener {
                val categoryList = it.toObjects(CategoryModel::class.java)
                setupCategoryRecyclerView(categoryList)
            }
    }

    fun setupCategoryRecyclerView(categoryList: List<CategoryModel>) {
        categoryAdapter = CategoryAdapter(categoryList)
        binding.categoriesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.categoriesRecyclerView.adapter = categoryAdapter
    }

    //Sections

    fun setupSection() {
        FirebaseFirestore.getInstance().collection("sections")
            .document("secion_1")
            .get().addOnSuccessListener {
                val section = it.toObject(CategoryModel::class.java)
                section?.apply {
                    binding.section1Title.text = name
                    binding.section1RecyclerView.layoutManager = LinearLayoutManager(
                        this@MainActivity,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    binding.section1RecyclerView.adapter = SectionSongListAdapter(songs)
                    binding.section1MainLayout.setOnClickListener{
                        SongListActivity.category = section
                        startActivity(Intent(this@MainActivity,SongListActivity::class.java))
                    }
                }
            }
    }
}