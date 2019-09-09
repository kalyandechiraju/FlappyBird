package com.kalyandechiraju.flappybird.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3

abstract class State {
  protected val camera: OrthographicCamera
  protected val mouse: Vector3
  protected val gameStateManager: GameStateManager

  protected constructor(manager: GameStateManager) {
    gameStateManager = manager
    this.camera = OrthographicCamera()
    this.mouse = Vector3()
  }

  protected abstract fun handleInput()

  abstract fun update(deltaTime: Float)

  abstract fun render(batch: SpriteBatch)

  abstract fun dispose()
}
