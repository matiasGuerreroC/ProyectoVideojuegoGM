package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Sprite;

// Esta clase representa al auto en el juego
public class Auto extends ObjetoCarretera {
    private Sprite sprite; // Un objeto Sprite para representar al auto en el juego
    private int vidas = 5; // Cantidad de vidas iniciales
    private int puntos = 0; // Puntuación del jugador
    private int velocidadX = 400; // Velocidad horizontal del auto
    private boolean chocado = false; // Bandera que indica si el auto está chocado
    private int tiempoChocadoMax = 50; // Duración máxima del estado chocado
    private int tiempoChocado; // Tiempo restante en el estado chocado
    private float inclinacion = 0; // Inclinación del auto (para simular movimiento)

    public Auto(Texture recto) {
        // Inicializamos el Sprite con la textura
    	super(); 
        sprite = new Sprite(recto);
        sprite.setSize(64, 102); // Establecemos el tamaño del sprite
        sprite.setOriginCenter(); // Establecemos el centro del sprite como origen
        
    }

    // Getters

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public Rectangle getArea() {
        return sprite.getBoundingRectangle();
    }

    // Método para sumar puntos al auto
    public void sumarPuntos(int pp) {
        puntos += pp;
    }

    // Método para crear el auto
    public void crear() {
        // Posicionamos el auto en la parte inferior y al centro de la pantalla
        sprite.setPosition(800 / 2 - 64 / 2, 20);
    }

    // Metodo utilizado si el auto choca con un obstáculo
    public void chocar() {
        vidas--; // Restamos una vida
        chocado = true; // Marcamos al auto como chocado
        tiempoChocado = tiempoChocadoMax; // Establecemos el tiempo de chocado
    }

    // Método para dibujar el auto
    public void dibujar(SpriteBatch batch) {
        if (!chocado) {
            // Si el auto no está chocado, aplicamos la inclinación al Sprite y lo dibujamos
            sprite.setRotation(inclinacion); // Aplicamos la inclinación
            sprite.draw(batch); // Dibujamos el Sprite
        } else {
            // Si el auto está chocado, lo dibujamos con una ligera variación vertical
            batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY() + MathUtils.random(-5, 5));
            tiempoChocado--;
            if (tiempoChocado <= 0) chocado = false; // Cuando se acaba el tiempo de chocado, lo marcamos como no chocado
        }
    }

    // Método para actualizar el movimiento del auto (desde el teclado)
    public boolean actualizarMovimiento(Auto auto, Carretera carretera) {
        // Obtenemos el tiempo transcurrido desde la última actualización
        float deltaTime = Gdx.graphics.getDeltaTime();

        // Verificamos si se presionó la tecla izquierda o derecha
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.setX(sprite.getX() - velocidadX * deltaTime); // Movimiento hacia la izquierda
            inclinacion = 10; // Inclinación hacia la izquierda
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprite.setX(sprite.getX() + velocidadX * deltaTime); // Movimiento hacia la derecha
            inclinacion = -10; // Inclinación hacia la derecha
        } else {
            inclinacion = 0; // Sin inclinación (recto)
        }

        // Limitamos el movimiento del auto para que no salga de los límites de la pantalla
        if (sprite.getX() < 0) sprite.setX(0);
        if (sprite.getX() > 800 - sprite.getWidth()) sprite.setX(800 - sprite.getWidth());
        return true; 
    }

    // Método para destruir el auto
    public void destruir() {
        sprite.getTexture().dispose(); // Liberamos los recursos de la textura del auto
    }

    // Método para verificar si el auto está chocado
    public boolean estaChocado() {
        return chocado; // Regresamos el valor de la bandera chocado
    }

	@Override
	protected void continuar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void pausar() {
		// TODO Auto-generated method stub
		
	}
}