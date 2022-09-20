package no.kasperi.Abstraction

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import no.kasperi.Models.LocalModel

class DiffUtilClass<T : LocalModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}