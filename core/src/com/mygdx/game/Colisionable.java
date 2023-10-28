package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public interface Colisionable {
	
	boolean verificarColision(Auto auto, Rectangle objeto);

}
