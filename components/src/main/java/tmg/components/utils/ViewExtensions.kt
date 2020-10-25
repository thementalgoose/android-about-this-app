package tmg.components.utils

import android.view.View

/**
 * Showing a view and specifying the inverse behavior if not
 *
 * view.show(false, isGone = false) will set it to invisible
 * view.show(false, isGone = true) will set it to gone
 * view.show(true, isGone = true) will set it to visible
 */
internal fun View.show(value: Boolean, isGone: Boolean = true) {
    if (value) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = if (isGone) View.GONE else View.INVISIBLE
    }
}