package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Carretera {
	private Texture carretera;
	private float y;  // Posición vertical de la textura del fondo
    private float velocidad;  // Velocidad de desplazamiento de la carretera

    public Carretera() {
        // Carga la textura de la carretera
        carretera = new Texture("carretera.png");

        // Inicializa la posición vertical de la carretera
        y = 0;
        
        // Inicializa la velocidad de desplazamiento
        velocidad = 10.0f;  // Puedes ajustar la velocidad a tu preferencia
    }

    public void render(SpriteBatch batch) {
        // Dibuja la textura de la carretera en la posición actual
        batch.draw(carretera, 0, y);

        // Dibuja otra instancia de la textura de la carretera arriba de la primera para crear un efecto de bucle
        batch.draw(carretera, 0, y + carretera.getHeight());

        // Ajusta la posición vertical para simular el movimiento de la carretera
        y -= velocidad;  // Puedes ajustar la velocidad de desplazamiento cambiando el valor 1
        if (y <= -carretera.getHeight()) {
            y = 0;  // Reinicia la posición vertical cuando la textura ha salido completamente de la pantalla
        }
    }

    public void dispose() {
        // Libera los recursos al cerrar el juego
        carretera.dispose();
    }
}
