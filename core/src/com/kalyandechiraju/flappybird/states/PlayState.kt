package com.kalyandechiraju.flappybird.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import com.kalyandechiraju.flappybird.HEIGHT
import com.kalyandechiraju.flappybird.WIDTH
import com.kalyandechiraju.flappybird.sprites.Bird
import com.kalyandechiraju.flappybird.sprites.Tube

class PlayState(manager: GameStateManager) : State(manager) {

  private val tubeSpacing = 125

  private val tubeCount = 4

  private val bird by lazy { Bird(50f, 300f) }

  private val background by lazy { Texture("bg.png") }

  private val tubes by lazy { Array<Tube>() }

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
    bird.update(deltaTime)

    // For camera to follow the bird and 80 to add some offset
    camera.position.x = bird.position.x + 80

    for (tube in tubes) {
      if ((camera.position.x - (camera.viewportWidth / 2)) > (tube.posTopTube.x + tube.topTube.width)) {
        tube.reposition(tube.posTopTube.x + ((Tube.TUBE_WIDTH + tubeSpacing) * tubeCount))
      }
    }

    camera.update()
  }

  override fun render(batch: SpriteBatch) {
    batch.projectionMatrix = camera.combined
    batch.begin()
    batch.draw(background, (camera.position.x - (camera.viewportWidth / 2)), 0f)
    batch.draw(bird.texture, bird.position.x, bird.position.y)
    for (tube in tubes) {
      batch.draw(tube.topTube, tube.posTopTube.x, tube.posTopTube.y)
      batch.draw(tube.bottomTube, tube.posBotTube.x, tube.posBotTube.y)
    }
    batch.end()
  }

  override fun dispose() {

  }
}