package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

import java.util.Set;

public class AutoAim implements Command {
    private final Drivetrain drivetrain;
    private final Limelight limelight;
    private final Set<Subsystem> subsystems;

    public AutoAim(Drivetrain drivetrain, Limelight limelight) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        this.subsystems = Set.of(this.drivetrain, this.limelight);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        drivetrain.arcadeDrive(0, limelight.autoAim());
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
