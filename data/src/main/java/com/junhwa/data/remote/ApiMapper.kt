package com.junhwa.data.remote

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.junhwa.domain.entity.Banner
import com.junhwa.domain.entity.Goods
import com.junhwa.domain.entity.Home


object ApiMapper {

    @Throws(IllegalStateException::class)
    fun jsonElementToGoods(jsonElement: JsonElement): Goods? {
        check(jsonElement.isJsonObject) { "goods JsonData $jsonElement is Not JsonElement" }
        val jsonObject = jsonElement.asJsonObject

        val id = jsonObject.getInt("id")
        val name = jsonObject.getString("name")
        val image = jsonObject.getString("image")
        val actualPrice = jsonObject.getInt("actual_price")
        val price = jsonObject.getInt("price")
        val isNew = jsonObject.getBoolean("is_new")
        val sellCount = jsonObject.getInt("sell_count")

        return try {
            Goods(
                id = checkNotNull(id),
                name = checkNotNull(name),
                image = checkNotNull(image),
                actualPrice = checkNotNull(actualPrice),
                price = checkNotNull(price),
                isNew = checkNotNull(isNew),
                sellCount = checkNotNull(sellCount)
            )
        } catch (e: Exception) {
            null
        }
    }

    @Throws(IllegalStateException::class)
    fun jsonElementToBanner(jsonElement: JsonElement): Banner? {
        check(jsonElement.isJsonObject) { "banner JsonData $jsonElement is Not JsonElement" }
        val jsonObject = jsonElement.asJsonObject

        val id = jsonObject.getInt("id")
        val image = jsonObject.getString("image")

        return try {
            Banner(
                id = checkNotNull(id),
                image = checkNotNull(image)
            )
        } catch (e: Exception) {
            null
        }
    }

    @Throws(IllegalStateException::class)
    fun jsonElementToGoodsList(jsonElement: JsonElement): List<Goods> {
        check(jsonElement.isJsonObject) { "$jsonElement is Not JsonObject" }
        val jsonObject = jsonElement.asJsonObject

        return jsonObject.getArray("goods")?.mapNotNull { jsonElementToGoods(it) } ?: emptyList()
    }

    @Throws(IllegalStateException::class)
    fun jsonElementToHome(jsonElement: JsonElement): Home {
        check(jsonElement.isJsonObject) { "$jsonElement is Not JsonObject" }
        val jsonObject = jsonElement.asJsonObject

        val banners =
            jsonObject.getArray("banners")?.mapNotNull { jsonElementToBanner(it) } ?: emptyList()
        val goods =
            jsonObject.getArray("goods")?.mapNotNull { jsonElementToGoods(it) } ?: emptyList()

        return Home(
            banners = banners,
            goods = goods
        )
    }
}

fun JsonObject.getArray(key: String): JsonArray? {
    return try {
        checkNotNull(getAsJsonArray(key))
    } catch (e: Exception) {
        null
    }
}

fun JsonObject.getBoolean(key: String): Boolean? {
    return try {
        getAsJsonPrimitive(key).asBoolean
    } catch (e: Exception) {
        null
    }
}

fun JsonObject.getString(key: String): String? {
    return try {
        getAsJsonPrimitive(key).asString
    } catch (e: Exception) {
        null
    }
}

fun JsonObject.getInt(key: String): Int? {
    return try {
        getAsJsonPrimitive(key).asInt
    } catch (e: Exception) {
        null
    }
}