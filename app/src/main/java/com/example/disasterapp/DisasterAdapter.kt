package com.example.disasterapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.disasterapp.databinding.ItemDisasterBinding

// typealias digunakan untuk memberi nama alias pada tipe fungsi, membuatnya lebih mudah dibaca [cite: 190, 191]
typealias OnClickDisaster = (Disaster) -> Unit

class DisasterAdapter(
    private val listDisaster: List<Disaster>,
    private val onClickDisaster: OnClickDisaster
) : RecyclerView.Adapter<DisasterAdapter.ItemDisasterViewHolder>() {

    // ViewHolder menyimpan referensi ke view dari setiap item [cite: 8]
    inner class ItemDisasterViewHolder(private val binding: ItemDisasterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Fungsi bind digunakan untuk mengisi data ke dalam view [cite: 149, 150]
        fun bind(data: Disaster) {
            with(binding) {
                txtDisasterName.text = data.nameDisaster
                txtDisasterType.text = data.disasterType

                // Menetapkan listener klik pada setiap item view [cite: 210]
                itemView.setOnClickListener {
                    onClickDisaster(data)
                }
            }
        }
    }

    // Metode ini dipanggil untuk membuat ViewHolder baru [cite: 86]
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDisasterViewHolder {
        val binding =
            ItemDisasterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemDisasterViewHolder(binding)
    }

    // Metode ini mengembalikan jumlah total item dalam daftar [cite: 88, 164]
    override fun getItemCount(): Int = listDisaster.size

    // Metode ini menghubungkan data pada posisi tertentu dengan ViewHolder [cite: 87, 167]
    override fun onBindViewHolder(holder: ItemDisasterViewHolder, position: Int) {
        holder.bind(listDisaster[position])
    }
}