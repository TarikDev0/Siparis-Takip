package com.tarik.zerbetsiparistakip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class MyAdapter(private val siparisList : ArrayList<SiparislerR>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.siparis_duzen,
            parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return siparisList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = siparisList[position]
        holder.subeadimiz.text = currentItem.sube
        holder.sadeAdetText.text = currentItem.sadeAdet.toString()
        holder.kolAdetText.text = currentItem.kolAdet.toString()
        holder.pogacaAdetText.text = currentItem.pogacaAdet.toString()
        holder.acmaAdetText.text = currentItem.acmaAdet.toString()
        holder.simitAdetText.text = currentItem.simitAdet.toString()
        holder.otluAdetText.text = currentItem.otluAdet.toString()
        holder.icliAdetText.text = currentItem.icliAdet.toString()
        holder.toplamTutarText.text = currentItem.toplamTutar.toString()
        holder.tarihtext.text = currentItem.tarih.toString()



        val isVisible : Boolean = currentItem.visibility
        holder.constraintLayout.visibility = if (isVisible) View.VISIBLE else View.GONE

        holder.subeadimiz.setOnClickListener{
            currentItem.visibility = !currentItem.visibility
            notifyItemChanged(position)
        }

    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val subeadimiz : TextView = itemView.findViewById(R.id.subeAdi)
        val sadeAdetText : TextView = itemView.findViewById(R.id.sadeAdet)
        val kolAdetText : TextView = itemView.findViewById(R.id.kolAdet)
        val pogacaAdetText : TextView = itemView.findViewById(R.id.pogacaAdet)
        val acmaAdetText : TextView = itemView.findViewById(R.id.acmaAdet)
        val simitAdetText : TextView = itemView.findViewById(R.id.simitAdet)
        val otluAdetText : TextView = itemView.findViewById(R.id.otluAdet)
        val icliAdetText : TextView = itemView.findViewById(R.id.icliAdet)
        val toplamTutarText : TextView = itemView.findViewById(R.id.tutar)
        val tarihtext: TextView = itemView.findViewById(R.id.tarih)
        val constraintLayout : ConstraintLayout = itemView.findViewById(R.id.expandedLayout)


    }
}