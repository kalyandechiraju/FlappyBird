package com.kalyandechiraju.flappybird

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
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

  // In LibGDX Music will be streamed from Disk and
  // Sound will be loaded into memory(RAM) for better resource management
  private var music: Music? = null

  override fun create() {
    batch = SpriteBatch()
    img = Texture("badlogic.jpg")
    manager = GameStateManager()

    music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"))
    music?.isLooping = true
    music?.volume = 0.05f
    music?.play()

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
  }

  override fun dispose() {
    batch?.dispose()
    img?.dispose()
    music?.dispose()
  }
}
