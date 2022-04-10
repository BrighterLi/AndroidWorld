package com.xiaoming.tools.developtools.unittest.mockk.demo1

//Android 单元测试之 Mockk: https://blog.csdn.net/rikkatheworld/article/details/115823178
class Car {
    fun drive(direction: Direction): Outcome {
        return Outcome.NO
    }
}

enum class Direction {
    NORTH,
    SOUTH,
    WEST,
    EAST
}

enum class Outcome {
    OK,
    NO
}
