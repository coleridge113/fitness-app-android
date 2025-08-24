package com.example.fitness_app_as.utilities

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView

object Utilities {

    /**
     * Embeds a clickable YouTube search link into the provided TextView using the given text.
     *
     * This function constructs a YouTube search URL based on the provided text, formats it as an HTML hyperlink,
     * and sets it as the content of the specified TextView. The TextView is also configured to handle link clicks.
     *
     * @param text The string to be used as the search query and hyperlink text.
     * @param tv The TextView in which the hyperlink will be embedded.
     */
    fun embedYTLink(tv: TextView, text: String){
        val link = "https://www.youtube.com/results?search_query=" + text.split(" ").joinToString("+")
        val htmlText = "<a href=\"$link\">${text}</a>"
        tv.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
        tv.movementMethod = LinkMovementMethod.getInstance()
    }
}