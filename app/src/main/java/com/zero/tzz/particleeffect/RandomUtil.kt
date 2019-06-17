package com.zero.tzz.particleeffect

import android.graphics.Color
import java.util.*

/**
 *
 * @author lucy
 * @date 2019-06-17 17:34
 * @description //TODO
 */
object RandomUtil {
    var random: Random = Random()
    fun randomColor(): Int {
//        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256))
        return Color.argb(randomInt(100, 255), 255, randomInt(180, 255), random.nextInt(256))
    }

    fun randomInt(start: Int = 0, end: Int): Int {
        return start + random.nextInt(end - start - 1)
    }
}