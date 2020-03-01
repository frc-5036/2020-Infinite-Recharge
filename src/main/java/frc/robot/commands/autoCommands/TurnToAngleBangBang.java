package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Drivetrain;

import java.util.Set;

public class TurnToAngleBangBang implements Command {
    private final Drivetrain drivetrain;
    private final Set<Subsystem> subsystems;
    double desiredAngle;
    final double ANGLE_TOLERANCE = 2;
    double currentAngle;

    public TurnToAngleBangBang(Drivetrain drivetrain, double desiredAngle) {
        this.drivetrain = drivetrain;
        this.subsystems = Set.of(this.drivetrain);
        this.desiredAngle = desiredAngle;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        currentAngle = drivetrain.getGyroYaw();

        if(currentAngle< desiredAngle)
        {
            drivetrain.arcadeDrive(0,0.5);
        }
        else if(currentAngle > desiredAngle)
        {
            drivetrain.arcadeDrive(0,0);
        }
    }

    @Override
    public boolean isFinished()
    {
        // TODO: Make this return true when this Command no longer needs to run execute()
        if( (currentAngle - ANGLE_TOLERANCE) < desiredAngle || (currentAngle + ANGLE_TOLERANCE) > desiredAngle)
        {
            return true;
        }
        return false;
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
