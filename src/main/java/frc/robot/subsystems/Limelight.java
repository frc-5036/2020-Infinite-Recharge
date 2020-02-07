package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Limelight implements Subsystem
{
    private double desiredDistance;

    private double kP_rotation = 0.0; //Proportional Gain  Needs to be toned
    private double kP_forward = 0.0; //For forward
    private double minCommmand = 0.0; //Needs to be toned

    //Getting limelight values variables
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    double tx; //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
    double ty; //Vertical Offset from Crosshair to target given  (-27 degrees to 27 degrees)

    //Getting Distance variables
    double distanceToLimelightFromFloor;
    double heightOfTheTarget;
    double mountedAngle;


    public Limelight()
    {
       tx = table.getEntry("<tx>").getDouble(0);
       ty = table.getEntry("<ty>").getDouble(0);


       //Getting distance
        distanceToLimelightFromFloor = 0.0;
        heightOfTheTarget = 0.0;
        mountedAngle = 0.0;

        desiredDistance = 0.0; //Needs to be changed



//        area = 0.0; //Needs a value in percent
//        k_constant = 4 * Math.sqrt(area);
//        distance = k_constant/Math.sqrt(area);


    }

    public double estimateDistance()
    {
        return (heightOfTheTarget - distanceToLimelightFromFloor) / Math.tan(mountedAngle + ty);
    }
    public void aimingWithVision(boolean button)
    {
        if(button)
        {
            double headingError = tx; //Might need to change to Negative
            double rotationAdjustment = 0.0;

            if (Math.abs(tx) < 1.0) //Might not need Abs
            {
                rotationAdjustment = headingError * kP_rotation + minCommmand;
            }
            else if (Math.abs(tx) > 1.0)
            {
                rotationAdjustment = headingError * kP_rotation - minCommmand;
            }

            double newRotation = rotationAdjustment;

        }
    }
    public void gettingInRange(boolean button)
    {
        double currentDistance = estimateDistance();
        double forwardAdjustment = 0;

        if (button)
        {
            double distanceError = desiredDistance - currentDistance;
            forwardAdjustment = kP_forward * distanceError;
        }
        double newForward = forwardAdjustment;
    }

    public void aimingAndGettingInRange(boolean button)
    {
        double headingError = tx; //Might need to change to Negative
        double rotationAdjustment = 0.0;
        double currentDistance = estimateDistance();
        double forwardAdjustment = 0;

        if(button)
        {
            if (Math.abs(tx) < 1.0) //Might not need Abs
            {
                rotationAdjustment = headingError * kP_rotation + minCommmand;
            }
            else if (Math.abs(tx) > 1.0)
            {
                rotationAdjustment = headingError * kP_rotation - minCommmand;
            }

            double distanceError = desiredDistance - currentDistance;
            forwardAdjustment = kP_forward * distanceError;
        }
        double newRotation = rotationAdjustment;
        double newForward = forwardAdjustment;

    }





}

