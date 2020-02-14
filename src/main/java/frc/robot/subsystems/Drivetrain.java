/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import java.util.Arrays;
import java.util.List;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.controller.PIDController;

import frc.robot.Extra.Util;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Drivetrain implements Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // Speed Controllers
    List<IMotorController> rightMotors;
    List<IMotorController> leftMotors;

    private static double kP;
    private static double kI;
    private static double kD;

    // Variable for Curvature Drive Methods
    double mQuickStopAccumulator;
    public static final double THROTTLE_DEADBAND = 0.02;
    private static final double WHEEL_DEADBAND = 0.02;
    private static final double TURN_SENS = 1.0;

    // Sensors

    Encoder rightEncoder;
    Encoder leftEncoder;
    AHRS gyro;

    // Stuff for Pathfinder
    DifferentialDriveKinematics kinematics;
    DifferentialDriveOdometry odometry;
    Pose2d pose;
    SimpleMotorFeedforward feedforward;

    PIDController leftPIDController;
    PIDController rightPIDController;

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        pose = odometry.update(getHeading(), leftEncoder.getDistance(), rightEncoder.getDistance());
    }

    public Drivetrain(List<IMotorController> rightMotors, List<IMotorController> leftMotors, Encoder leftEncoder,
            Encoder rightEncoder, AHRS gyro) 
        {

        this.leftMotors = leftMotors;
        this.rightMotors = rightMotors;

        // Sensors
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;

        leftEncoder.setDistancePerPulse(2 * Math.PI * Constants.WHEEl_RADIUS / Constants.ENCODER_RESOLUTION);
        rightEncoder.setDistancePerPulse(2 * Math.PI * Constants.WHEEl_RADIUS / Constants.ENCODER_RESOLUTION);

        this.gyro = gyro;

        kinematics = new DifferentialDriveKinematics(Constants.TRACK_WIDTH);
        odometry = new DifferentialDriveOdometry(getHeading()); // Can add a second argument
        feedforward = new SimpleMotorFeedforward(Constants.kS, Constants.kV, Constants.kA);

        kP = 0;
        kI = 0;
        kD = 0;

        leftPIDController = new PIDController(kP, kI, kD);
        rightPIDController = new PIDController(kP, kI, kD);

        setBrakeMode();
        gyroReset();
        encoderReset();

    }

    public static Drivetrain createForRobot() 
    {
        TalonSRX rightFront = new TalonSRX(RobotMap.RIGHT_FRONT_DRIVE);
        TalonSRX leftFront = new TalonSRX(RobotMap.LEFT_FRONT_DRIVE);

        TalonSRX rightMiddle = new TalonSRX(RobotMap.RIGHT_MIDDLE_DRIVE);
        VictorSPX leftMiddle = new VictorSPX(RobotMap.LEFT_MIDDLE_DRIVE);

        TalonSRX rightBack = new TalonSRX(RobotMap.RIGHT_BACK_DRIVE);
        VictorSPX leftBack = new VictorSPX(RobotMap.LEFT_BACK_DRIVE);


        //Setting Right to Reverse
        rightBack.setInverted(true);
        rightMiddle.setInverted(true);
        rightFront.setInverted(true);

        //Sensors
        AHRS m_gyro = new AHRS(SPI.Port.kMXP);

        Encoder m_rightEncoder = new Encoder(RobotMap.RIGHT_ENC_IN, RobotMap.RIGHT_ENC_OUT,false, CounterBase.EncodingType.k4X);
        Encoder m_leftEncoder = new Encoder(RobotMap.LEFT_ENC_IN, RobotMap.LEFT_ENC_OUT, false, CounterBase.EncodingType.k4X);


        return new Drivetrain(Arrays.asList(rightFront, rightMiddle, rightBack), Arrays.asList(leftFront, leftMiddle, leftBack), m_leftEncoder, m_rightEncoder, m_gyro);
    }

    private void setMotors(double power, List<IMotorController> speedControllers)
    {
        if(Math.abs(power) > 1)
        {
            power = power / (Math.abs(power));
        }
        for(IMotorController sc : speedControllers)
        {
            sc.set(ControlMode.PercentOutput, power);
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
        setRightMotors(forward + rotation);
    }


    public void curvatureDrive(double forward, double rotation, boolean isQuickTurn)
    {
        rotation = Util.handleDeadband(rotation, WHEEL_DEADBAND);
        forward = Util.handleDeadband(forward, THROTTLE_DEADBAND);

        double overPower;

        double angularPower;

        if(isQuickTurn)
        {
            if ((Math.abs(forward) < 0.2))
            {
                double alpha = 0.1;
                mQuickStopAccumulator = (1 - alpha * mQuickStopAccumulator + alpha * Util.limit(rotation, 1.0) * 2);
            }
            overPower = 1.0;
            angularPower = rotation;
        }
        else
        {
            overPower = 0.0;
            angularPower = Math.abs(forward) * rotation * TURN_SENS - mQuickStopAccumulator;
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

    public void Stop()
    {
        setRightMotors(0);
        setLeftMotors(0);
    }
    private void setBrakeMode()
    {
        for (IMotorController sc: leftMotors)
        {
            sc.setNeutralMode(NeutralMode.Brake);
        }
        for (IMotorController sc: rightMotors)
        {
            sc.setNeutralMode(NeutralMode.Brake);
        }
    }
    public void setCoastMode()
    {
        for (IMotorController sc: leftMotors)
        {
            sc.setNeutralMode(NeutralMode.Coast);
        }
        for (IMotorController sc: rightMotors)
        {
            sc.setNeutralMode(NeutralMode.Coast);
        }
    }


    //Path Finder Methods
    public DifferentialDriveWheelSpeeds getSpeeds()
    {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
    }
    public SimpleMotorFeedforward getFeedforward()
    {
        return feedforward;
    }
    public PIDController getLeftPIDController()
    {
        return leftPIDController;
    }
    public PIDController getRightPIDController()
    {
        return rightPIDController;
    }
    public DifferentialDriveKinematics getKinematics()
    {
        return kinematics;
    }
    public Pose2d getPose()
    {
        return pose;
    }
    public void setOutput(double rightVolts, double lefVolts) //Might be wrong (Double check)
    {
        setLeftMotors(lefVolts/12);
        setRightMotors(rightVolts / 12);
    }


    //Sensors Methods
    public Rotation2d getHeading()
    {
        return Rotation2d.fromDegrees(-gyro.getAngle());
    }
    public void gyroReset()
    {
        gyro.reset();
    }
    public Encoder getRightEncoder()
    {
        return rightEncoder;
    }
    public Encoder getLeftEncoder()
    {
        return leftEncoder;
    }
    public void encoderReset()
    {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public void updateShuffleboard()
    {
        SmartDashboard.putNumber("Left Encoder", leftEncoder.getRaw());
        SmartDashboard.putNumber("Right Encoder", rightEncoder.getRaw());
    }



}
