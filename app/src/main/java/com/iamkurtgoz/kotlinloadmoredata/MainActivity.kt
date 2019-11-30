package com.iamkurtgoz.kotlinloadmoredata

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val loadTime: Long = 1000 * 2L //yüklenme süresini 2 saniye olarak aldık
    private var isLoading: Boolean = false //Yüklenme işleminin başlamasını ve bitmesini kontrol eden bool değişkeni
    private var itemPosition: Int = 0 //pozisyon bilgisi

    private val arrayList: ArrayList<Model?> = ArrayList()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: Adapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this@MainActivity)

        adapter = Adapter(arrayList)
        recyclerView = findViewById(R.id.activity_main_recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var lastPosition: Int = layoutManager.findLastVisibleItemPosition() //Görünen son görünümün pozisyonunu alır
                var listSize: Int = arrayList.size //liste uzunluğu

                if (!isLoading && listSize == (lastPosition + 1)) { //eğer yüklenme işlemi yoksa ve liste en aşağıdaysa
                    addLoadMoreData() //yeni değerleri yükle
                    isLoading = true //yükleniypr
                }
            }
        })
        addItems()
    }

    private fun addLoadMoreData(){
        arrayList.add(null) //listeye boş bir görüntü model ekle. Bunu adapterda eğer boş ise yükleniyor anlamında kullanacağız
        adapter.notifyItemInserted(arrayList.size - 1) //adapter son itemi alıyor
        layoutManager.scrollToPosition(arrayList.size - 1) //listeyi kaydırıyoruz

        Handler().postDelayed({ //2 saniye sonra
            arrayList.removeAt(arrayList.size - 1) //yükleniyor progressBar item'ini siliyoruz
            adapter.notifyItemRemoved(arrayList.size) //son değerin silindiğini adaptera bildiriyoruz
            isLoading = false //Yüklenmiyor
            addItems() //yeni itemleri gönderiyoruz
        }, loadTime)
    }

    private fun addItems(){
        var i: Int = 0
        while (i < 20) {
            arrayList.add(Model(++itemPosition)) //itemleri ekliyor
            i++
        }
        adapter.notifyDataSetChanged() //listeyi yeniliyor
    }
}
