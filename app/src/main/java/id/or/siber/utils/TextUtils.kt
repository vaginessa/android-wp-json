package id.or.siber.utils

object TextUtils {

    /**
     * Truncates text to the specified length and appends an optional end string.
     *
     * @param text The text to be truncated.
     * @param maxLength The maximum length of the text.
     * @param endString The string to append at the end of the truncated text (e.g., "...").
     * @return The truncated text with the end string appended.
     */
    fun limitText(text: String, maxLength: Int, endString: String = "..."): String {
        // Check if the text needs to be truncated
        if (text.length <= maxLength) {
            return text
        }

        // Determine the end index for truncation
        val endIndex = maxLength - endString.length

        // Ensure the end index is not negative
        if (endIndex < 0) {
            throw IllegalArgumentException("maxLength must be longer than endString length.")
        }

        // Truncate the text and append the end string
        return text.substring(0, endIndex) + endString
    }
}