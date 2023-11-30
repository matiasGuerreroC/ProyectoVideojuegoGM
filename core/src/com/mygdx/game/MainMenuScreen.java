package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

// Esta clase representa la pantalla principal del juego.
public class MainMenuScreen implements Screen {

    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Texture fondo;
    private GameDesignFactory selectedFactory;
    private boolean flag = false;
    
    private boolean showLine = true; // Variable para alternar la visibilidad de la línea
    private float blinkTimer = 0f; // Temporizador para el parpadeo
    
    GameMenu game = GameMenu.getInstance();

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
        font.draw(batch, "Diseño del juego:", 550, camera.viewportHeight / 2 - 125);
        font.draw(batch, "1. Diseño Original", 550, camera.viewportHeight / 2 - 150);
        font.draw(batch, "2. Diseño Alternativo", 550, camera.viewportHeight / 2 - 175);
        
        // Actualiza el temporizador
        blinkTimer += delta;

        // Cambia la visibilidad de la línea cada 0.5 segundos (o el valor que desees)
        if (blinkTimer >= 0.4f) {
            showLine = !showLine;
            blinkTimer = 0f; // Reinicia el temporizador
        }
        
        // Dibuja la línea solo si showLine es true
        if (showLine) {
            font.getData().setScale(1, 2);
            font.draw(batch, "!!Presione 1 o 2 para Iniciar Juego!!", 550, camera.viewportHeight/2-200);
        }
        
        batch.end();    // Finaliza el dibujo
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            // Selecciona la fábrica por defecto
            selectedFactory = new OriginalDesign();
            flag = true;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            // Selecciona la fábrica alternativa
            selectedFactory = new AlternativeDesign();
            flag = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) && flag) {
            // Comienza el juego con la fábrica seleccionada
            game.setScreen(new GameScreen(game, selectedFactory));
            dispose();
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