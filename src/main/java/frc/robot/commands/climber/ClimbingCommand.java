package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.customInterfaces.OperatorController;
import frc.robot.subsystems.Climber;

import java.util.Set;

public class ClimbingCommand implements Command {
    private final Climber climber;
    private final Set<Subsystem> subsystems;
    private final OperatorController oi;

    public ClimbingCommand(Climber climber, OperatorController oi) {
        this.climber = climber;
        this.subsystems = Set.of(climber);
        this.oi = oi;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        if (oi.getClimbingBtn())
        {
            climber.climbUp(-0.8);
        }
        else
        {
            climber.climbUp(0);
        }
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
