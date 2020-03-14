package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Intake;

import java.util.Set;

public class IntakeForAuto implements Command {
    private final Intake intake;
    private final double timeToStop;
    private final Set<Subsystem> subsystems;
    Timer timer = new Timer();

    public IntakeForAuto(Intake intake, double timeToStop) {
        this.intake = intake;
        this.timeToStop = timeToStop;
        this.subsystems = Set.of(this.intake);

    }

    @Override
    public void initialize()
    {
        timer.start();
    }

    @Override
    public void execute()
    {
        intake.intakeOut();
        intake.Run(0.7);
    }

    @Override
    public boolean isFinished()
    {
        // TODO: Make this return true when this Command no longer needs to run execute()
       if (timer.get() >= timeToStop)
       {
           return true;
       }
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
