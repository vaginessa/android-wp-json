package id.or.siber.utils

import android.os.Build
import android.text.Html
import android.text.Spanned

object HtmlUtils {
    /**
     * Returns a Spanned object from HTML.
     *
     * @param html The HTML string to be parsed.
     * @return The parsed Spanned object.
     */
    fun fromHtml(html: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(html).toString()
        }
    }

    fun limitText(text: String, maxLength: Int): String {
        if (text.length <= maxLength) return text
        val endIndex = maxLength.coerceAtMost(text.length) - 1
        return text.substring(0, endIndex).trim { it <= ' ' } + "…"
    }

    fun fromHtmlLimit(html: String, limit: Int): CharSequence? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val spanned = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            val plainText = spanned.toString()
            limitText(plainText, limit)
        } else {
            @Suppress("DEPRECATION")
            val spanned = Html.fromHtml(html)
            val plainText = spanned.toString()
            limitText(plainText, limit)
        }
    }
}