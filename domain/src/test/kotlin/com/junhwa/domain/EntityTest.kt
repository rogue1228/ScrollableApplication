package com.junhwa.domain

import com.junhwa.domain.entity.Goods
import org.junit.Test
import kotlin.test.assertEquals

class EntityTest {

    private val goods: Goods = Goods(
        id = 1001,
        name = "Tom",
        image = "",
        actualPrice = 10000,
        price = 8000,
        isNew = true,
        sellCount = 100
    )

    @Test
    fun discountTest() {
        println("할인율은 ${goods.discount}%")

        assertEquals(20f, goods.discount)
    }
}