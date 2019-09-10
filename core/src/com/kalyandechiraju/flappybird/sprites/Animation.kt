package com.kalyandechiraju.flappybird.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class Animation(region: TextureRegion, private val frameCount: Int, cycleTime: Float) {
  private val frames by lazy { Array<TextureRegion>() }

  private val maxFrameTime by lazy { cycleTime / frameCount }

  private var frame: Int

  private var currentFrameTime = 0f

  init {
    val frameWidth = region.regionWidth / frameCount
    for (i in 0 until frameCount) {
      frames.add(TextureRegion(region, i * frameWidth, 0, frameWidth, region.regionHeight))
    }
    frame = 0
  }

  fun update(deltaTime: Float) {
    currentFrameTime += deltaTime
    if (currentFrameTime > maxFrameTime) {
      frame++
      currentFrameTime = 0f
    }
    if (frame >= frameCount) {
      frame = 0
    }
  }

  fun getFrame() = frames[frame]
}