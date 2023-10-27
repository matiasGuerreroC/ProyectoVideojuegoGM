package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;   
	private BitmapFont font;
	private Auto auto;
	private ObjetoBueno monedas;
	private Obstaculo obstaculos;
	private Carretera fondo;

	   
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
        fondo = new Carretera();
        
        auto = new Auto(new Texture(Gdx.files.internal("auto.png")));
         
        Texture moneda = new Texture(Gdx.files.internal("coin.png"));
        Texture cono = new Texture(Gdx.files.internal("cono.png"));
         
		Sound coinSound = Gdx.audio.newSound(Gdx.files.internal("collect_coin.mp3"));
		Sound crashSound = Gdx.audio.newSound(Gdx.files.internal("crash.mp3"));
		Music carMusic = Gdx.audio.newMusic(Gdx.files.internal("car_acceleration.mp3"));
		 
		monedas = new ObjetoBueno(moneda, coinSound, carMusic);
		obstaculos = new Obstaculo(cono, crashSound, carMusic);
		  
		// camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 813, 600);
		batch = new SpriteBatch();
		  
		// creacion del auto
		auto.crear();
		  
		// creacion de los objetos
		monedas.crear();
		obstaculos.crear();
	}

	@Override
	public void render(float delta) {
		//actualizar matrices de la cámara
		camera.update();
		
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		fondo.render(batch);
		
		//dibujar textos
		font.draw(batch, "Gotas totales: " + auto.getPuntos(), 5, 595);
		font.draw(batch, "Vidas : " + auto.getVidas(), 670, 595);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 595);
		
		if (!auto.estaChocado()) {
			// movimiento del auto desde teclado
			auto.actualizarMovimiento();       
			// caida de la lluvia 
	       if (!monedas.actualizarMovimiento(auto) || !obstaculos.actualizarMovimiento(auto)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<auto.getPuntos())
	    		  game.setHigherScore(auto.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		auto.dibujar(batch);
		monedas.dibujar(batch);
		obstaculos.dibujar(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		monedas.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		monedas.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		auto.destruir();
		monedas.destruir();
		obstaculos.destruir();
	}
}