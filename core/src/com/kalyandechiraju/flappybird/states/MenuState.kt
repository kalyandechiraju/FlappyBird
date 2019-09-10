package com.kalyandechiraju.flappybird.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.kalyandechiraju.flappybird.HEIGHT
import com.kalyandechiraju.flappybird.WIDTH

class MenuState(manager: GameStateManager) : State(manager) {
  private val background by lazy { Texture("bg.png") }

  private val playButton by lazy { Texture("playbtn.png") }

  init {
    // To help resolution for phone
    camera.setToOrtho(false, WIDTH / 2, HEIGHT / 2)
  }

  override fun handleInput() {
    if (Gdx.input.justTouched()) {
      gameStateManager.set(PlayState(gameStateManager))
    }
  }

  override fun update(deltaTime: Float) {
    handleInput()
  }

  override fun render(batch: SpriteBatch) {
    batch.projectionMatrix = camera.combined
    batch.begin()
    batch.draw(background, 0f, 0f)
    batch.draw(playButton, camera.position.x - playButton.width / 2, camera.position.y)
    batch.end()
  }

  override fun dispose() {
    background.dispose()
    playButton.dispose()
  }
}