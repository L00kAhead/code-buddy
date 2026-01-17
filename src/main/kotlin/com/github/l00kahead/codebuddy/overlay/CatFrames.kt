package com.github.l00kahead.codebuddy.overlay

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

object CatFrames {

    val walk: List<BufferedImage> by lazy {
        (1..12).map { i ->
            val path = "/pets/cat_walk_512x512_frame0%02d.png".format(i)
            ImageIO.read(
                CatFrames::class.java.getResource(path)
                    ?: error("Missing frame $path")
            )
        }
    }
}
