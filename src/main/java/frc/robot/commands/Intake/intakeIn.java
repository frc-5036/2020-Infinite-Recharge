package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Intake;


public class intakeIn extends CommandBase {
    private final Intake intake;

    public intakeIn(Intake intake)
    {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void initialize()
    {

    }

    @Override
    public void execute()
    {
        intake.intakeIn();
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
