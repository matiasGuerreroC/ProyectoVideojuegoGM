package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Esta clase representa la pantalla principal del juego.
public class GameScreen implements Screen {
	// Atributos privados de la clase
	final GameMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;   
	private BitmapFont font;
	private Auto auto;
	private ObjetoBueno monedas;
	private Obstaculo obstaculos;
	private Carretera fondo;

	// Constructor que recibe una instancia de GameMenu
	public GameScreen(final GameMenu game) {
		this.game = game;
        this.batch = game.getBatch();  // Obtiene el SpriteBatch de GameMenu
        this.font = game.getFont();    // Obtiene el BitmapFont de GameMenu

        fondo = new Carretera();  // Crea una instancia de Carretera

        auto = new Auto(new Texture(Gdx.files.internal("auto.png")));  // Crea una instancia de Auto con una textura

        Texture moneda = new Texture(Gdx.files.internal("coin.png"));
        Texture cono = new Texture(Gdx.files.internal("cono.png"));
         
		Sound coinSound = Gdx.audio.newSound(Gdx.files.internal("collect_coin.mp3"));
		Sound crashSound = Gdx.audio.newSound(Gdx.files.internal("crash.mp3"));
		Music carMusic = Gdx.audio.newMusic(Gdx.files.internal("car_acceleration.mp3"));
		 
		monedas = new ObjetoBueno(moneda, coinSound, carMusic);  // Crea una instancia de ObjetoBueno (Monedas)
		obstaculos = new Obstaculo(cono, crashSound, carMusic);  // Crea una instancia de Obstaculo (Conos)
		  
		// Configuración de la cámara ortográfica
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 813, 600);
		batch = new SpriteBatch();  // Crea un nuevo SpriteBatch
		  
		// Creación del auto y de los objetos
		auto.crear();
		monedas.crear();
		obstaculos.crear();
	}

	@Override
	public void render(float delta) {
		// Actualiza las matrices de la cámara
		camera.update();
		
		// Actualiza la proyección del SpriteBatch
		batch.setProjectionMatrix(camera.combined);
		batch.begin();  // Comienza a dibujar

		fondo.render(batch);  // Renderiza la carretera de fondo

		// Dibuja información en la pantalla
		font.draw(batch, "Monedas totales: " + auto.getPuntos(), 5, 595);
		font.draw(batch, "Vidas : " + auto.getVidas(), 670, 595);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 595);
		
		if (!auto.estaChocado()) {
			// Actualiza el movimiento del auto desde el teclado
			auto.actualizarMovimiento();       
			
			// Controla la caída de las monedas y los obstáculos
			if (!monedas.actualizarMovimiento(auto) || !obstaculos.actualizarMovimiento(auto)) {
				// Actualiza el puntaje más alto si es necesario
				if (game.getHigherScore() < auto.getPuntos())
					game.setHigherScore(auto.getPuntos());  
				
				// Cambia a la pantalla de fin de juego y destruye la actual
				game.setScreen(new GameOverScreen(game));
				dispose();
			}
		}
		
		auto.dibujar(batch);    // Dibuja el auto
		monedas.dibujar(batch);  // Dibuja las monedas
		obstaculos.dibujar(batch);  // Dibuja los obstáculos
		
		batch.end();  // Finaliza el dibujo
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		monedas.continuar();  // Continúa la animación de las monedas
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		monedas.pausar();  // Pausa la animación de las monedas
		game.setScreen(new PausaScreen(game, this));  // Cambia a la pantalla de pausa
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		auto.destruir();       // Libera los recursos del auto
		monedas.destruir();    // Libera los recursos de las monedas
		obstaculos.destruir();  // Libera los recursos de los obstáculos
	}
}