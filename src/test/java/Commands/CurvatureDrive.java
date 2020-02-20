package Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.customInterfaces.DriveController;
import frc.robot.subsystems.Drivetrain;


public class CurvatureDrive extends CommandBase {
    private final Drivetrain drivetrain;
    private final DriveController driveController;

    public CurvatureDrive(Drivetrain drivetrain, OI oi, DriveController driveController)
    {
        this.drivetrain = drivetrain;
        this.driveController = driveController;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        drivetrain.curvatureDrive(driveController.getForward(), driveController.getRotate(), driveController.getQuickTurn());
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted)
    {
        drivetrain.stop();
    }
}
