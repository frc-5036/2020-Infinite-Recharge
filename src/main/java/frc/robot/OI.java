/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.extra.Util;
import frc.robot.extra.ControllerButtons;
import frc.robot.customInterfaces.DriveController;
import frc.robot.customInterfaces.OperatorController;

/**
 * Add your docs here.
 */
public class OI implements DriveController, OperatorController
{
    ControllerButtons driverController = new ControllerButtons(RobotMap.DRIVER_CONTROLLER);
    ControllerButtons operatorController = new ControllerButtons(RobotMap.OPERATOR_CONTROLLER);


    public OI()
    {

    }

    @Override
    public double getForward()
    {
        double forward = driverController.getLeftAxisY();
        return Util.handleDeadband(forward, 0.03);
    }
    @Override
    public double getRotate()
    {
        double gettingRotate =  driverController.getRightAxisX();
        double rotate = 0.6 * (gettingRotate) + 0.4 * (Math.pow(gettingRotate,3));
        return  Util.handleDeadband(rotate, 0.03);
    }

    @Override
    public boolean getQuickTurn() 
    {
        return driverController.getR1();
    }

    @Override
    public boolean getIntakeBtn() {
        return operatorController.getYellowButton();
    }

    @Override
    public double getManualShooter()
    {
        return operatorController.getLeftAxisY();
    }

    @Override
    public boolean getClimbingBtn() {
        return operatorController.getBlueButton();
    }

    @Override
    public boolean getAutoAim() {
        return operatorController.getL1();
    }

    public double getManualIndexer()
    {
        return operatorController.getRightAxisY();
    }


}
