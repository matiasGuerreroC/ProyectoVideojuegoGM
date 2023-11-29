package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Esta clase representa el punto de entrada y el controlador principal del juego.
public class GameMenu extends Game {

    private static GameMenu instance; // La única instancia de GameMenu
	private SpriteBatch batch;  // Utilizado para dibujar gráficos en la pantalla.
    private BitmapFont font;    // Utilizado para mostrar texto en la pantalla.
    private int higherScore;    // Almacena la puntuación más alta del juego.

    // Este método se llama al inicio del juego.
    public void create() {
        batch = new SpriteBatch();  // Inicializa el SpriteBatch para dibujar gráficos.
        font = new BitmapFont();    // Inicializa el BitmapFont para mostrar texto.
        this.setScreen(new MainMenuScreen(this));  // Establece la pantalla principal del juego.
    }
    
    public static GameMenu getInstance() {
    	// Método estático para obtener la instancia única de GameMenu
    	if(instance == null) {
    		instance = new GameMenu();
    	}
    	return instance;
    }

    // Este método se llama en cada cuadro del juego.
    public void render() {
        super.render();  // Llama al renderizador de la pantalla actual (importante para el flujo del juego).
    }

    // Este método se llama al cerrar el juego y se utiliza para liberar recursos.
    public void dispose() {
        batch.dispose();  // Libera el SpriteBatch.
        font.dispose();   // Libera el BitmapFont.
    }

    // Métodos para acceder a los atributos privados.

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public int getHigherScore() {
        return higherScore;
    }

    public void setHigherScore(int higherScore) {
        this.higherScore = higherScore;
    }
}