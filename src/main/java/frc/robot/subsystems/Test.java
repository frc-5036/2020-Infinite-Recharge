package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Test implements Subsystem {

    private final NetworkTable m_limelight;
    private double tx, ty, tv;

    private final double kP = 0.1;

    private final double AIM_THRESHOLD = 1.0;

    public Test()
    {
        m_limelight = NetworkTableInstance.getDefault().getTable("limelight-shooter");

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        tx = m_limelight.getEntry("tx").getDouble(0);
        // ty = m_limelight.getEntry("ty").getDouble(0);
        // tv = m_limelight.getEntry("tv").getDouble(0);
        SmartDashboard.putNumber("X", 5);
        //SmartDashboard.putNumber("limelight X", getTX());
        //SmartDashboard.putNumber("limelight Y", ty);
        //SmartDashboard.putNumber("Target Found", tv);
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

}

