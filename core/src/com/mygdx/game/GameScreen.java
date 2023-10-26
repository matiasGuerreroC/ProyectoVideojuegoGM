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
	private Lluvia lluvia;
	private Carretera fondo;

	   
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
        fondo = new Carretera();
        
		  // load the images for the droplet and the bucket, 64x64 pixels each
		  auto = new Auto(new Texture(Gdx.files.internal("auto.png")));
         
	      // load the drop sound effect and the rain background "music" 
         Texture gota = new Texture(Gdx.files.internal("coin.png"));
         Texture cono = new Texture(Gdx.files.internal("cono.png"));
         
         Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("collect_coin.mp3"));
        
	     Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("car_acceleration.mp3"));
         lluvia = new Lluvia(gota, cono, dropSound, rainMusic);
	      
	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 813, 600);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      auto.crear();
	      
	      // creacion de la lluvia
	      lluvia.crear();
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
			// movimiento del tarro desde teclado
			auto.actualizarMovimiento();        
			// caida de la lluvia 
	       if (!lluvia.actualizarMovimiento(auto)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<auto.getPuntos())
	    		  game.setHigherScore(auto.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		auto.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  lluvia.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		auto.destruir();
      lluvia.destruir();

	}

}
