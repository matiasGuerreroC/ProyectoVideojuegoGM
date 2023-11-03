package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

// Esta interfaz representa un objeto que puede colisionar con un objeto Auto.
public interface Colisionable {
	
    // Declaración del método que verifica colisiones entre un objeto Auto y un objeto representado por un Rectangle.
	boolean verificarColision(Auto auto, Rectangle objeto);
}
