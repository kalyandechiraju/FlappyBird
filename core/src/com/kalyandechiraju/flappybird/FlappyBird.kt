package com.kalyandechiraju.flappybird

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.kalyandechiraju.flappybird.states.GameStateManager
import com.kalyandechiraju.flappybird.states.MenuState

const val WIDTH = 480f
const val HEIGHT = 800f

const val TITLE = "Flappy Bird"

class FlappyBird : ApplicationAdapter() {

  private var batch: SpriteBatch? = null
  private var img: Texture? = null
  private var manager: GameStateManager? = null

  override fun create() {
    batch = SpriteBatch()
    img = Texture("badlogic.jpg")
    manager = GameStateManager()
    Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
    manager?.let {
      it.push(MenuState(it))
    }
  }

  override fun render() {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    manager?.update(Gdx.graphics.deltaTime)
    batch?.let {
      manager?.render(it)
    }
    /*batch?.begin()
    batch?.draw(img, 0f, 0f)
    batch?.end()*/
  }

  override fun dispose() {
    batch?.dispose()
    img?.dispose()
  }
}
