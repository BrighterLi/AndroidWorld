package com.xiaoming.a010kotlin.kt_grammer

//Kotlin和Java隔离
fun main() {
    showTest()
    //`   `('M')
    `4325436465375`("Derry")
}

//只能Kotlin调用，Java无法调用
fun `showTest`() {
    println("showText")
}

//只能Kotlin调用，Java无法调用
/*fun `   `(sex: Char) {
    println("sex:$sex")
}*/

//只能Kotlin调用，Java无法调用
fun `4325436465375`(name: String) {

}