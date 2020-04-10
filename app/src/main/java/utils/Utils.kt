package utils

import android.view.View

object Utils {

    fun setVisibility(view: View?, isVisible: Boolean, isNeedGone: Boolean) {
        if (view == null) return

        if (isVisible) {
            setVisibility(view, View.VISIBLE)
        } else {
            val visibility = if (isNeedGone) View.GONE else View.INVISIBLE
            setVisibility(view, visibility)
        }
    }

    fun setVisibility(view: View, visibility: Int) {
        if (view.visibility != visibility) view.visibility = visibility
    }

}