package com.kalyandechiraju.flappybird.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.kalyandechiraju.flappybird.HEIGHT
import com.kalyandechiraju.flappybird.WIDTH
import com.kalyandechiraju.flappybird.sprites.Bird
import com.kalyandechiraju.flappybird.sprites.Tube

class PlayState(manager: GameStateManager) : State(manager) {

  private val tubeSpacing = 125
  private val tubeCount = 4

  private val groundOffset = -50f

  private val bird by lazy { Bird(50f, 300f) }

  private val background by lazy { Texture("bg.png") }

  private val tubes by lazy { Array<Tube>() }

  private val ground by lazy { Texture("ground.png") }

  // Starts at left side of the screen
  private val groundOne by lazy { Vector2(camera.position.x - camera.viewportWidth / 2, groundOffset) }

  // Starts right after the first ground ends
  private val groundTwo by lazy { Vector2((camera.position.x - camera.viewportWidth / 2) + ground.width, groundOffset) }

  init {
    camera.setToOrtho(false, WIDTH / 2, HEIGHT / 2)

    for (i in 1..tubeCount) {
      tubes.add(Tube(i * (tubeSpacing + Tube.TUBE_WIDTH).toFloat()))
    }
  }

  override fun handleInput() {
    if (Gdx.input.justTouched()) {
      bird.jump()
    }
  }

  override fun update(deltaTime: Float) {
    handleInput()
    updateGround()
    bird.update(deltaTime)

    // For camera to follow the bird and 80 to add some offset
    camera.position.x = bird.position.x + 80

    // Don't use forEach here -> Array (libgdx) doesn't support nested iterations
    for (i in 0 until tubes.size) {
      val tube = tubes[i]
      if ((camera.position.x - (camera.viewportWidth / 2)) > (tube.posTopTube.x + tube.topTube.width)) {
        tube.reposition(tube.posTopTube.x + ((Tube.TUBE_WIDTH + tubeSpacing) * tubeCount))
      }
      // If bird hits with any of the tubes, reset the play state
      if (tube.didCollide(bird.bounds)) {
        gameStateManager.set(PlayState(gameStateManager))
      }
    }

    // If bird collides with the ground, reset the play state
    if (bird.position.y <= ground.height + groundOffset) {
      gameStateManager.set(PlayState(gameStateManager))
    }

    // actually moves the camera with the bird
    camera.update()
  }

  override fun render(batch: SpriteBatch) {
    batch.projectionMatrix = camera.combined
    batch.begin()
    batch.draw(background, (camera.position.x - (camera.viewportWidth / 2)), 0f)
    batch.draw(bird.getBirdTextureRegion(), bird.position.x, bird.position.y)
    tubes.forEach { tube ->
      batch.draw(tube.topTube, tube.posTopTube.x, tube.posTopTube.y)
      batch.draw(tube.bottomTube, tube.posBotTube.x, tube.posBotTube.y)
    }
    batch.draw(ground, groundOne.x, groundOne.y)
    batch.draw(ground, groundTwo.x, groundTwo.y)
    batch.end()
  }

  private fun updateGround() {
    // If camera crosses half point of the ground, move the ground to the right
    if (camera.position.x - (camera.viewportWidth/2) > groundOne.x + ground.width) {
      groundOne.add(ground.width.toFloat() * 2, 0f)
    }
    if (camera.position.x - (camera.viewportWidth/2) > groundTwo.x + ground.width) {
      groundTwo.add(ground.width.toFloat() * 2, 0f)
    }
  }

  override fun dispose() {
    background.dispose()
    bird.dispose()
    ground.dispose()
    tubes.forEach { it.dispose() }
  }
}