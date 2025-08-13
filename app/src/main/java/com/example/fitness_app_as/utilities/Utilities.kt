package com.example.fitness_app_as.utilities

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView

object Utilities {
    fun embedYTLink(text: String, tv: TextView){
        val link = "https://www.youtube.com/results?search_query=" + text.split(" ").joinToString("+")
        val htmlText = "<a href=\"$link\">${text}</a>"
        tv.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
        tv.movementMethod = LinkMovementMethod.getInstance()
    }
}