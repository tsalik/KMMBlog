package me.tsalikis.blog

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}