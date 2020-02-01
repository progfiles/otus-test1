package ru.otus.test1

import ru.otus.test1.entity.ImageData

fun ImageData.authorPrefix() = "Автор: $author"

fun action(word: String, operation: (String) -> String): String {
    val result = operation(word)
    return result
}