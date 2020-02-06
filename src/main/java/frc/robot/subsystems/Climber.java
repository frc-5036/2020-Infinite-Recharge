/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climber extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public VictorSP climberMotor1;
  public VictorSP climberMotor2;


  public Climber()
  {
    climberMotor1=new VictorSP (RobotMap.VICTOR_CLIMBER_MOTOR_1);
    climberMotor2=new VictorSP (RobotMap.VICTOR_CLIMBER_MOTOR_2);

  }

  public void climbUp(double power)
  {
    climberMotor1.set(power);
    climberMotor2.set(power);
  }

  public void climbDown (double power)
  {
    climberMotor1.set(-power);
    climberMotor2.set(-power);
  }

  public void stop ()
  {
    climberMotor1.set(0);
    climberMotor2.set(0);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
