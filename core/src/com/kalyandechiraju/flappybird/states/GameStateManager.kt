package com.kalyandechiraju.flappybird.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*

class GameStateManager {
  private val states: Stack<State> = Stack()

  fun push(state: State) = states.push(state)

  fun pop(state: State) = states.pop()

  fun set(state: State) {
    states.pop()
    states.push(state)
  }

  fun update(deltaTime: Float) = states.peek().update(deltaTime)

  fun render(batch: SpriteBatch) = states.peek().render(batch)
}