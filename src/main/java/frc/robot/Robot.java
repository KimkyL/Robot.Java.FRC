/*
  2023 Racconstic Code
  Reescrito y mapuchado por Angel Dabit Gael villatoro Y Cesar Augusto
  Autor original de el codigo: carson graf

*/

/*
  This is catastrophically poorly written code for the sake of being easy to follow
  Esto esta castatroficamente pobre  
  If you know what the word "refactor" means, you should refactor this code
*/

// 1.325 aprox gira 90 grados 

package frc.robot;




import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  
  //Definitions for the hardware. Change this if you change what stuff you have plugged in
  // definiciones del hardware, como moteres 
  CANSparkMax driveLeftA = new CANSparkMax(1, MotorType.kBrushed);
  CANSparkMax driveLeftB = new CANSparkMax(2, MotorType.kBrushed);
  CANSparkMax driveRightA = new CANSparkMax(3, MotorType.kBrushed);
  CANSparkMax driveRightB = new CANSparkMax(4, MotorType.kBrushed);
  CANSparkMax elevador = new CANSparkMax(5, MotorType.kBrushless);
  VictorSPX intake = new VictorSPX(6);

  Joystick driverController = new Joystick(0);
  Joystick armController = new Joystick(0);


  //Constants for controlling the arm. consider tuning these for your particular robot
  final double armHoldUp = 0.08;
  final double armHoldDown = 0.15;
  final double armTravel = 0.5;

  final double armTimeUp = 0.5;
  final double armTimeDown = 0.35;

  //Varibles needed for the code
  boolean armUp = true; //Arm initialized to up because that's how it would start a match
  boolean burstMode = false;
  double lastBurstTime = 0;

  double autoStart = 0;
  double time = 0;
  boolean goForAuto = false; // it will change as autonomous is activated

  double ticks = 0; // used for the automatic ( not anymored)
  double autoTimeElapsed = 0;

  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //Configure motors to turn correct direction. You may have to invert some of your motors
    //Esta parte sirve para poder invertir los motores del robot *esto se puede probar a la hora de hacer deploy*
    driveLeftA.setInverted(true);
    driveLeftA.burnFlash();
    driveLeftB.setInverted(true);
    driveLeftB.burnFlash();
    driveRightA.setInverted(false);
    driveRightA.burnFlash();
    driveRightB.setInverted(false);
    driveRightB.burnFlash();
    
    elevador.setInverted(false);
    elevador.setIdleMode(IdleMode.kBrake);
    elevador.burnFlash();

    //add a thing on the dashboard to turn off auto if needed
    SmartDashboard.getBoolean ("Go For Auto", false);
    goForAuto = SmartDashboard.getBoolean("Go For Auto", false);


    
  }
    
    
  @Override
  public void autonomousInit() {
    //get a time for auton start to do events based on time later
      goForAuto=true;
      autoStart = Timer.getFPGATimestamp();
     
    }
  

/** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    autoTimeElapsed = Timer.getFPGATimestamp() - autoStart;
    if (goForAuto == true ){ 
      if ((autoTimeElapsed < 5.3)) //Da una vueltita suculenta
      { // Rotation to the right side about 360 degrees | rotacion hacia la derecha por 360 grados
        driveLeftA.set(0.3);
        driveLeftB.set(0.3);

        driveRightA.set(0.3);
        driveRightB.set(0.3);
        

      } else if ((autoTimeElapsed < 6.3) && (autoTimeElapsed > 5.3)) //Se detiene
      { // stops for about a second  || se detiene por un segundo
        driveLeftA.set(0);
        driveLeftB.set(0); 
        driveRightA.set(0);
        driveRightB.set(0);

      } else if ((autoTimeElapsed < 11.6) && (autoTimeElapsed >6.3)) //Avanza bien sexy
      { //  Rotation to the left and it SHOULD if it is correct return to its original position
        // rotacion pa la izquiera Y DEBERIA si esta bien regresar a su poscicion original
        driveLeftA.set(0.3);
        driveLeftB.set(0.3);
        driveRightA.set(-0.3);
        driveRightB.set(-0.3);

      } else if ( (autoTimeElapsed < 12.6) && (autoTimeElapsed >11.6))//Se detiene
      {
        driveLeftA.set(0);
        driveLeftB.set(0); 
        driveRightA.set(0);
        driveRightB.set(0);
      } else if ((autoTimeElapsed < 17.9) && (autoTimeElapsed >12.6))//Da otra vueltita bien sexy
      {
        driveLeftA.set(-0.3);
        driveLeftB.set(-0.3);
        driveRightA.set(0.3);
        driveRightB.set(0.3);

      }else if ((autoTimeElapsed <18.9) && (autoTimeElapsed > 17.9)){ //se detiene
        driveLeftA.set(0);
        driveLeftB.set(0); 
        driveRightA.set(0);
        driveRightB.set(0);
      }
      else if ((autoTimeElapsed < 20.9) && (autoTimeElapsed > 18.9)) //avanza bien tryhard
      {
        driveLeftA.set(0.1);
        driveLeftB.set(0.1);
        driveRightA.set(-0.1);
        driveRightB.set(-0.1);
      }else{  // ya se apago upsss //se detiene
        driveLeftA.set(0.0);
        driveLeftB.set(0.0);
        driveRightA.set(0.0);
        driveRightB.set(0.0);
      }
        }

    }

         
    
     
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //Set up arcade steer
     //se configura en modo arcade steer
    double forward = -driverController.getRawAxis(1);// y
    double turn = -driverController.getRawAxis(2);//x
    
    double driveLeftPower = forward - turn;
    double driveRightPower = forward + turn;

    driveLeftA.set(driveLeftPower);
    driveLeftB.set(driveLeftPower);
    driveRightA.set(driveRightPower);
    driveRightB.set(driveRightPower);

    //Intake controls
    //Controles de entrada estos son los botones para el elevador que suba y baje idependientemente el uso 
    if(armController.getRawButton(2)){
      intake.set(VictorSPXControlMode.PercentOutput, 1);;
    }
    else if(armController.getRawButton(1)){
      intake.set(VictorSPXControlMode.PercentOutput, -1);
    }
    else{
      intake.set(VictorSPXControlMode.PercentOutput, 0);
    }

    //Arm Controls
    //Control de la garra y elevador
    if(armUp){
      if(Timer.getFPGATimestamp() - lastBurstTime < armTimeUp){
        elevador.set(armTravel);
      }
      else{
        elevador.set(armHoldUp);
      }
    }
    else{
      if(Timer.getFPGATimestamp() - lastBurstTime < armTimeDown){
        elevador.set(-armTravel);
      }
      else{
        elevador.set(-armHoldDown);
      }
    }
  
    if(armController.getRawButtonPressed(3) && !armUp){
      lastBurstTime = Timer.getFPGATimestamp();
      armUp = true;
    }
    else if(armController.getRawButtonPressed(4) && armUp){
      lastBurstTime = Timer.getFPGATimestamp();
      armUp = false;
    }

  }

  @Override
  public void disabledInit() {
    //On disable turn off everything
    //si este esta encendido apaga todo los motores
    //done to solve issue with motors "remembering" previous setpoints after reenable
    driveLeftA.set(0);
    driveLeftB.set(0);
    driveRightA.set(0);
    driveRightB.set(0);
    elevador.set(0);
    intake.set(ControlMode.PercentOutput, 0);
  }
  /*
  do{
    if(armController.getRawButtonPressed (11)){
      armUp = false;
    

  }}while(elevator.set(armTravel);)
  */
  
}

/**
 * 
 */




