package com.ist.simpleloginscreen.data


/**
 * Represents an event that can be observed.
 *
 * @param T the type of the event content.
 * @property content the content of the event.
 */
open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    /**
     * Returns the content of the event if it has not been handled yet, or null if it has been handled.
     *
     * @return the content of the event or null.
     */
    fun getContentOrNull(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}