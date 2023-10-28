package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class ObjetoBueno extends ObjetoCarretera implements Colisionable {
    public ObjetoBueno(Texture textura, Sound sonido, Music musica) {
        super(textura, sonido, musica);
    }

    @Override
    public void crear() {
        // Implementa lógica para crear objetos buenos en la carretera
    	crearMonedas();
    	
    	musica.setLooping(true);
    	musica.play();
    }
    
    private void crearMonedas() {
    	Rectangle moneda = new Rectangle();
    	moneda.x = MathUtils.random(0, 813-64);
    	moneda.y = 600;
    	moneda.width = 64;
    	moneda.height = 64;
    	objetosPos.add(moneda);
    	
    	super.lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public boolean actualizarMovimiento(Auto auto) {
        // Implementa lógica para actualizar el movimiento de los objetos buenos en la carretera
    	if(TimeUtils.nanoTime() - lastDropTime > 1000000000) crearMonedas();
    	
    	// Revisar si las monedas cayeron al suelo o chocaron con el auto
        for (int i=0; i < objetosPos.size; i++ ) {
            Rectangle coindrop = objetosPos.get(i);
            coindrop.y -= 300 * Gdx.graphics.getDeltaTime();

            // Revisar si la moneda cayo al suelo y se elimina
            if (coindrop.y + 64 < 0) {
            	super.objetosPos.removeIndex(i);
            }
            
            if(verificarColision(auto, coindrop)) {
            	auto.sumarPuntos(1);
            	
            	sonido.play();
            	super.objetosPos.removeIndex(i);
            }
        }

        return true;
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        // Implementa la lógica para dibujar objetos buenos en la carretera
    	for(int i=0; i < super.objetosPos.size; i++) {
    		Rectangle moneda = super.objetosPos.get(i);
    		
    		batch.draw(textura, moneda.x, moneda.y);
    	}
    }
    
    @Override
    public void destruir() {
        sonido.dispose();
        musica.dispose();
        textura.dispose();
     }
    
    public void pausar() {
    	musica.stop();
    }
    
    public void continuar() {
    	musica.play();
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