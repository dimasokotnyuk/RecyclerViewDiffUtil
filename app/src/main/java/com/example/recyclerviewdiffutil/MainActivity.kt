package com.example.recyclerviewdiffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var adapter = RecyclerAdapter()

    val handler = Handler(Looper.getMainLooper())

    var runnable =object  : Runnable{
        override fun run() {
            adapter.setData(generatorRandom())
            handler.postDelayed(this,5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var container = findViewById<RecyclerView>(R.id.container)
        container.adapter = adapter
        adapter.setData(generator())
        adapter.setData(generatorRandom())
        handler.post(runnable)

    }


    private fun generator(): ArrayList<RandomValue> {
        var randomList = arrayListOf<RandomValue>()
        for (i in 0..10) {
            randomList.add(RandomValue(i))
        }
        return randomList
    }

    private fun generatorRandom(): ArrayList<RandomValue> {
        var randomList = arrayListOf<RandomValue>()
        for (i in 0..10) {
                randomList.add(RandomValue(Random.nextInt(100)))

        }
        return randomList
    }


}