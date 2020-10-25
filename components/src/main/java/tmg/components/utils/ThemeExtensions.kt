package tmg.components.utils

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

@ColorInt
internal fun Resources.Theme.getColor(@AttrRes attribute: Int): Int {
    val typedValue = TypedValue()
    resolveAttribute(attribute, typedValue, true)
    return typedValue.data
}