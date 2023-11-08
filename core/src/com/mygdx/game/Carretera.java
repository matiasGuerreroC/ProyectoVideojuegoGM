package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Esta clase representa la carretera del juego.
public class Carretera {
    private Texture carretera;  // Textura de la carretera
    private float y;            // Posición vertical de la textura del fondo
    private float velocidad;     // Velocidad de desplazamiento de la carretera

    public Carretera() {
        // Carga la textura de la carretera desde un archivo de imagen
        carretera = new Texture("carretera.png");

        // Inicializa la posición vertical de la carretera en la parte superior de la pantalla
        y = 0;
        
        // Inicializa la velocidad de desplazamiento de la carretera
        velocidad = 15.0f;  // Puedes ajustar la velocidad según tus preferencias
    }

    public void render(SpriteBatch batch) {
        // Dibuja la textura de la carretera en la posición actual
        batch.draw(carretera, 0, y);

        // Dibuja otra instancia de la textura de la carretera arriba de la primera para crear un efecto de bucle
        batch.draw(carretera, 0, y + carretera.getHeight());

        // Ajusta la posición vertical para simular el movimiento de la carretera
        y -= velocidad;  // Puedes ajustar la velocidad de desplazamiento cambiando el valor 15

        // Cuando la textura de la carretera se desplaza completamente fuera de la pantalla, se reinicia su posición vertical
        if (y <= -carretera.getHeight()) {
            y = 0;
        }
    }

    public void dispose() {
        // Libera los recursos al cerrar el juego
        carretera.dispose();
    }
}
