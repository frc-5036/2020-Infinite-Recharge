/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import java.util.Arrays;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Drivetrain extends SubsystemBase
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

 List<SpeedController> rightMotors;
 List<SpeedController> leftMotors;

 public Drivetrain(List<SpeedController> rightMotors, List<SpeedController> leftMotors)
 {
    this.leftMotors = leftMotors;
    this.rightMotors = rightMotors;
 }

 public static Drivetrain createForDrivetrain()
 {
   PWMTalonSRX rightFront = new PWMTalonSRX(RobotMap.RIGHT_FRONT_DRIVE);
   PWMTalonSRX leftFront = new PWMTalonSRX(RobotMap.LEFT_FRONT_DRIVE);
   PWMTalonSRX rightBack = new PWMTalonSRX(RobotMap.RIGHT_BACK_DRIVE);
   PWMTalonSRX leftBack = new PWMTalonSRX(RobotMap.LEFT_BACK_DRIVE);

   //Setting Right to Reverse

   rightBack.setInverted(true);
   rightFront.setInverted(true);

   return new Drivetrain(Arrays.asList(rightFront, rightBack), Arrays.asList(leftFront, leftBack));
 }

 private void setMotors(double power, List<SpeedController> speedControllers)
 {
   if(Math.abs(power) > 1)
   {
     power = power / (Math.abs(power));
   }
   for(SpeedController sc : speedControllers)
   {
     sc.set(power);
   }
 }

 private void setLeftMotors(double power)
 {
   setMotors(power, leftMotors);
 }
 private void setRightMotors(double power)
 {
   setMotors(power, rightMotors);
 }

 public void arcadeDrive(double forward, double rotation)
 {
   setLeftMotors(forward - rotation);
   setRightMotors(forward - rotation);
 }

 public void Stop()
 {
   setRightMotors(0);
   setLeftMotors(0);
 }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
