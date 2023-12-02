# ProyectoVideojuegoGM

Highway Havoc es un juego simple en 2D desarrollado con LibGDX, un marco de trabajo de código abierto para el desarrollo de juegos en Java. El juego simula una aventura en carretera donde el jugador controla un auto, recolecta monedas y evita obstáculos para ganar puntos.

## Características Principales

- **Personaje Jugable (Auto):** Controla un auto con habilidades de movimiento hacia la izquierda y derecha.
- **Objetos Buenos (Monedas):** Recoge monedas para ganar puntos y aumentar tu puntuación.
- **Obstáculos (Conos y Charcos de Aceite):** Evita obstáculos en la carretera para no perder vidas.
- **Diseños Personalizables:** Puedes cambiar los diseños del auto, las monedas y los obstáculos mediante diferentes configuraciones.

## Instrucciones de Juego

1. **Movimiento del Auto:** Utiliza las teclas de flecha izquierda y derecha para controlar el movimiento del auto.
2. **Recolección de Monedas:** Aumenta tu puntuación recogiendo monedas en la carretera.
3. **Evitar Obstáculos:** Evita los conos y charcos de aceite para no perder vidas.
4. **Personalización:** Experimenta con diferentes diseños para personalizar tu experiencia de juego.

## Instrucciones de Ejecución

1. **Requisitos Previos:**
   - Asegúrate de tener instalado Java en tu máquina.
   - Descarga e instala un entorno de desarrollo Java, como Eclipse o IntelliJ.

2. **Clonar el Repositorio:**
   ```bash
   git clone https://github.com/matiasGuerreroC/ProyectoVideojuegoGM
   ```

3. **Importar Proyecto:**
   - Abre tu entorno de desarrollo y selecciona "Importar Proyecto".
   - Selecciona el directorio del proyecto clonado.

4. **Ejecutar el Juego:**
   - Busca y ejecuta la clase `JuegoPrincipal` que contiene el método `main`.
   - ¡Disfruta del juego!

## Estructura del Proyecto

El proyecto sigue una estructura orientada a objetos y está dividido en varias clases clave:

- **Auto:** Representa el automóvil del jugador. Controla su movimiento y estado, como vidas y puntos.

- **ObjetoBueno:** Representa objetos que el jugador debe recolectar para obtener puntos.

- **Obstaculo:** Representa obstáculos que el jugador debe evitar. Pueden afectar la velocidad del juego.

- **ObjetoCarretera:** Clase abstracta que sirve como base para objetos en la carretera. Contiene la lógica común.

- **Carretera:** Representa la carretera por la que se desplaza el jugador. Gestiona el desplazamiento continuo.

- **EstrategiaVelocidad, EstrategiaAceite, EstrategiaCono:** Implementan estrategias que afectan la velocidad del juego.

- **GameDesignFactory:** Define una interfaz para crear instancias de objetos del juego según un diseño específico.

- **OriginalDesign, AlternativeDesign:** Implementan la interfaz `GameDesignFactory` para crear instancias según dos diseños diferentes.

## Principios de Programación Orientada a Objetos Aplicados

El código sigue varios principios de POO:

- **Encapsulamiento:** Los atributos de las clases están encapsulados, limitando el acceso directo desde fuera de la clase.

- **Herencia:** Se utiliza para compartir atributos y métodos comunes entre las clases. `ObjetoBueno` y `Obstaculo` heredan de `ObjetoCarretera`.

- **Polimorfismo:** Se implementa al tratar objetos de subclases como si fueran objetos de la superclase, simplificando la lógica del juego.

- **Interfaces:** La interfaz `Colisionable` define un método común para verificar colisiones, proporcionando coherencia en el manejo de colisiones.

## Personalización del Juego

El proyecto permite la personalización del juego a través de las clases `GameDesignFactory`. Puedes crear instancias con diferentes diseños (`OriginalDesign` o `AlternativeDesign`) para obtener una experiencia de juego única.
 
## Integrantes: 

- Samira Becerra
- Matias Guerrero
- Fabiana Piña
