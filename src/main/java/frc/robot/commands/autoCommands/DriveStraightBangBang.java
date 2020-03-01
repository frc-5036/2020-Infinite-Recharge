package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Drivetrain;

import java.util.Set;

public class DriveStraightBangBang implements Command {
    private final Drivetrain drivetrain;
    private final Set<Subsystem> subsystems;
    private double desiredDistance;
    private final double TOLERANCE = 5.0;
    private double currentDistance;

    public DriveStraightBangBang(Drivetrain drivetrain, double desiredDistance)
    {
        this.drivetrain = drivetrain;
        this.subsystems = Set.of(this.drivetrain);
        this.desiredDistance = desiredDistance;
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        currentDistance = drivetrain.getRightEncoderDistance();

        if(currentDistance < desiredDistance)
        {
            drivetrain.arcadeDrive(0.5,0);
        }
        else if(currentDistance > desiredDistance)
        {
            drivetrain.arcadeDrive(0,0);
        }
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        if((currentDistance - TOLERANCE) < desiredDistance || (currentDistance + TOLERANCE) > desiredDistance)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
