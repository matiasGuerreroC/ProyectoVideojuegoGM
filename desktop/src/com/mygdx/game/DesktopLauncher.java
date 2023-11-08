package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;


// Esta clase se utiliza para iniciar la aplicación de escritorio del juego.
public class DesktopLauncher {
    public static void main(String[] arg) {
        // Configuración de la aplicación de escritorio.
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        // Configuración de la tasa de fotogramas y título de la ventana.
        config.setForegroundFPS(60);  // Establece la tasa de fotogramas en primer plano en 60 FPS.
        config.setTitle("Highway Havoc");  // Establece el título de la ventana en "Highway Havoc".
        config.setWindowIcon("auto.png");  // Establece un icono de ventana utilizando "auto.png".

        // Configuración del modo de ventana y resolución.
        config.setWindowedMode(1600, 960);  // Establece el modo de ventana con resolución 1600x960 píxeles.

        // Crea una instancia de la aplicación de escritorio y la inicia con la clase GameMenu y la configuración especificada.
        new Lwjgl3Application(new GameMenu(), config);
    }
}