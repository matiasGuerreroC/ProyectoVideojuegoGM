package com.mygdx.game;

public class EstrategiaCono implements EstrategiaVelocidad {
    @Override
    public void aplicarEfecto(Carretera carretera) {
    	carretera.reducirVelocidadPorTiempo(5.0f);
    }
}
