package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

// Clase abstracta que representa un objeto en la carretera
public abstract class ObjetoCarretera {
    protected Array<Rectangle> objetosPos;  // Almacena las posiciones de los objetos en la carretera
    protected Array<Integer> objetosTipo;   // Almacena los tipos de objetos en la carretera
    protected Texture textura;             // Textura utilizada para representar los objetos
    protected Sound sonido;                // Sonido relacionado con los objetos
    protected Music musica;                // Música relacionada con los objetos
    protected long lastDropTime;           // Tiempo del último objeto creado

    public ObjetoCarretera() {
    	
    	objetosPos = new Array<Rectangle>();  // Inicializa el arreglo de posiciones de objetos
		objetosTipo = new Array<Integer>();   // Inicializa el arreglo de tipos de objetos
    }
    //revisar
    public void templateMethod(Auto auto, SpriteBatch batch, Carretera carretera) {
        crear();
        while (actualizarMovimiento(auto, carretera)) {
            dibujar(batch);
        }
        destruir();
    }
    

    public void crear() {
        // Agrega lógica para crear objetos en la carretera
    }

    public abstract boolean actualizarMovimiento(Auto auto, Carretera carretera);

    public void dibujar(SpriteBatch batch) {
        // Dibuja los objetos en la carretera
        for (Rectangle objeto : objetosPos) {
            batch.draw(textura, objeto.x, objeto.y);  // Dibuja la textura del objeto en su posición
        }
    }

    public void destruir() {
        textura.dispose();  // Libera los recursos de la textura
    }
}