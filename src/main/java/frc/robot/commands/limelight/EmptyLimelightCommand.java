package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Limelight;

import java.util.Set;

public class EmptyLimelightCommand implements Command {
    private final Set<Subsystem> subsystems;
    private final Limelight limelight;

    public EmptyLimelightCommand(Limelight limelight) {
        this.limelight = limelight;
        this.subsystems = Set.of(limelight);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {

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
