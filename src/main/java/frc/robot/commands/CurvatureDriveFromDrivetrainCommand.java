package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;


public class CurvatureDriveFromDrivetrainCommand extends CommandBase {
    private final Drivetrain drivetrain;
    private final OI oi;

    public CurvatureDriveFromDrivetrainCommand(Drivetrain drivetrain, OI oi)
    {
        this.drivetrain = drivetrain;
        this.oi = oi;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        drivetrain.curvatureDrive(oi.getCurvatureForward(),oi.getCurvatureRotate(),oi.isQuickTurn());
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
