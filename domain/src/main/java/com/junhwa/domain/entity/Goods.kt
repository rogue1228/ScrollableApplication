package com.junhwa.domain.entity

/**
 * "goods": [
{
"id": Int, // 상품 ID
"name": String, // 상품 이름
"image": String, // 상품 이미지 url
"actual_price": Int, // 상품 기본 가격
"price": Int, // 상품 실제 가격(기본가격 X 할인율 / 100 = 실제가격),
"is_new": Boolean, // 신상품인지 여부
"sell_count": Int, // 구매중 갯수
}
 */
data class Goods(
    val id: Int,
    val name: String,
    val image: String,
    val actualPrice: Int,
    val price: Int,
    val isNew: Boolean,
    val sellCount: Int,
    private var _like: Boolean = false
) {
    val discount: Float = (actualPrice - price) / actualPrice.toFloat() * 100
    val hasDiscount: Boolean = actualPrice > price

    fun toggleLike() {
        _like = !_like
    }
    fun hasLike(): Boolean = _like
}