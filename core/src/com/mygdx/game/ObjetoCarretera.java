package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class ObjetoCarretera {
    protected Array<Rectangle> objetosPos;
    protected Array<Integer> objetosTipo;
    protected Texture textura;
    protected Sound sonido;
    protected Music musica;
    protected long lastDropTime;

    public ObjetoCarretera(Texture textura, Sound sonido, Music musica) {
    	this.sonido = sonido;
    	this.musica = musica;
    	this.textura = textura;
    	
    	objetosPos = new Array<Rectangle>();
		objetosTipo = new Array<Integer>();
    }

    public void crear() {
        // Agrega lógica para crear objetos en la carretera
    }

    public abstract boolean actualizarMovimiento(Auto auto);

    public void dibujar(SpriteBatch batch) {
        // Dibuja los objetos en la carretera
        for (Rectangle objeto : objetosPos) {
            batch.draw(textura, objeto.x, objeto.y);
        }
    }

    public void destruir() {
        textura.dispose();
    }
}