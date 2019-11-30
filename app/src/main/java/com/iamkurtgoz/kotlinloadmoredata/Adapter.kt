package com.iamkurtgoz.kotlinloadmoredata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val arrayList: ArrayList<Model?>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val VIEW_TYPE_ITEM = 0 //Normal item
    private val VIEW_TYPE_LOADING = 1 //Yükleniyor

    override fun getItemViewType(position: Int): Int {
        if (arrayList[position] == null){ //Eğer model boş ise (arrayList.add(null) ile kullandık) yükleniyor
            return VIEW_TYPE_LOADING
        } else { //Değilse normal item
            return VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM){ //view type değerine göre görüntü layout'u ekliyoruz
            return HolderModel(LayoutInflater.from(parent.context).inflate(R.layout.list_item_text_row, parent, false))
        } else {
            return HolderLoading(LayoutInflater.from(parent.context).inflate(R.layout.list_item_loading_row, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(recyclerHolder: RecyclerView.ViewHolder, position: Int) {
        val model: Model? = arrayList[position]

        if (recyclerHolder is HolderLoading){ //Yükleniyor
            var holder: HolderLoading = recyclerHolder
            //Yükleniyor kısmı. progress bar burada
        } else if (recyclerHolder is HolderModel){ //Normal item
            var holder: HolderModel = recyclerHolder
            //burada işlem olarak text içeriğini değiştiriyoruz
            holder.textTitle.text = "Position : ${model?.position}"
        }
    }
}