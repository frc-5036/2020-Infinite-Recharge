/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climber implements Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  List<SpeedController> climbMotors;

  public Climber(List<SpeedController> climbMotors)
  {
    this.climbMotors = climbMotors;

  }

  public Climber createForClimber()
  {
    VictorSP climberMotor1 = new VictorSP (RobotMap.VICTOR_CLIMBER_MOTOR_1);
    VictorSP climberMotor2 = new VictorSP (RobotMap.VICTOR_CLIMBER_MOTOR_2);

    return new Climber(Arrays.asList(climberMotor1, climberMotor2));
  }

  public void climbUp(double power)
  {
    for(SpeedController sc : climbMotors)
      {
        sc.set(power);
      }
  }

  public void climbDown (double power)
  {
    for(SpeedController sc : climbMotors)
      {
        sc.set(power);
      }
  }

  public void stop ()
  {
    for(SpeedController sc : climbMotors)
    {
      sc.set(0);
    }
  }

  
}
