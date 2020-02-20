package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.customInterfaces.OperatorController;
import frc.robot.subsystems.Intake;

import java.util.HashSet;
import java.util.Set;


public class DefaultIntakeCommand implements Command{
    private final Intake intake;
    private final OperatorController oi;
    private final Set<Subsystem> required;
    public int counter;

    public DefaultIntakeCommand(Intake intake, OperatorController oi)
    {

        this.intake = intake;
        this.oi = oi;
        required = new HashSet<>();
        required.add(intake);

    }


    @Override
    public void initialize()
    {
        counter = 0;
    }

    @Override
    public void execute()
    {
        if(oi.getIntakeBtn())
        {
            if (intake.getBeamBreaker())
            {
                counter++;
            }
            intake.intakeOut();
            intake.Run(0.5);
        }
        else
        {
            intake.intakeIn();
            intake.Stop();
        }
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return required;
    }

    @Override
    public void end(boolean interrupted)
    {

    }
}
