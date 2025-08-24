package com.example.rickandmortyapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.domain.model.Character
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.databinding.ItemCharacterBinding

class CharacterListAdapter :
    ListAdapter<Character, CharacterListAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    // ViewHolder -> representa cada item da lista
    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.characterNameTv.text = character.name
            binding.characterStatusTv.text = character.status
             Glide.with(binding.characterIv.context)
                .load(character.image)
                .into(binding.characterIv)
        }
    }

    // Cria o ViewHolder (infla o layout de cada item da lista)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterViewHolder(binding)
    }

    // Liga os dados do item da posição [position] ao ViewHolder
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }
}

// DiffUtil -> calcula diferença entre listas, evitando recriar tudo
class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id // geralmente pelo ID único
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem // compara conteúdo inteiro (data class ajuda aqui)
    }
}