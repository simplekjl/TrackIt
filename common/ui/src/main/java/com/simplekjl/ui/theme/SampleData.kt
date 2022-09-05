package com.simplekjl.ui.theme

import com.github.mikephil.charting.data.Entry

object SampleData {

    val entries: MutableList<Entry> = mutableListOf<Entry>().apply {
        val c1e1 = Entry(0f, 100f) // 0 == quarter 1
        add(c1e1)
        val c1e2 = Entry(1f, 120f) // 1 == quarter 2 ...
        add(c1e2)
        val c1e3 = Entry(1.2f, 112f) // 1 == quarter 3 ...
        add(c1e3)
        val c1e4 = Entry(1.2f, 120f) // 1 == quarter 4 ...
        add(c1e4)
    }
}
