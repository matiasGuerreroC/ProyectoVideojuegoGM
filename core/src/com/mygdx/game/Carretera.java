package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// Esta clase representa la carretera del juego.
public class Carretera {
    private Texture carretera;  // Textura de la carretera
    private float y;            // Posición vertical de la textura del fondo
    private float velocidad;     // Velocidad de desplazamiento de la carretera
    private boolean reduccionActiva;
    private float duracionReduccion;
    private float tiempoRestanteReduccion;
    private boolean aumentoActivo;
    private float duracionAumento;
    private float tiempoRestanteAumento;
    

    public Carretera() {
        // Carga la textura de la carretera desde un archivo de imagen
        carretera = new Texture("carretera.png");

        // Inicializa la posición vertical de la carretera en la parte superior de la pantalla
        y = 0;
        
        // Inicializa la velocidad de desplazamiento de la carretera
        velocidad = 15.0f;  // Puedes ajustar la velocidad según tus preferencias
    }
    
    public float getVelocidad(){
    	return velocidad;
    }
    
    public void setVelocidad(float velocidad) {
    	this.velocidad = velocidad;
    	
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
    
    public void reducirVelocidadPorTiempo(float duracion) {
        if (!reduccionActiva) {
            this.duracionReduccion = duracion;
            this.tiempoRestanteReduccion = duracion;
            this.reduccionActiva = true;
        }
    }

    public void actualizarDisminuir(float delta) {
        if (reduccionActiva) {
            // Reducir la velocidad durante la duración establecida
            velocidad *= 0.5f; // Por ejemplo, reducir a la mitad

            tiempoRestanteReduccion -= delta;
            if (tiempoRestanteReduccion <= 0) {
                // Restaurar la velocidad original
                velocidad *= 2.0f; // Por ejemplo, aumentar al doble

                reduccionActiva = false;
                duracionReduccion = 0;
                tiempoRestanteReduccion = 0;
            }
        }
    }
    
    public void aumentarVelocidadPorTiempo(float duracion) {
        if (!aumentoActivo) {
            this.duracionAumento = duracion;
            this.tiempoRestanteAumento = duracion;
            this.aumentoActivo = true;
        }
    }
    

    public void actualizarAumento(float delta) {
        if (aumentoActivo) {
            // Aumentar la velocidad durante la duración establecida
            velocidad *= 2.0f; // Por ejemplo, aumentar al doble

            tiempoRestanteAumento -= delta;
            if (tiempoRestanteAumento <= 0) {
                // Restaurar la velocidad original
                velocidad *= 0.5f; // Por ejemplo, reducir a la mitad

                aumentoActivo = false;
                duracionAumento = 0;
                tiempoRestanteAumento = 0;
            }
        }
    }
}
