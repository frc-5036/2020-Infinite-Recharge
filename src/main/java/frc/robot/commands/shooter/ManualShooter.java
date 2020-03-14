package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.OI;
import frc.robot.subsystems.Shooter;

import java.util.Set;

public class ManualShooter implements Command {
    private final Shooter shooter;
    private final OI oi;
    private final Set<Subsystem> subsystems;

    public ManualShooter(Shooter shooter, OI oi) {
        this.shooter = shooter;
        this.oi = oi;
        this.subsystems = Set.of(this.shooter);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute()
    {
        shooter.setPower(oi.getManualShooter());
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
