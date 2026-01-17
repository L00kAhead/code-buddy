package com.github.l00kahead.codebuddy

import com.intellij.DynamicBundle
import org.jetbrains.annotations.PropertyKey

object CodeBuddyBundle : DynamicBundle("messages.CodeBuddyBundle") {

    fun message(
        @PropertyKey(resourceBundle = "messages.CodeBuddyBundle") key: String,
        vararg params: Any
    ): String = getMessage(key, *params)
}
