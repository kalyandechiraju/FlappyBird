package com.kalyandechiraju.flappybird.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.kalyandechiraju.flappybird.HEIGHT
import com.kalyandechiraju.flappybird.WIDTH
import com.kalyandechiraju.flappybird.sprites.Bird

class PlayState(manager: GameStateManager) : State(manager) {

  private val bird by lazy { Bird(50f, 300f) }

  private val background by lazy { Texture("bg.png") }

  init {
    camera.setToOrtho(false, WIDTH / 2, HEIGHT / 2)
  }

  override fun handleInput() {
    if (Gdx.input.justTouched()) {
      bird.jump()
    }
  }

  override fun update(deltaTime: Float) {
    handleInput()
    bird.update(deltaTime)
  }

  override fun render(batch: SpriteBatch) {
    batch.projectionMatrix = camera.combined
    batch.begin()
    batch.draw(background, (camera.position.x - (camera.viewportWidth / 2)), 0f)
    batch.draw(bird.texture, bird.position.x, bird.position.y)
    batch.end()
  }

  override fun dispose() {

  }
}