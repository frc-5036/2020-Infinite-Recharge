package frc.robot.extra;

public class PID
{

   double currentError, lastError, target, pConstant, dConstant;
   public PID(double target, double pConstant, double dConstant)
   {
        lastError = target;
        this.target = target;
        currentError = target - lastError;
        this.pConstant = pConstant;
        this.dConstant = dConstant;
   }

   public double proportionalControl()
   {
       return pConstant*currentError;
   }

   public double derivativeControl()
   {
       return dConstant * (currentError - lastError);
   }

   public double getOutput(double currentError)
   {
       this.currentError = currentError;
       double output = proportionalControl() + derivativeControl();
       lastError = currentError;
       return output;
   }
}
