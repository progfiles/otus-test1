package ru.otus.test1.utils

import ru.otus.test1.entity.ImageData

fun ImageData.authorPrefix() = "Автор: $author"

fun action(word: String, operation: (String) -> String): String {
    val result = operation(word)
    return result
}