package frc.robot.Extra;

public class Util
{
    public static double limit(double v, double limit)
    {
        return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
    }

    public static double handleDeadband(double val, double deadband)
    {
        if( Math.abs(val) < Math.abs(deadband))
        {
            return 0;
        }
        else
        {
            return val;
        }
    }
}
