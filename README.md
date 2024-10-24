# Proyecto FRC - Control y Programación del Robot

## Descripción

Este repositorio contiene el código fuente del robot desarrollado para la **First Robotics Competition (FRC)**, disenado para pruebas y un respaldo de nuestro codigo. El sistema está programado en Java utilizando la librería **WPILib**, que proporciona las herramientas necesarias para controlar el robot y gestionar sus sistemas de movimiento.

## Estructura del Código

El código se organiza de acuerdo con la estructura típica de un proyecto FRC basado en WPILib:

- **src/main/java/frc/robot**: Contiene las clases principales del robot.
  - **Robot.java**: Esta es la clase principal del proyecto, que contiene el ciclo de vida del robot (inicialización, periodos autónomos y de teleoperación, etc.).
 - **Commands**: Contiene las clases que describen los comandos específicos que el robot puede ejecutar, como moverse en una dirección, lanzar un objeto o interactuar con un sensor.
  - **OI.java (Operator Interface)**: Define los controles del operador y mapea las acciones de los mandos del robot a los comandos del subsistema.

## Funcionamiento

El robot está diseñado para moverse en cualquier dirección y realizar acciones según las instrucciones recibidas a través de los controladores. El sistema de control está basado en un modelo de **DriveTrain** para movimiento tradicional
### Fases de la Competencia:

1. **Autonomous Mode**: 
   - Durante esta fase, el robot ejecuta acciones preprogramadas sin intervención humana. Se usa la clase **AutonomousCommand** para manejar las rutinas de movimiento y acciones automáticas, como moverse hacia un objetivo o realizar una tarea específica.
   
2. **Teleoperated Mode**:
   - En esta fase, el robot es controlado manualmente por el operador utilizando un mando genérico. Los controles del operador están mapeados en la clase se ejecutan dependiendo de las entradas del controlador.

### Componentes Clave

- **DriveTrain Subsystem**: Controla las ruedas del robot, permitiendo su movimiento en todas direcciones (adelante, atrás, izquierda, derecha, rotación).

### Tecnologías Utilizadas

- **WPILib**: Librería oficial de FRC para la programación y control de robots.
- **Java**: Lenguaje de programación principal.
- **Command-based Programming**: Modelo de programación estructurada para robots FRC, utilizando comandos y subsistemas para dividir el control del robot en módulos.

## Requisitos

- **Java JDK 11** o superior.
- **WPILib**: Instalación de la librería de control de FRC.
- **Gradle**: Sistema de compilación para automatizar las dependencias del proyecto.



