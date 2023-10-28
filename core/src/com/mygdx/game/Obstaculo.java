package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Obstaculo extends ObjetoCarretera implements Colisionable {
    public Obstaculo(Texture textura, Sound sonido, Music musica) {
        super(textura, sonido, musica);
    }

    @Override
    public void crear() {
        // Implementa lógica para crear obstáculos en la carretera
    	crearObstaculos();
    	
    	musica.setLooping(true);
    	musica.play();
    }
    
    private void crearObstaculos() {
    	Rectangle obstaculo = new Rectangle();
    	obstaculo.x = MathUtils.random(0, 813-64);
    	obstaculo.y = 600;
    	obstaculo.width = 64;
    	obstaculo.height = 75;
    	objetosPos.add(obstaculo);
    	
    	super.lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public boolean actualizarMovimiento(Auto auto) {
        // Implementa lógica para actualizar el movimiento de los obstáculos en la carretera
    	if(TimeUtils.nanoTime() - lastDropTime > 400000000) crearObstaculos();
    	
    	// Revisar si las monedas cayeron al suelo o chocaron con el auto
        for (int i=0; i < objetosPos.size; i++ ) {
            Rectangle drop = objetosPos.get(i);
            drop.y -= 400 * Gdx.graphics.getDeltaTime();
            
            // Revisar si la moneda cayo al suelo y se elimina
            if (drop.y + 64 < 0) {
            	super.objetosPos.removeIndex(i);
            }
            
            if(verificarColision(auto, drop)) {
            	auto.chocar();
            	
            	if(auto.getVidas() <= 0) {
            		return false;
            	}
            	
            	sonido.play();
            	super.objetosPos.removeIndex(i);
            }
        }
    	
    	return true;
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        // Implementa la lógica para dibujar obstáculos en la carretera
    	for(int i=0; i < super.objetosPos.size; i++) {
    		Rectangle obstaculo = super.objetosPos.get(i);
    		
    		batch.draw(textura, obstaculo.x, obstaculo.y);
    	}
    }
    
    @Override
    public void destruir() {
        sonido.dispose();
        textura.dispose();
     }

	@Override
	public boolean verificarColision(Auto auto, Rectangle objeto) {
		// TODO Auto-generated method stub
		
		if(objeto.overlaps(auto.getArea()))
		{
			return true;
		}
		return false;
		
	}
}