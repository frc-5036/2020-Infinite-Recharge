package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotMap;

import java.util.Arrays;
import java.util.List;

public class Shooter implements Subsystem {

    List<IMotorController> shooterMotors;
    DigitalInput beamBreaker;
    TalonSRX talonWthEnc;

    double desiredRPM = 0;



    public Shooter(List<IMotorController> shooterMotors, TalonSRX talonWthEnc, DigitalInput beamBreaker) {
        // TODO: Set the default command, if any, for this subsystem by calling setDefaultCommand(command)
        //       in the constructor or in the robot coordination class, such as RobotContainer.

        this.shooterMotors = shooterMotors;
        this.beamBreaker = beamBreaker;
        this.talonWthEnc = talonWthEnc;

        talonWthEnc.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic() 
    {
        updateSmartdashboard();    
    }

    public static Shooter createForRobot()
    {

        TalonSRX shooter1 = new TalonSRX(RobotMap.SHOOTER_1);

        TalonSRX shooter2 = new TalonSRX(RobotMap.SHOOTER_2);
        VictorSPX shooter3 = new VictorSPX(RobotMap.SHOOTER_3);
        VictorSPX shooter4 = new VictorSPX(RobotMap.SHOOTER_4);

        shooter2.follow(shooter1);
        shooter3.follow(shooter1);
        shooter4.follow(shooter1);

        shooter1.configFactoryDefault();
        shooter2.configFactoryDefault();
        shooter3.configFactoryDefault();
        shooter4.configFactoryDefault();

        shooter1.setInverted(true);
        shooter2.setInverted(InvertType.FollowMaster);
        shooter4.setInverted(InvertType.OpposeMaster);
        shooter3.setInverted(InvertType.OpposeMaster);

        shooter1.setNeutralMode(NeutralMode.Coast);
        shooter2.setNeutralMode(NeutralMode.Coast);
        shooter3.setNeutralMode(NeutralMode.Coast);
        shooter3.setNeutralMode(NeutralMode.Coast);

        shooter1.configOpenloopRamp(1);
        shooter2.configOpenloopRamp(1);

//        shooter1.configContinuousCurrentLimit(30);
//        shooter2.configContinuousCurrentLimit(30);
//
//        shooter1.configPeakCurrentLimit(40);


        DigitalInput breaker = new DigitalInput(RobotMap.SHOOTER_BEAM_BREAKER);

        return new Shooter(Arrays.asList(shooter2,shooter3,shooter4), shooter1, breaker);
    }
    public void setPower(double power)
    {
        talonWthEnc.set(ControlMode.PercentOutput, power);

        for (IMotorController sc: shooterMotors)
        {
            sc.set(ControlMode.PercentOutput, power);
        }
    }
    public void stopShooters()
    {
        setPower(0);
    }

    public void setDesiredRPM(double desiredRPM)
    {
        this.desiredRPM = desiredRPM;
    }
    public double getDesiredRPM()
    {
        return desiredRPM;
    }

    public boolean getBeamBreaker()
    {
        return beamBreaker.get();
    }
    public double getRPM()
    {
        return -(talonWthEnc.getSelectedSensorVelocity()/6130.2)*600;
    }
    public double getPostionEnc()
    {
        return talonWthEnc.getSelectedSensorPosition();
    }
    public void resetPostionEnc()
    {
        talonWthEnc.setSelectedSensorPosition(0);
    }
    public double getMotorPower()
    {
        return talonWthEnc.getMotorOutputPercent();
    }



    public void updateSmartdashboard()
    {
        SmartDashboard.getBoolean("Shooter Beam Breaker", getBeamBreaker());
        SmartDashboard.putNumber("Shooter 1", talonWthEnc.getMotorOutputPercent());
        SmartDashboard.putNumber("Shooter 2", shooterMotors.get(0).getMotorOutputPercent());
        SmartDashboard.putNumber("Shooter 3", shooterMotors.get(1).getMotorOutputPercent());
        SmartDashboard.putNumber("Shooter 4", shooterMotors.get(2).getMotorOutputPercent());
        SmartDashboard.putNumber("Encoder Velocity", getRPM());

    }


}

