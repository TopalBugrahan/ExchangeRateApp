package com.example.xmljsoup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var button:Button
    lateinit var sp_alis_value:TextView
    lateinit var sp_satis_value:TextView
    lateinit var banka_alis_value:TextView
    lateinit var banka_satis_value:TextView
    lateinit var header:TextView
    lateinit var currencyArr:MutableList<Currency>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp_alis_value=findViewById(R.id.sp_alis_value)
        sp_satis_value=findViewById(R.id.sp_satis_value)
        banka_alis_value=findViewById(R.id.banka_alis_value)
        banka_satis_value=findViewById(R.id.banka_satis_value)
        header=findViewById(R.id.header)
        val xml =XmlResult()
        var run = Runnable {
            currencyArr = xml.xmlCurrency()
            Log.d("currencyArr",currencyArr.toString())

            runOnUiThread {
                header.text="Bu Uygulamada Bulununan Tüm Bilgiler ${currencyArr.get(0).Tarih} Tarihinde Merkez Bankasından Alınmıştır"
            }
        }
        Thread(run).start()
        button=findViewById(R.id.button1)
        button.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,button)

            popupMenu.menuInflater.inflate(R.menu.menu_popup,popupMenu.menu)
            for(item in currencyArr){
                popupMenu.getMenu().add(item.Isim);
            }
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                var data =currencyArr.filter {
                    it.Isim==item.toString()
                }
                Log.d("data",data.toString())
                val realData=data.get(0)
                sp_alis_value.text=realData.ForexBuying
                sp_satis_value.text=realData.ForexSelling
                banka_satis_value.text=realData.BanknoteSelling
                banka_alis_value.text=realData.BanknoteBuying
                //ak git buradan
                true
            })
            popupMenu.show()
        }



    }
}