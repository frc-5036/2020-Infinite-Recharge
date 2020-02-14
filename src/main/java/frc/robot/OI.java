/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Extra.Util;
import frc.robot.Extra.controllerButtons;
import frc.robot.customInterfaces.DriveController;

/**
 * Add your docs here.
 */
public class OI implements DriveController
{
    controllerButtons driverController = new controllerButtons(RobotMap.DRIVER_CONTROLLER);
    controllerButtons operatorController = new controllerButtons(RobotMap.OPERATOR_CONTROLLER);



    public OI()
    {

    }

    public double getForward()
    {
       double forward = driverController.getLeftAxisY();
       System.out.println("Calling getForward");
      //Deadband
      if( forward < 0.3)
      {
          return 0;
      }
      else
      {
          return forward;
      }
        //return Util.handleDeadband(forward, 0.3);
    }
    public double getRotate()
    {
        double rotate = driverController.getRightAxisX();
        if( rotate < 0.3)
      {
          return 0;
      }
      else
      {
          return rotate;
      }
       
    }

    public boolean getQuickTurn() 
    {
        return operatorController.getR1();
    }


}
