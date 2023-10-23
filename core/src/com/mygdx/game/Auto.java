package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Auto {
    private Sprite sprite;  // Agregamos un Sprite
    private int vidas = 3;
    private int puntos = 0;
    private int velocidadX = 400;
    private boolean chocado = false;
    private int tiempoChocadoMax = 50;
    private int tiempoChocado;
    private float inclinacion = 0;

    public Auto(Texture recto) {
        // Inicializamos el Sprite con la textura
        sprite = new Sprite(recto);
        sprite.setSize(64, 102);  // Establecemos el tamaño
        sprite.setOriginCenter();  // Establecemos el centro como origen
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public Rectangle getArea() {
        return sprite.getBoundingRectangle();
    }

    public void sumarPuntos(int pp) {
        puntos += pp;
    }

    public void crear() {
        sprite.setPosition(800 / 2 - 64 / 2, 20);
    }

    public void chocar() {
        vidas--;
        chocado = true;
        tiempoChocado = tiempoChocadoMax;
    }

    public void dibujar(SpriteBatch batch) {
        if (!chocado) {
            // Aplicamos la inclinación al Sprite
            sprite.setRotation(inclinacion);
            sprite.draw(batch);
        } else {
            batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY() + MathUtils.random(-5, 5));
            tiempoChocado--;
            if (tiempoChocado <= 0) chocado = false;
        }
    }

    public void actualizarMovimiento() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.setX(sprite.getX() - velocidadX * deltaTime);
            inclinacion = 10;  // Inclina hacia la izquierda
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprite.setX(sprite.getX() + velocidadX * deltaTime);
            inclinacion = -10;  // Inclina hacia la derecha
        } else {
            inclinacion = 0;  // Sin inclinación
        }

        if (sprite.getX() < 0) sprite.setX(0);
        if (sprite.getX() > 800 - sprite.getWidth()) sprite.setX(800 - sprite.getWidth());
    }

    public void destruir() {
        sprite.getTexture().dispose();
    }

    public boolean estaChocado() {
        return chocado;
    }
}