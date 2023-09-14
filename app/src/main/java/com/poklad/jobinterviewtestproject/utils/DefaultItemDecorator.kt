package com.poklad.jobinterviewtestproject.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//TODO надо ?
class GridItemDecorator(
    private val space: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val column = position % 2

        if (column == 0) {
            outRect.right = space / 2
            outRect.left = space
        } else {
            outRect.left = space / 2
            outRect.right = space
        }

        if (position >= 2) {
            outRect.top = space
        }
    }
}