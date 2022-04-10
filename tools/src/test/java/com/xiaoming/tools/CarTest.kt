package com.xiaoming.tools

import com.xiaoming.tools.developtools.unittest.mockk.demo1.Car
import com.xiaoming.tools.developtools.unittest.mockk.demo1.Direction
import com.xiaoming.tools.developtools.unittest.mockk.demo1.Outcome
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase

class CarTest : TestCase() {

    fun testDrive() {
        // mock car对象
        val car = mockk<Car>()

        // 设置监听
        every { car.drive(Direction.NORTH) } returns Outcome.OK

        // 执行
        car.drive(Direction.NORTH)

        // 验证
        verify { car.drive(Direction.NORTH) }

        // 双重验证
        confirmVerified(car)
    }
}
