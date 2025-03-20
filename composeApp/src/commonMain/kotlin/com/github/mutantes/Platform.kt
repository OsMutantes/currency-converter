package com.github.mutantes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform