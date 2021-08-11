package tmg.aboutthisapp.utils

import android.content.ClipboardManager
import android.content.Context

internal val Context.clipboardManager: ClipboardManager?
    get() = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager