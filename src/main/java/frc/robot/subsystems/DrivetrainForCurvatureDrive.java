/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DrivetrainForCurvatureDrive extends SubsystemBase {
  /**
   * Creates a new DrivetrainForCurvatureDrive.
   */

  SpeedController leftFront = new PWMTalonSRX(RobotMap.LEFT_FRONT_DRIVE);
  SpeedController leftBack = new PWMTalonSRX(RobotMap.LEFT_BACK_DRIVE);
  SpeedController rightFront = new PWMTalonSRX(RobotMap.RIGHT_FRONT_DRIVE);
  SpeedController rightBack = new PWMTalonSRX(RobotMap.RIGHT_BACK_DRIVE);

  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftBack);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightBack);

  DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
  

  public DrivetrainForCurvatureDrive() 
  {
   rightBack.setInverted(true);
   rightFront.setInverted(true);

   //For Safety
   leftBack.setInverted(false);
   leftFront.setInverted(false);
  }

  public void curvatureDrive(double forward, double rotation, boolean isQuickTurn)
  {
    drive.curvatureDrive(forward, rotation, isQuickTurn);
  }
  public void curvatureStop()
  {
    drive.curvatureDrive(0, 0, false);
  }

  public void testDrive(double xSpeed, double xRotation, boolean isQuickTurn)
  {
    
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
