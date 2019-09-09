package com.kalyandechiraju.flappybird.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MenuState(manager: GameStateManager): State(manager) {
  private val background by lazy { Texture("bg.png") }

  private val playButton by lazy { Texture("playbtn.png") }

  override fun handleInput() {
    if(Gdx.input.justTouched()) {
      gameStateManager.set(PlayState(gameStateManager))
      dispose()
    }
  }

  override fun update(deltaTime: Float) {
    handleInput()
  }

  override fun render(batch: SpriteBatch) {
    batch.begin()
    batch.draw(background, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    batch.draw(playButton, ((Gdx.graphics.width.toFloat()/2) - (playButton.width/2)), Gdx.graphics.height.toFloat()/2)
    batch.end()
  }

  override fun dispose() {
    background.dispose()
    playButton.dispose()
  }
}