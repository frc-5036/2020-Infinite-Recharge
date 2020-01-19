/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController.Button;

/**
 * Add your docs here.
 */
public class OI 
{
    Joystick driverController = new Joystick(RobotMap.DRIVER_CONTROLLER);
    Joystick operatorController = new Joystick(RobotMap.OPERATOR_CONTROLLER);


    public double getForward()
    {
       double forward = driverController.getRawAxis(1); 

       //Deadband 
       if( forward < 0.3)
       {
           return 0;
       }
       else
       {
           return forward;
       }
    }
    public double getRotate()
    {
        double rotate = driverController.getRawAxis(0);

        if(rotate < 0.3)
        {
            return 0;
        }
        else 
        {
            return rotate;
        }
    }

    //For Curveature Drive (Will prolly get rid of later)
    public double getCurvatureForward()
    {
       double forward = operatorController.getRawAxis(1); 

       //Deadband 
       if( forward < 0.3)
       {
           return 0;
       }
       else
       {
           return forward;
       }
    }
    public double getCurvatureRotate()
    {
        double rotate = operatorController.getRawAxis(0);

        if(rotate < 0.3)
        {
            return 0;
        }
        else 
        {
            return rotate;
        }
    }
    public boolean isQuickTurn()
    {
        return operatorController.getRawButton(1);//Needs to be changed to be a proper button
    }
}
