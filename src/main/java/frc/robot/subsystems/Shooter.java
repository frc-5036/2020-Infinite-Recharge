package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;

import java.util.Arrays;
import java.util.List;

public class Shooter implements Subsystem {

    List<IMotorController> shooterMotors;

    public Shooter(List<IMotorController> shooterMotors) {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.

        this.shooterMotors = shooterMotors;
    }

    public Shooter createForShooter()
    {
        TalonSRX shooter1 = new TalonSRX(RobotMap.SHOOTER_1);
        TalonSRX shooter2 = new TalonSRX(RobotMap.SHOOTER_2);
        TalonSRX shooter3 = new TalonSRX(RobotMap.SHOOTER_3);
        TalonSRX shooter4 = new TalonSRX(RobotMap.SHOOTER_4);

        return new Shooter(Arrays.asList(shooter1,shooter2,shooter3,shooter4));
    }
    public void setRPM(double RPM)
    {
        for (IMotorController sc: shooterMotors)
        {
            sc.set(ControlMode.Velocity, RPM);
        }
    }
    public void stopShooters()
    {
        for (IMotorController sc: shooterMotors)
        {
            sc.set(ControlMode.Velocity, 0);
        }
    }
//    public double getRPM()
//    {
//        return encoder.getVelocity();
//    }


}

