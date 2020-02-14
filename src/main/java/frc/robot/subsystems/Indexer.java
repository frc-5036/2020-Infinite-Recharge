package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;

import java.util.Arrays;
import java.util.List;

public class Indexer implements Subsystem {

    List<SpeedController> speedControllers;

    double getCurrentTime;

    public Indexer(List<SpeedController> speedControllers)
    {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor  or in the robot coordination class, such as RobotContainer.

        this.speedControllers = speedControllers;

        getCurrentTime = Timer.getFPGATimestamp();
    }
    public Indexer createForIndexer()
    {
        VictorSP indexerMotor = new VictorSP(RobotMap.INDEXER_MOTOR);

        return new Indexer(Arrays.asList(indexerMotor));
    }

    public void runIndexer (double power)
    {
        for (SpeedController sc: speedControllers)
        {
           sc.set(power);
        }
    }
    public void stopIndexer ()
    {
        for (SpeedController sc: speedControllers)
        {
            sc.set(0);
        }
    }
    public void pulser()
    {
        if(getCurrentTime % 5 == 0)
        {
            runIndexer(0.5);
            Timer.delay(0.25);
            runIndexer(-0.5);
        }
    }



}

