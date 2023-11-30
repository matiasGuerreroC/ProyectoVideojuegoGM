package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AlternativeDesign implements GameDesignFactory {
	@Override
    public Auto crearAuto() {
        // Puedes personalizar la creación del auto aquí
        return new Auto(new Texture(Gdx.files.internal("auto2.png")));
    }

    @Override
    public ObjetoBueno crearObjetoBueno() {
        // Puedes personalizar la creación de objetos buenos aquí
        return new ObjetoBueno(new Texture(Gdx.files.internal("coin2.png")), Gdx.audio.newSound(Gdx.files.internal("collect_coin.mp3")), Gdx.audio.newMusic(Gdx.files.internal("car_acceleration.mp3")));
    }

    @Override
    public Obstaculo crearObstaculo() {
        // Puedes personalizar la creación de obstáculos aquí
        return new Obstaculo(new Texture(Gdx.files.internal("cono2.png")), new Texture(Gdx.files.internal("aceite2.png")), Gdx.audio.newSound(Gdx.files.internal("crash.mp3")), Gdx.audio.newMusic(Gdx.files.internal("car_acceleration.mp3")), 0);
    }
}
