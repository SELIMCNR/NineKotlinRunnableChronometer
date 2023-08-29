package com.cinar.runnablekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.cinar.runnablekotlin.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    var number = 0
    var runnable : Runnable = Runnable {}
    var handler : Handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       // Thread.sleep(4000) main threading yapar kullanıcı arayüzünde işlem yaptırır bize gerekli olan arka planda işlem yapma
        //threading - paralell  - işlemcinin farklı çekirdeklerinde işlem yaptırır arka planda

        //Runnable arka planda androide işlem yaptırır
        //Handler runnableyi kullandırır

    }

    fun Start(view:View){
        number = 0

        runnable = object  :Runnable {
            override fun run() {
                //Arka planda şu işlemleri yap
                number = number+1
                binding.timeText.text = "Time : ${number}"
                handler.postDelayed(runnable,1000) // 1 saniyede 1 işlem yap
           }
        }
    handler.post(runnable) // runnableyi başlat
    binding.button.isEnabled = false
    }


    fun  Stop(view:View){
    binding.button.isEnabled = true
    number = 0
    binding.timeText.text = "Time : 0"
    handler.removeCallbacks(runnable) // runnable işlemini bitir arka planda

    }
}