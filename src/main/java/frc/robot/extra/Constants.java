/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.extra;

import edu.wpi.first.wpilibj.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{
    public static final int TRACK_WIDTH = 0; //Put in meters
    public static final double WHEEl_RADIUS = Units.inchesToMeters(3.0); // Put in meters
    public static final int ENCODER_RESOLUTION = 256;

    public static final double MAX_SPEED = 0; //Needs in meters per seconds
    public static final double MAX_ANGULAR_SPEED = 0; //Needs in one rotation per second

    //Robot Characterization
    public static final double kS = 0;
    public static final double kV = 0;
    public static final double kA = 0;

    //Trajectory Config
    public static final double MAX_VELOCITY = 0;
    public static final double MAX_ACCELERATION = 0;


    //PID Drivetrain Constants
    public static final double kP_DRIVE_FORWARD = 0;
    public static final double kD_DRIVE_FORWARD = 0;

    public static final double kP_DRIVE_ROTATE = 0;
    public static final double kD_DRIVE_ROTATE = 0;



}
