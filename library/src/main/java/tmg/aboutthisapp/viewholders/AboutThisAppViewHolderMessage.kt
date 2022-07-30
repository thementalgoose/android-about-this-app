package tmg.aboutthisapp.viewholders

import android.content.ClipData
import android.graphics.Typeface
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import tmg.aboutthisapp.R
import tmg.aboutthisapp.AboutThisAppCallback
import tmg.aboutthisapp.AboutThisAppItem
import tmg.aboutthisapp.utils.clipboardManager
import kotlin.coroutines.coroutineContext

internal class AboutThisAppViewHolderMessage(
    private val callback: AboutThisAppCallback,
    itemView: View
): RecyclerView.ViewHolder(itemView), View.OnLongClickListener {

    private val title: TextView = itemView.findViewById(R.id.aboutThisAppMessage_title)
    private val container: ConstraintLayout = itemView.findViewById(R.id.aboutThisAppMessage_container)

    private lateinit var msg: String

    fun bind(item: AboutThisAppItem.Message) {

        if (item.longClickCopy) {
            container.setOnLongClickListener(this)
        } else {
            container.setOnLongClickListener(null)
        }

        this.msg = item.msg

        when (item.isPrimary) {
            true -> {
                title.setTypeface(null, Typeface.NORMAL)
                title.alpha = 1.0f
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            }
            false -> {
                title.setTypeface(null, Typeface.ITALIC)
                title.alpha = 0.4f
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f)
            }
        }

        title.gravity = if (item.isCentered) Gravity.CENTER else Gravity.START
        title.text = item.msg
    }

    override fun onLongClick(p0: View?): Boolean {
        val context = p0?.context ?: return false
        context.clipboardManager?.let { manager ->
            val clipData = ClipData.newPlainText("", msg)
            manager.setPrimaryClip(clipData)
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
                Toast.makeText(context, R.string.about_this_app_copy_to_clipboard, Toast.LENGTH_LONG)
                    .show()
            }
        }
        return true
    }
}