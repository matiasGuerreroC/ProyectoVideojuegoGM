package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Auto {
    private Rectangle auto;
    private Texture autoRectoImage;
    private Texture autoIzquierdaImage;
    private Texture autoDerechaImage;
    private Sound sonidoChoque;
    private int vidas = 3;
    private int puntos = 0;
    private int velocidadX = 400;
    private boolean chocado = false;
    private int tiempoChocadoMax = 50;
    private int tiempoChocado;
    private float inclinacion = 0;  // Inclinación del auto

    public Auto(Texture recto, Texture izquierda, Texture derecha, Sound ss) {
        autoRectoImage = recto;
        autoIzquierdaImage = izquierda;
        autoDerechaImage = derecha;
        sonidoChoque = ss;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public Rectangle getArea() {
        return auto;
    }

    public void sumarPuntos(int pp) {
        puntos += pp;
    }

    public void crear() {
        auto = new Rectangle();
        auto.x = 800 / 2 - 64 / 2;
        auto.y = 20;
        auto.width = 64;
        auto.height = 64;
    }

    public void chocar() {
        vidas--;
        chocado = true;
        tiempoChocado = tiempoChocadoMax;
        sonidoChoque.play();
    }

    public void dibujar(SpriteBatch batch) {
        if (!chocado)
        	// Dibuja el auto inclinado en la dirección apropiada
            if (inclinacion > 0) {
                batch.draw(autoIzquierdaImage, auto.x, auto.y);
            } else if (inclinacion < 0) {
                batch.draw(autoDerechaImage, auto.x, auto.y);
            } else {
                batch.draw(autoRectoImage, auto.x, auto.y);
            }
        else {
            batch.draw(autoRectoImage, auto.x, auto.y + MathUtils.random(-5, 5));
            tiempoChocado--;
            if (tiempoChocado <= 0) chocado = false;
        }
    }

    public void actualizarMovimiento() {
    	if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            auto.x -= velocidadX * Gdx.graphics.getDeltaTime();
            inclinacion = 10;  // Inclina hacia la izquierda
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            auto.x += velocidadX * Gdx.graphics.getDeltaTime();
            inclinacion = -10;  // Inclina hacia la derecha
        } else {
            inclinacion = 0;  // Sin inclinación
        }
        
        if (auto.x < 0) auto.x = 0;
        if (auto.x > 800 - 64) auto.x = 800 - 64;
    }

    public void destruir() {
    	autoRectoImage.dispose();
    }

    public boolean estaChocado() {
        return chocado;
    }
}
