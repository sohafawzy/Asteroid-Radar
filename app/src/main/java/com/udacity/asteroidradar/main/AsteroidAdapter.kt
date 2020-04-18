package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding

class AsteroidAdapter  : RecyclerView.Adapter<AsteroidAdapter.AsteroidHolder>() {
    private lateinit var list: List<Asteroid>

    fun addServices(r: List<Asteroid>) {
        this.list = r
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidHolder {
        return AsteroidHolder(
            ItemAsteroidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (::list.isInitialized) list.size else 0
    }

    override fun onBindViewHolder(holder: AsteroidHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener{ view ->
            val action = MainFragmentDirections.actionShowDetail(list[position])
            view.findNavController().navigate(action)
        }
    }


    class AsteroidHolder(private val binding: ItemAsteroidBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = AsteroidViewModel()

        fun bind(asteroids: Asteroid) {
            viewModel.bind(asteroids)
            binding.viewModel = viewModel
        }
    }

}