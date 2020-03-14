package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.customInterfaces.OperatorController;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

import java.util.HashSet;
import java.util.Set;


public class DefaultIntakeCommand implements Command{
    private final Intake intake;
    private Indexer indexer;
    private final OperatorController oi;
    private final Set<Subsystem> required;
    public int counter;

    public DefaultIntakeCommand(Intake intake, OperatorController oi, Indexer indexer)
    {

        this.intake = intake;
        this.oi = oi;
        this.indexer = indexer;
        required = new HashSet<>();
        required.add(intake);
        required.add(indexer);

    }


    @Override
    public void initialize()
    {
        counter = 0;
    }

    @Override
    public void execute()
    {


//            if (intake.getBeamBreaker() == false)
//            {
//                counter++;
//            }

//            if (intake.getButtonSensor() == false)
//            {
//                indexer.stopIndexer();
//            }
//            else
//            {
//                indexer.runIndexer(0.9,0.3,0.2);
//            }

            intake.intakeOut();
            intake.Run(0.75);

    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        if(counter == 5)
        {
            counter = 0;
            return true;
        }
        return false;
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return required;
    }

    @Override
    public void end(boolean interrupted)
    {
        intake.Stop();

    }
}
