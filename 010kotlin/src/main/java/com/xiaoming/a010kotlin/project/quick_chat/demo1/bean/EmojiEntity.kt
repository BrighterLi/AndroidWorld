package com.xiaoming.a010kotlin.project.quick_chat.demo1.bean


class EmojiEntity {
    var name: String? = null
    var unicode: Int = 0

    /*fun getUnicode(): String {
        return String(Character.toChars(unicode))
    }

    fun setUnicode(unicode: Int) {
        this.unicode = unicode
    }*/

    override fun toString(): String {
        return "EmojiEntity{" +
                "name='" + name + '\''.toString() +
                ", unicode=" + unicode +
                '}'.toString()
    }
}
