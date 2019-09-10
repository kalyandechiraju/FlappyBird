package com.kalyandechiraju.flappybird.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import java.util.*

class Tube(x: Float) {

  companion object {
    const val TUBE_WIDTH = 52
  }
  private val fluctuation = 130
  private val tubeGap = 100
  private val lowestOpening = 120

  val topTube by lazy { Texture("toptube.png") }

  val bottomTube by lazy { Texture("bottomtube.png") }

  private val random by lazy { Random() }

  val posTopTube by lazy { Vector2(x, (random.nextInt(fluctuation) + tubeGap + lowestOpening).toFloat()) }

  val posBotTube by lazy { Vector2(x, (posTopTube.y - tubeGap - bottomTube.height)) }

  private val boundsTop by lazy { Rectangle(posTopTube.x, posTopTube.y, topTube.width.toFloat(), topTube.height.toFloat()) }

  private val boundsBottom by lazy { Rectangle(posBotTube.x, posBotTube.y, bottomTube.width.toFloat(), bottomTube.height.toFloat()) }

  fun reposition(x: Float) {
    posTopTube.set(x, (random.nextInt(fluctuation) + tubeGap + lowestOpening).toFloat())
    posBotTube.set(x, (posTopTube.y - tubeGap - bottomTube.height))

    boundsTop.setPosition(posTopTube.x, posTopTube.y)
    boundsBottom.setPosition(posBotTube.x, posBotTube.y)
  }

  fun didCollide(bird: Rectangle) = bird.overlaps(boundsTop) || bird.overlaps(boundsBottom)

  fun dispose() {
    topTube.dispose()
    bottomTube.dispose()
  }
}