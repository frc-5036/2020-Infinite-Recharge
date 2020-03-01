//package frc.robot.commands.limelight;
//
//import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Subsystem;
//import frc.robot.customInterfaces.OperatorController;
//import frc.robot.subsystems.Drivetrain;
//
//import java.util.Set;
//
//public class AutoAim implements Command {
//    private final Drivetrain drivetrain;
//    private final Set<Subsystem> subsystems;
//    private final OperatorController oi;
//
//    public AutoAim(Drivetrain drivetrain, OperatorController oi) {
//        this.drivetrain = drivetrain;
//        this.subsystems = Set.of(drivetrain);
//        this.oi = oi;
//        //double offset = drivetrain.aimingWithVision();
//    }
//
//    @Override
//    public void initialize() {
//        System.out.println("aiming");
//
//    }
//
//    @Override
//    public void execute()
//    {
//        double offset = drivetrain.aimingWithVision();
//        drivetrain.arcadeDrive(0,offset);
//
//    }
//
//    @Override
//    public boolean isFinished() {
//        if(drivetrain.aimingWithVision() == 0){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//
//    }
//
//    @Override
//    public Set<Subsystem> getRequirements() {
//        return this.subsystems;
//    }
//}
