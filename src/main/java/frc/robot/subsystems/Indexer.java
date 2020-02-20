package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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

    VictorSP topRoller, conveyor;

    double getCurrentTime;

    @Override
    public void periodic() {
        updateSmartdashboard();
    }

    public Indexer(VictorSP topRoller, VictorSP conveyor)
    {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor  or in the robot coordination class, such as RobotContainer.

        this.topRoller = topRoller;
        this.conveyor = conveyor;

        getCurrentTime = Timer.getFPGATimestamp();
    }
    public static Indexer createForRobot()
    {
        VictorSP indexerMotor = new VictorSP(RobotMap.INDEXER_CONVEYOR);
        VictorSP singleShaftMotor = new VictorSP(RobotMap.INDEXER_ROLLER);

        indexerMotor.setInverted(true);
        singleShaftMotor.setInverted(true);

        return new Indexer(singleShaftMotor, indexerMotor);
    }

    public void runIndexer (double conveyorpower, double topRollerPower)
    {
       topRoller.set(topRollerPower);
       conveyor.set(conveyorpower);
    }
    public void stopIndexer ()
    {
       runIndexer(0, 0);
    }

    public void pulser()
    {
        if(getCurrentTime % 5 == 0)
        {
            runIndexer(0.5, 0.5);

            runIndexer(-0.5, -0.5);
        }
    }
    public void updateSmartdashboard()
    {
        SmartDashboard.putNumber("Indexer Sanity Check", System.currentTimeMillis());
        SmartDashboard.putNumber("Indexer top roller" , topRoller.get());
        SmartDashboard.putNumber("Conveyor" ,  conveyor.get());

    }




}

