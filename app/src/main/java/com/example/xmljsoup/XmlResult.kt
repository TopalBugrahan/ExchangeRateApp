package com.example.xmljsoup

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class XmlResult {
    fun xmlCatalog():MutableList<Plant>{
        var arr = mutableListOf<Plant>()
        val url="https://www.w3schools.com/xml/plant_catalog.xml"
        val doc : Document=Jsoup.connect(url).timeout(15000).ignoreContentType(true).get()
        val elements:Elements=doc.getElementsByTag("PLANT")
        for(element in elements){
            var COMMON=element.getElementsByTag("COMMON").text()
            var BOTANICAL=element.getElementsByTag("BOTANICAL").text()
            var ZONE=element.getElementsByTag("ZONE").text()
            var LIGHT=element.getElementsByTag("LIGHT").text()
            var PRICE=element.getElementsByTag("PRICE").text()
            val plant = Plant(COMMON,BOTANICAL,ZONE,LIGHT,PRICE)
            arr.add(plant)
        }
        return arr
    }

    fun xmlCurrency ():MutableList<Currency>{
        var arr = mutableListOf<Currency>()
        val url="https://www.tcmb.gov.tr/kurlar/today.xml"
        val doc : Document=Jsoup.connect(url).timeout(15000).ignoreContentType(true).get()
        val TarihXml=doc.getElementsByTag("Tarih_Date")
        var Tarih=TarihXml.attr("Tarih")
        Log.d("Tarih",Tarih)
        val elements:Elements=doc.getElementsByTag("Currency ")
        for(element in elements){

            var Isim=element.getElementsByTag("Isim").text()
            var CurrencyName=element.getElementsByTag("CurrencyName").text()
            var ForexBuying=element.getElementsByTag("ForexBuying").text()
            var ForexSelling=element.getElementsByTag("ForexSelling").text()
            var BanknoteBuying=element.getElementsByTag("BanknoteBuying").text()
            var BanknoteSelling=element.getElementsByTag("BanknoteSelling").text()

            val currency = Currency(Isim,CurrencyName,ForexBuying,ForexSelling,BanknoteBuying,BanknoteSelling,Tarih)
            arr.add(currency)
        }
        return arr
    }
}