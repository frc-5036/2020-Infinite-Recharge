package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.extra.Constants;
import frc.robot.extra.PID;
import frc.robot.subsystems.Drivetrain;

import java.util.Set;

public class DriveStraight implements Command
{
    private final Drivetrain drivetrain;
    private final Set<Subsystem> subsystems;
    private final double kP = Constants.kP_DRIVE_FORWARD;
    private final double kD = Constants.kD_DRIVE_FORWARD;
    PID pid;
    double target;
    int counter;
    final double EPSILON = 2.5;

    public DriveStraight(Drivetrain drivetrain, double target)
    {
        this.drivetrain = drivetrain;
        this.subsystems = Set.of(drivetrain);
        this.target = target;
        counter = 0;
    }

    @Override
    public void initialize()
    {
        pid = new PID(target, kP, kD);
        drivetrain.encoderReset();
        drivetrain.gyroReset();
    }

    @Override
    public void execute()
    {
        double currentError = target - pid.getOutput(drivetrain.getAverageDistace());
        double gyroCorrection = -Constants.kP_DRIVE_ROTATE * drivetrain.getGyroPitch();
        drivetrain.arcadeDrive(currentError, gyroCorrection);

    }

    @Override
    public boolean isFinished()
    {
        // TODO: Make this return true when this Command no longer needs to run execute()
        double currentError = Math.abs(target - drivetrain.getAverageDistace());
        if(currentError < EPSILON)
        {
            counter++;
        }
        if(counter > 10)
        {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        drivetrain.stop();
    }

    @Override
    public Set<Subsystem> getRequirements()
    {
        return this.subsystems;
    }
}
