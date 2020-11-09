package com.example.recyclerviewdiffutil

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var adapter = RecyclerAdapter()

    val handler = Handler(Looper.getMainLooper())

    var runnable = object : Runnable {
        override fun run() {
            adapter.setData(generatorRandom())
//            adapter.setData(generatorRandom())
            handler.postDelayed(this, 2000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container.adapter = adapter
        setUpItemTouchHelper()
        adapter.setData(generator())
        container.addItemDecoration(DividerItemDecoration(this, VERTICAL))
//        adapter.setData(generatorRandom())
//        handler.post(runnable)

    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }


    private fun generator(): ArrayList<RandomValue> {
        var randomList = arrayListOf<RandomValue>()
        for (i in 0..10) {
            randomList.add(RandomValue(UUID.randomUUID().toString(), i))
        }
        return randomList
    }

    private fun generatorRandom(): ArrayList<RandomValue> {
        var randomList = arrayListOf<RandomValue>()
        for (i in 0..10) {
            randomList.add(RandomValue(UUID.randomUUID().toString(), Random.nextInt(5)))

        }
        return randomList
    }

    private fun setUpItemTouchHelper() {
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.DOWN or ItemTouchHelper.UP,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: ViewHolder,
                    target: ViewHolder
                ): Boolean {
                    adapter.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                    return true
                }

                override fun onSwiped(viewHolder: ViewHolder, swipeDir: Int) {
                    val swipedPosition = viewHolder.adapterPosition
                    adapter.remove(swipedPosition)
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(container)
    }

}