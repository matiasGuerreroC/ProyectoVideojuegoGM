package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Esta clase representa la pantalla principal del juego.
public class MainMenuScreen implements Screen {

    final GameMenu game;
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture fondo;

    // Constructor que recibe una instancia de GameMenu
    public MainMenuScreen(final GameMenu game) {
        this.game = game;
        this.batch = game.getBatch(); // Obtiene el SpriteBatch de GameMenu
        this.font = game.getFont();   // Obtiene el BitmapFont de GameMenu

        // Configura la cámara ortográfica
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    // Método que se llama en cada cuadro para renderizar la pantalla
    @Override
    public void render(float delta) {
        camera.update();   // Actualiza la cámara

        batch.setProjectionMatrix(camera.combined);   // Configura la matriz de proyección del SpriteBatch

        batch.begin();  // Comienza a dibujar en el SpriteBatch

        // Dibuja el fondo con una textura en la posición (0, 0) con dimensiones 800x480
        batch.draw(fondo, 0, 0, 800, 480);

        font.getData().setScale(3, 3);   // Establece el tamaño de la fuente
        font.draw(batch, "Bienvenido a Highway Havoc!!! ", 100, camera.viewportHeight/2+200);   // Dibuja el texto
        font.getData().setScale(1, 1);   // Restaura el tamaño de la fuente
        font.draw(batch, "Toca en cualquier lugar para comenzar!", 280, camera.viewportHeight/2-200);   // Dibuja otro texto

        batch.end();    // Finaliza el dibujo

        // Verifica si se ha tocado la pantalla
        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));   // Cambia a la pantalla de juego
            dispose();   // Libera los recursos de esta pantalla
        }
    }

    // Método llamado cuando esta pantalla se vuelve la pantalla actual
    @Override
    public void show() {
        fondo = new Texture(Gdx.files.internal("fondo.jpg"));   // Carga la textura de fondo
    }

    // Métodos no utilizados (pueden mantenerse vacíos o sin implementación)
    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    // Método llamado cuando esta pantalla se elimina o cambia
    @Override
    public void dispose() {
        fondo.dispose();   // Libera la textura de fondo
    }
}