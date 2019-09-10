package com.kalyandechiraju.flappybird.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3

class Bird(x: Float, y: Float) {

  private val gravity = -15f

  private val movement = 100

  val position by lazy { Vector3(x, y, 0f) }

  private val velocity by lazy { Vector3(0f, 0f, 0f) }

  val texture by lazy { Texture("bird.png") }

  fun update(deltaTime: Float) {
    // Only update velocity if the bird is above ground
    if (position.y > 0) {
      // For every update, we add gravity to velocity in only y-axis direction
      velocity.add(0f, gravity, 0f)
    }

    // Scale the velocity as per deltaTime
    velocity.scl(deltaTime)

    // Add to position based on y value of velocity and
    // move the bird by a factor of deltaTime and movement constant
    position.add(movement * deltaTime, velocity.y, 0f)

    // Scale the velocity at inverse deltaTime
    velocity.scl(1 / deltaTime)

    // To not let bird fall off of the screen
    if (position.y < 0) position.y = 0f
  }

  fun jump() {
    velocity.y = 250f
  }

}