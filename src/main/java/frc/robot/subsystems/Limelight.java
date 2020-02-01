package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Limelight implements Subsystem
{
    private boolean limelightHasValidTarget = false;
    private double limelightForward = 0.0;
    private double getLimelightRotate = 0.0;

    private double kP = 0.0; //Proportional Gain  Needs to be toned
    private double minCommmand = 0.0; //Needs to be toned

    //Getting limelight values variables
    double tx; //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    double ty; //Vertical Offset from Crosshair to target given  (-27 degrees to 27 degrees)

    //Getting Distance Methods
    double distanceToLimelightFromFloor;
    double heightOfTheTarget;
    double mountedAngle;
    double distance;

    public Limelight()
    {
       tx =  NetworkTableInstance.getDefault().getTable("limelight").getEntry("<tx>").getDouble(0);
       ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("<ty>").getDouble(0);


       //Getting distance

        distanceToLimelightFromFloor = 0.0;
        heightOfTheTarget = 0.0;
        mountedAngle = 0.0;

        distance = (heightOfTheTarget - distanceToLimelightFromFloor) / Math.tan(mountedAngle + ty);
//        area = 0.0; //Needs a value in percent
//        k_constant = 4 * Math.sqrt(area);
//        distance = k_constant/Math.sqrt(area);


    }

    public void gettingInRange(boolean button)
    {
        if(button)
        {
            double headingError = tx; //Might need to change to Negative
            double rotationAdjustment = 0.0;

            if (Math.abs(headingError) < 1.0)
            {
                rotationAdjustment = headingError * kP + minCommmand;
            }
            if (Math.abs(headingError) > 1.0)
            {
                rotationAdjustment = headingError * kP - minCommmand;
            }

            double newRotation = rotationAdjustment;

        }
    }




}

