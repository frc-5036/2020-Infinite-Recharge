package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

import java.util.Arrays;
import java.util.List;

public class Indexer implements Subsystem {

    VictorSP topRoller, bottomRoller;
    IMotorController indexer1;
    VictorSP indexer2;

    double getCurrentTime;

    @Override
    public void periodic() {
        updateSmartdashboard();
    }

    public Indexer(VictorSP topRoller,  VictorSP indexer2, IMotorController indexer1, VictorSP bottomRoller)
    {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor  or in the robot coordination class, such as RobotContainer.

        this.topRoller = topRoller;
        this.indexer1 = indexer1;
        this.indexer2 = indexer2;
        this.bottomRoller = bottomRoller;

        getCurrentTime = Timer.getFPGATimestamp();
    }
    public static Indexer createForRobot()
    {

        VictorSP singleShaftMotor = new VictorSP(RobotMap.INDEXER_ROLLER);
        VictorSP bottomConveyorRoller = new VictorSP(RobotMap.BOTTOM_ROLLER);
        IMotorController frontIndexer1 = new TalonSRX(RobotMap.INDEXER_CONVEYOR);
        VictorSP frontIndexer2 = new VictorSP(RobotMap.INDEXER_CONVEYOR2);

        frontIndexer2.setInverted(true);
        bottomConveyorRoller.setInverted(true);
        singleShaftMotor.setInverted(true);

        return new Indexer(singleShaftMotor, frontIndexer2, frontIndexer1, bottomConveyorRoller);
    }

    public void runIndexer (double leftSidePower, double rightSidePower, double topRollerPower, double bottomRollerPower)
    {
       topRoller.set(topRollerPower);
       indexer1.set(ControlMode.PercentOutput,leftSidePower);
       indexer2.set(rightSidePower);
       bottomRoller.set(bottomRollerPower);
    }
    public void stopIndexer ()
    {
       runIndexer(0,0,0,0);
    }

//    public void pulser()
//    {
//        if(getCurrentTime % 5 == 0)
//        {
//            runIndexer(0.5, 0.5);
//
//            runIndexer(-0.5, -0.5);
//        }
//    }
    public void updateSmartdashboard()
    {
        SmartDashboard.putNumber("Indexer Sanity Check", System.currentTimeMillis());
        SmartDashboard.putNumber("Indexer top roller" , topRoller.get());
        SmartDashboard.putNumber("Indexer bottom roller", bottomRoller.get());
        SmartDashboard.putNumber("Indexer side rollers", indexer1.getMotorOutputPercent());
        //SmartDashboard.putNumber("Indexer side rollers 2", indexer2.getMotorOutputPercent());


    }




}

