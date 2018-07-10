package xyz.laziness.dailycommit.ui.custom

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class RecyclerItemDivider(private val height: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.top = height
    }

}