/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climber implements Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Relay relay;

  @Override
  public void periodic()
  {
    //updateShuffle();
  }

  public Climber()
  {
    relay = new Relay(RobotMap.RELAY);
  }

  public void goUp()
  {
    relay.set(Relay.Value.kForward);
  }
  public void goDown()
  {
    relay.set(Relay.Value.kOff);
  }



  public void updateShuffle()
  {
    //SmartDashboard.putNumber("Climber", climbMotors.get(1).get());
  }


  
}
