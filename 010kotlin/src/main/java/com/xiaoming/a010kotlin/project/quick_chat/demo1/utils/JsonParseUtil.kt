package com.xiaoming.a010kotlin.project.quick_chat.demo1.utils

import com.xiaoming.a010kotlin.project.quick_chat.demo1.bean.EmojiEntity
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

object JsonParseUtil {
    fun parseEmojiList(json: String): List<EmojiEntity> {
        val emojiEntityList = ArrayList<EmojiEntity>()
        try {
            val jsonObject = JSONObject(json)
            val jsonArray = jsonObject.optJSONArray("emoji_list")
            if (jsonArray != null) {
                for (i in 0 until jsonArray.length()) {
                    val jsonObject1 = jsonArray.optJSONObject(i)
                    if (jsonObject1 != null) {
                        val mEmojiEntity = EmojiEntity()
                        mEmojiEntity.name = jsonObject1.optString("name", "")
                        mEmojiEntity.unicode = jsonObject1.optInt("unicode", 0)
                        emojiEntityList.add(mEmojiEntity)
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return emojiEntityList
    }
}
