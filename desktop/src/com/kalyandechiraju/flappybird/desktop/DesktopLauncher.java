package com.kalyandechiraju.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kalyandechiraju.flappybird.FlappyBird;
import com.kalyandechiraju.flappybird.FlappyBirdKt;

public class DesktopLauncher {
  public static void main (String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = (int) FlappyBirdKt.WIDTH;
    config.height = (int) FlappyBirdKt.HEIGHT;
    config.title = FlappyBirdKt.TITLE;
    new LwjglApplication(new FlappyBird(), config);
  }
}