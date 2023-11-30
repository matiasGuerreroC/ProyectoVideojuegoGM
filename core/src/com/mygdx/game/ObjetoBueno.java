package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

// Esta clase representa un objeto bueno en la carretera, es subclase de ObjetoCarretera e implementa la interfaz Colisionable
public class ObjetoBueno extends ObjetoCarretera implements Colisionable {
	protected Texture textura;             // Textura utilizada para representar los objetos
    protected Sound sonido;                // Sonido relacionado con los objetos
    protected Music musica;

    // Constructor que recibe una textura, un sonido y una música
    public ObjetoBueno(Texture textura, Sound sonido, Music musica) {
        super();
        this.textura = textura; 
        this.sonido = sonido; 
        this.musica = musica; 
    }

    // Método que se llama al inicio del juego
    @Override
    public void crear() {
        // Implementa lógica para crear objetos buenos en la carretera
        crearMonedas();
        
        musica.setLooping(true);  // Configura la música para que se reproduzca en bucle
        musica.play();  // Reproduce la música
    }
    
    private void crearMonedas() {
        Rectangle moneda = new Rectangle();
        moneda.x = MathUtils.random(0, 813 - 64);  // Genera una posición x aleatoria
        moneda.y = 600;  // La moneda comienza desde la parte superior de la pantalla
        moneda.width = 64;  // Tamaño de la moneda
        moneda.height = 64;
        objetosPos.add(moneda);  // Agrega la moneda al conjunto de objetos en la carretera
        
        super.lastDropTime = TimeUtils.nanoTime();  // Registra el tiempo de creación de la última moneda
    }

    @Override
    public boolean actualizarMovimiento(Auto auto, Carretera carretera) {
        // Implementa lógica para actualizar el movimiento de los objetos buenos en la carretera
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) crearMonedas();  // Crea monedas a intervalos regulares
        
        // Revisar si las monedas cayeron al suelo o chocaron con el auto
        for (int i = 0; i < objetosPos.size; i++) {
            Rectangle coindrop = objetosPos.get(i);
            coindrop.y -= 300 * Gdx.graphics.getDeltaTime();  // Mueve las monedas hacia abajo a una velocidad constante
            
            // Revisar si la moneda cayó al suelo y se elimina
            if (coindrop.y + 64 < 0) {
                super.objetosPos.removeIndex(i);
            }
            
            // Revisar si hay colisión entre el auto y una moneda
            if (verificarColision(auto, coindrop)) {
                auto.sumarPuntos(1);  // Incrementa el puntaje del auto al recoger una moneda
                
                sonido.play();  // Reproduce el sonido de recolección de moneda
                super.objetosPos.removeIndex(i);  // Elimina la moneda
            }
        }

        return true;
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        // Implementa la lógica para dibujar objetos buenos en la carretera
        for (int i = 0; i < super.objetosPos.size; i++) {
            Rectangle moneda = super.objetosPos.get(i);
            
            batch.draw(textura, moneda.x, moneda.y);  // Dibuja la textura de la moneda en su posición
        }
    }
    
    @Override
    public void destruir() {
        sonido.dispose();  // Libera los recursos del sonido
        musica.dispose();  // Libera los recursos de la música
        textura.dispose();  // Libera los recursos de la textura
     }
    
    public void pausar() {
        musica.stop();  // Pausa la reproducción de la música
    }
    
    public void continuar() {
        musica.play();  // Continúa la reproducción de la música
    }

    @Override
    public boolean verificarColision(Auto auto, Rectangle objeto) {
        // Verifica si hay colisión entre el auto y un objeto rectangular (en este caso, una moneda)
        if (objeto.overlaps(auto.getArea())) {
            return true;  // Retorna verdadero si hay colisión
        }
        return false;  // Retorna falso si no hay colisión
    }
}