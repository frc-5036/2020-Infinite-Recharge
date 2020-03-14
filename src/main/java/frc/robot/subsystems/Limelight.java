package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Limelight implements Subsystem {

    private final NetworkTable m_limelight;
    private double tx, ty, tv;

    private final double kP = 0.03;

    private final double AIM_THRESHOLD = 1.0;

    public Limelight()
    {
        m_limelight = NetworkTableInstance.getDefault().getTable("limelight");

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        tx = m_limelight.getEntry("tx").getDouble(0);
       // ty = m_limelight.getEntry("ty").getDouble(0);
       // tv = m_limelight.getEntry("tv").getDouble(0);
        SmartDashboard.putNumber("X", getTX());
        //SmartDashboard.putNumber("limelight X", getTX());
        //SmartDashboard.putNumber("limelight Y", ty);
        //SmartDashboard.putNumber("Target Found", tv);

        streamSetup(2);
        limelightLED(1);
    }

    public double getTX() {
        return tx;
    }

    public double getTY() {
        return ty;
    }

    public boolean isTargetValid() {
        return (tv == 1.0);
    }

    public double autoAim()
    {

        double heading_error = tx; //Might need to be negative
        double steering_adjusment = 0.0;

        if(tx > AIM_THRESHOLD)
        {
            steering_adjusment = kP * heading_error;
        }
        else if(tx < AIM_THRESHOLD)
        {
            steering_adjusment = kP * heading_error;
        }

        double rotation = steering_adjusment;

        return rotation;
    }
    public void limelightLED (int value)
    {
        m_limelight.getEntry("ledMode").setNumber(value);
    }
    public void streamSetup(int value)
    {
        m_limelight.getEntry("stream").setNumber(value);
    }




}

