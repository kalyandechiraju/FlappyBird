package com.kalyandechiraju.flappybird.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Bird(x: Float, y: Float) {

  private val gravity = -15f

  private val movement = 100

  val position by lazy { Vector3(x, y, 0f) }

  private val velocity by lazy { Vector3(0f, 0f, 0f) }

  private val texture by lazy { Texture("birdanimation.png") }

  // width should be divided by 3 as we switched to bird animated texture
  val bounds by lazy { Rectangle(x, y, texture.width.toFloat() / 3, texture.height.toFloat()) }

  private val birdAnimation by lazy {
    Animation(region = TextureRegion(texture), frameCount = 3, cycleTime = 0.5f)
  }

  private val flap by lazy {
    Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"))
  }

  var isColliding = false

  fun getBirdTextureRegion(): TextureRegion = birdAnimation.getFrame()

  fun update(deltaTime: Float) {
    if (isColliding.not()) {
      // update bird animation
      birdAnimation.update(deltaTime)
    }

    // Only update velocity if the bird is above ground
    if (position.y > 0) {
      // For every update, we add gravity to velocity in only y-axis direction
      velocity.add(0f, gravity, 0f)
    }

    // Scale the velocity as per deltaTime
    velocity.scl(deltaTime)

    if (isColliding.not()) {
      // Add to position based on y value of velocity and
      // move the bird by a factor of deltaTime and movement constant
      position.add(movement * deltaTime, velocity.y, 0f)
    }

    // To not let bird fall off of the screen
    if (position.y < 0) position.y = 0f

    // Scale the velocity at inverse deltaTime
    velocity.scl(1 / deltaTime)

    bounds.setPosition(position.x, position.y)
  }

  fun jump() {
    velocity.y = 250f

    // Play the flap sound
    flap.play(0.5f)
  }

  fun dispose() {
    texture.dispose()
    flap.dispose()
  }
}