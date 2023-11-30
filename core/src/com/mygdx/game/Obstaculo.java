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
	protected Texture texturaCono;             // Textura utilizada para representar los conos
	protected Texture texturaCharcoAceite;
    protected Sound sonido;                // Sonido relacionado con los objetos
    protected Music musica;
    protected int tipo;
    private EstrategiaVelocidad estrategiaCono;
    private EstrategiaVelocidad estrategiaAceite;
    
    public Obstaculo(Texture texturaCono, Texture texturaCharcoAceite, Sound sonido, Music musica, int tipo) {
        super();
        this.texturaCono = texturaCono; 
        this.texturaCharcoAceite = texturaCharcoAceite;
        this.sonido = sonido; 
        this.musica = musica; 
        this.tipo = tipo;
        this.estrategiaCono = new EstrategiaCono();
        this.estrategiaAceite = new EstrategiaAceite();
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

        // Asigna un tipo específico al obstáculo (0 para cono, 1 para charco de aceite)
        int tipoObstaculo = MathUtils.random(1);
        objetosTipo.add(tipoObstaculo);

        objetosPos.add(obstaculo);

        super.lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public boolean actualizarMovimiento(Auto auto, Carretera carretera) {
        // Implementa lógica para actualizar el movimiento de los obstáculos en la carretera
        if (TimeUtils.nanoTime() - lastDropTime > 400000000)
            crearObstaculos();
        
        // Revisar si los obstáculos cayeron al suelo o chocaron con el auto
        for (int i = 0; i < objetosPos.size; i++) {
            Rectangle drop = objetosPos.get(i);
            drop.y -= 400 * Gdx.graphics.getDeltaTime();
            
            if (verificarColision(auto, drop)) {
            	
            	if(objetosTipo.get(i) == 0) {
            		
            		estrategiaCono.aplicarEfecto(carretera);
            	}
            	else if(objetosTipo.get(i) == 1) {
            		
            		estrategiaAceite.aplicarEfecto(carretera);
            	}
            	
                auto.chocar();
                
                if (auto.getVidas() <= 0) {
                    return false;  // El juego termina si el auto se queda sin vidas
                }
                
                sonido.play();  // Reproducir el sonido de colisión
                super.objetosPos.removeIndex(i);
                super.objetosTipo.removeIndex(i);
            }
        }
        
        return true;  // El juego continúa
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        for (int i = 0; i < super.objetosPos.size; i++) {
            Rectangle obstaculo = super.objetosPos.get(i);
            int tipoObstaculo = objetosTipo.get(i);

            // Utiliza la textura correspondiente al tipo de obstáculo
            Texture texturaObstaculo = obtenerTexturaSegunTipo(tipoObstaculo);
            batch.draw(texturaObstaculo, obstaculo.x, obstaculo.y);
        }
    }
    
    // Método para obtener la textura según el tipo de obstáculo
    private Texture obtenerTexturaSegunTipo(int tipoObstaculo) {
        switch (tipoObstaculo) {
            case 0:
                return texturaCono; // Debes tener una textura para el cono
            case 1:
                return texturaCharcoAceite; // Debes tener una textura para el charco de aceite
            default:
                throw new IllegalArgumentException("Tipo de obstáculo no reconocido: " + tipoObstaculo);
        }
    }
    
    @Override
    public void destruir() {
        sonido.dispose();  // Libera los recursos del sonido
     }

    @Override
    public boolean verificarColision(Auto auto, Rectangle objeto) {
        // Método para verificar la colisión entre el auto y un objeto
        if (objeto.overlaps(auto.getArea())) {
            return true;  // Hay colisión
        }
        return false;  // No hay colisión
    }

	@Override
	public int getVidas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPuntos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean estaChocado() {
		// TODO Auto-generated method stub
		return false;
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