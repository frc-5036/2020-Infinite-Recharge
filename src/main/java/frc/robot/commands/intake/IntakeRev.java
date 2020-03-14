package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Intake;

import java.util.Set;

public class IntakeRev implements Command {
    private final Intake intake;
    private final Set<Subsystem> subsystems;

    public IntakeRev(Intake intake) {
        this.intake = intake;
        this.subsystems = Set.of(this.intake);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        intake.Run(-0.6);

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
