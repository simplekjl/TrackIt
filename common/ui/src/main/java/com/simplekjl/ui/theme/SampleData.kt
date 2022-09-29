package com.simplekjl.ui.theme

import com.github.mikephil.charting.data.Entry

/**
 * Helpful links
 * https://developersbreach.com/modal-bottom-sheet-jetpack-compose-android/#1-creating-a-modal-bottom-sheet
 * https://foso.github.io/Jetpack-Compose-Playground/material/modalbottomsheetlayout/
 * https://vanpra.github.io/compose-material-dialogs/DateTimePicker/
 *
 */

/**
 * Icons
 * <a href="https://iconscout.com/icons/calendar" target="_blank">Calendar Icon</a> by <a href="https://iconscout.com/contributors/jemismali">Jemis Mali</a> on <a href="https://iconscout.com">IconScout</a>
 * <a href="https://iconscout.com/icons/scale" target="_blank">Scale Icon</a> by <a href="https://iconscout.com/contributors/unicons" target="_blank">Unicons Font</a>
 * https://iconscout.com/icon-pack/user-interface-216
 * <a href="https://iconscout.com/icons/body-description" target="_blank">Body Description Icon</a> by <a href="https://iconscout.com/contributors/mark-aventura">IconMark</a> on <a href="https://iconscout.com">IconScout</a>
 * <a href="https://iconscout.com/icons/graph" target="_blank">Graph Icon</a> by <a href="https://iconscout.com/contributors/muhazdinata" target="_blank">Muhazdinata</a>
 * <a href="https://iconscout.com/icons/graph" target="_blank">Graph Icon</a> by <a href="https://iconscout.com/contributors/petras-nargela" target="_blank">Petras Nargėla</a>
 * https://visualpharm.com/free-icons/scale-595b40b85ba036ed117de199
 *
 */
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
