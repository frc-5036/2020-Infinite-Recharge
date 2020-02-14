package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Intake;


public class intakeOut extends CommandBase {
    private final Intake intake;
    private final OI oi;

    public intakeOut(Intake intake, OI oi) {
        this.intake = intake;
        this.oi = oi;
        addRequirements(intake);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        intake.intakeOut();
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
