package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

import java.sql.Time;


public class RunIndexer extends CommandBase {
    private final Indexer indexer;
    private double startTime;
    private boolean isRunningBackwards;
    private final double timeRunningForward = 500;
    private final double timeRunningBackward = 100;
    private final double clockCycle = timeRunningBackward + timeRunningForward;


    public RunIndexer(Indexer indexer) {
        this.indexer = indexer;
        addRequirements(indexer);
    }

    @Override
    public void initialize()
    {
        startTime = System.currentTimeMillis();
        isRunningBackwards = false;
    }

    @Override
    public void execute()
    {

        double currentTime = System.currentTimeMillis() - startTime;
        double timeInClockCycle = currentTime % clockCycle;
        final boolean pulserOFF = false;
        if(timeInClockCycle < timeRunningForward && pulserOFF )
        {

        }

//        else
//        {
//            indexer.runIndexer(-0.5,-0.35);
//        }
        indexer.runIndexer(0.9,0.5,0.8);
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        indexer.stopIndexer();
    }
}
