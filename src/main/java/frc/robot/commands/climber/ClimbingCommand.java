package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.customInterfaces.OperatorController;
import frc.robot.subsystems.Climber;

import java.util.Set;

public class ClimbingCommand implements Command {
    private final Climber climber;
    private final Set<Subsystem> subsystems;

    public ClimbingCommand(Climber climber) {
        this.climber = climber;
        this.subsystems = Set.of(climber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        System.out.println("From climbing command");
        climber.goUp();
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        climber.goDown();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return this.subsystems;
    }
}
