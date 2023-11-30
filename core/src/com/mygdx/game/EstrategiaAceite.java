package com.mygdx.game;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EstrategiaAceite implements EstrategiaVelocidad {
    @Override
    public void aplicarEfecto(final Carretera carretera) {
    	
    	if(carretera.getVelocidad() != 25.0f) {
    		carretera.setVelocidad(25.0f);
    		
    		// Programar la restauración de la velocidad original después de 5 segundos
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(() -> {
                // Restaurar la velocidad original después de 5 segundos
                carretera.setVelocidad(15.0f);
            }, 2, TimeUnit.SECONDS);

            // Apagar el planificador después de 5 segundos
            scheduler.shutdown();
    	}
    }
}
