package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Esta clase representa la pantalla de Game Over del juego.
public class GameOverScreen implements Screen {
    
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture fondo;
    
    GameMenu game = GameMenu.getInstance();

    // Constructor que recibe la instancia de GameMenu
    public GameOverScreen(final GameMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        // Limpia la pantalla (no está siendo utilizado aquí)
        // ScreenUtils.clear(0, 0, 0.2f, 1);

        // Actualiza la cámara
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Dibuja el fondo
        batch.draw(fondo, 0, 0, 800, 480);

        // Configura el tamaño de la fuente y dibuja el texto "GAME OVER"
        font.getData().setScale(3, 3);
        font.draw(batch, "GAME OVER ", 50, 400);

        // Configura el tamaño de la fuente y dibuja el texto de reinicio
        font.getData().setScale(1, 1);
        font.draw(batch, "Toca en cualquier lado para reiniciar.", 50, 300);

        batch.end();

        if (Gdx.input.isTouched()) {
            // Cuando se toca la pantalla, se inicia una nueva instancia de GameScreen
            game.setScreen(new GameScreen(game, new OriginalDesign()));
            dispose();
        }
    }

    @Override
    public void show() {
        // Carga la textura de fondo cuando se muestra la pantalla
        fondo = new Texture(Gdx.files.internal("gameover.jpg"));
    }

	// Los siguientes métodos no están siendo utilizados en esta clase

    @Override
    public void resize(int width, int height) {
        // No es necesario implementar este método en este contexto
    }

    @Override
    public void pause() {
        // No es necesario implementar este método en este contexto
    }

    @Override
    public void resume() {
        // No es necesario implementar este método en este contexto
    }

    @Override
    public void hide() {
        // No es necesario implementar este método en este contexto
    }

    @Override
    public void dispose() {
        fondo.dispose();  // Libera los recursos de textura al cerrar la pantalla de Game Over
    }
}