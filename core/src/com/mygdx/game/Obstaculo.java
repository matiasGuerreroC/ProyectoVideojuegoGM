package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

// Esta clase representa un obstáculo en la carretera, que es subclase de ObjetoCarretera e implementa la interfaz Colisionable
public class Obstaculo extends ObjetoCarretera implements Colisionable {
	protected Texture textura;             // Textura utilizada para representar los objetos
    protected Sound sonido;                // Sonido relacionado con los objetos
    protected Music musica;
    
    public Obstaculo(Texture textura, Sound sonido, Music musica) {
        super();
        this.textura = textura; 
        this.sonido = sonido; 
        this.musica = musica; 
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
        obstaculo.x = MathUtils.random(0, 813 - 64);
        obstaculo.y = 600;
        obstaculo.width = 64;
        obstaculo.height = 75;
        objetosPos.add(obstaculo);
        
        super.lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public boolean actualizarMovimiento(Auto auto) {
        // Implementa lógica para actualizar el movimiento de los obstáculos en la carretera
        if (TimeUtils.nanoTime() - lastDropTime > 400000000)
            crearObstaculos();
        
        // Revisar si los obstáculos cayeron al suelo o chocaron con el auto
        for (int i = 0; i < objetosPos.size; i++) {
            Rectangle drop = objetosPos.get(i);
            drop.y -= 400 * Gdx.graphics.getDeltaTime();
            
            // Revisar si el obstáculo cayó al suelo y se elimina
            if (drop.y + 64 < 0) {
                super.objetosPos.removeIndex(i);
            }
            
            if (verificarColision(auto, drop)) {
                auto.chocar();
                
                if (auto.getVidas() <= 0) {
                    return false;  // El juego termina si el auto se queda sin vidas
                }
                
                sonido.play();  // Reproducir el sonido de colisión
                super.objetosPos.removeIndex(i);
            }
        }
        
        return true;  // El juego continúa
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        // Implementa la lógica para dibujar obstáculos en la carretera
        for (int i = 0; i < super.objetosPos.size; i++) {
            Rectangle obstaculo = super.objetosPos.get(i);
            
            batch.draw(textura, obstaculo.x, obstaculo.y);  // Dibujar la textura del obstáculo en su posición
        }
    }
    
    @Override
    public void destruir() {
        sonido.dispose();  // Libera los recursos del sonido
        textura.dispose(); // Libera los recursos de la textura
     }

    @Override
    public boolean verificarColision(Auto auto, Rectangle objeto) {
        // Método para verificar la colisión entre el auto y un objeto
        if (objeto.overlaps(auto.getArea())) {
            return true;  // Hay colisión
        }
        return false;  // No hay colisión
    }
}