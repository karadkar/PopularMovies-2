package io.github.karadkar.popularmovies.utils

import java.util.*
import kotlin.collections.ArrayList

/**
 * https://github.com/hitherejoe/GithubTrending/blob/5842790075d980ae762b5e341e6eff960b8b745f/Data/src/test/java/co/joebirch/data/test/factory/DataFactory.kt
 */

object DataFactory {
    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return Random().nextInt(99999)
    }

    fun randomLong(): Long {
        return randomInt().toLong()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun randomDouble() = Math.random()

    fun randomIntList(size: Int = 10): List<Int> {
        return ArrayList<Int>().apply {
            for (i in 0..size) {
                add(randomInt())
            }
        }
    }
}