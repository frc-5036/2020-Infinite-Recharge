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

 //Variable for Curvature Drive Methods
    double mQuickStopAccumulator;
    public static final double THROTTLE_DEADBAND = 0.02;
    private static final double WHEEL_DEADBAND = 0.02;
    private static final double TURN_SENS = 1.0;



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


 public void curvatureDrive(double forward, double roatation, boolean isQuickTurn)
 {
     roatation = handleDeadband(roatation, WHEEL_DEADBAND);
     forward = handleDeadband(forward, THROTTLE_DEADBAND);

     double overPower;

     double angularPower;

     if(isQuickTurn)
     {
         if ((Math.abs(forward) < 0.2))
         {
             double alpha = 0.1;
              mQuickStopAccumulator = (1 - alpha * mQuickStopAccumulator + alpha * limit(roatation, 1.0) * 2);
         }
         overPower = 1.0;
         angularPower = roatation;
     }
     else
     {
         overPower = 0.0;
         angularPower = Math.abs(forward) * roatation * TURN_SENS - mQuickStopAccumulator;
         if(mQuickStopAccumulator > 1)
         {
             mQuickStopAccumulator -= 1;
         }
         else if (mQuickStopAccumulator < -1)
         {
             mQuickStopAccumulator += 1;
         }
         else
         {
             mQuickStopAccumulator = 0.0;
         }
     }

     double rightMotors = forward - angularPower;
     double leftMotors = forward + angularPower;

     if (leftMotors > 1.0)
     {
         rightMotors -= overPower *(leftMotors - 1.0);
         leftMotors = 1.0;
     }
     else if (rightMotors > 1)
     {
         leftMotors -= overPower * (-1.0 - leftMotors);
         leftMotors = -1.0;
     }
     else if (leftMotors < -1.0)
     {
         rightMotors += overPower * (-1.0 - leftMotors);
         leftMotors = -1.0;
     }
     else if (rightMotors < -1.0)
     {
         leftMotors += overPower * (-1.0 - rightMotors);
         rightMotors = -1.0;
     }



     setLeftMotors(leftMotors);
     setRightMotors(rightMotors);
 }


 public double handleDeadband(double val, double deadband)
 {
     if( Math.abs(val) < Math.abs(deadband))
     {
         return 0;
     }
     else
     {
         return val;
     }
 }

 public static double limit(double v, double limit)
 {
     return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
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
