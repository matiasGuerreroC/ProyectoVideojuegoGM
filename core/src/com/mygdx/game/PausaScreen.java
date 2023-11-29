package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

// Esta clase representa la pantalla de pausa del juego.
public class PausaScreen implements Screen {

    private GameScreen juego;  // Referencia a la instancia de GameScreen
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    
    GameMenu game = GameMenu.getInstance();

    // Constructor que recibe la instancia de GameMenu y la instancia de GameScreen
    public PausaScreen(final GameMenu game, GameScreen juego) {
        this.game = game;
        this.juego = juego;  // Guarda la referencia a GameScreen
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        // Limpia la pantalla con un color de fondo
        ScreenUtils.clear(0, 0, 1.0f, 0.5f);

        // Actualiza la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibuja el mensaje de "Juego en Pausa" y un mensaje de "Toca en cualquier lado para continuar !!!"
        font.draw(batch, "Juego en Pausa ", 100, 150);
        font.draw(batch, "Toca en cualquier lado para continuar !!!", 100, 100);

        batch.end();

        if (Gdx.input.isTouched()) {
            // Cuando se toca la pantalla, se vuelve al juego reanudando GameScreen
            game.setScreen(juego);
            dispose();
        }
    }

    // Los siguientes métodos no están siendo utilizados en esta clase
    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}