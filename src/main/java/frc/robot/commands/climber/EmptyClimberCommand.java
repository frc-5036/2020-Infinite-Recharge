package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Climber;

import java.util.Set;

public class EmptyClimberCommand implements Command {
    private final Climber climber;
    private final Set<Subsystem> subsystems;

    public EmptyClimberCommand(Climber climber) {
        this.climber = climber;
        this.subsystems = Set.of(this.climber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

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
