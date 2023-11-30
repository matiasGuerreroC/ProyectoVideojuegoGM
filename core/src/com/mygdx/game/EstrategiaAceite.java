package com.mygdx.game;

public class EstrategiaAceite implements EstrategiaVelocidad {
    @Override
    public void aplicarEfecto(Carretera carretera) {
        carretera.aumentarVelocidadPorTiempo(5.0f); // Método de Auto para aumentar la velocidad
    }
}
