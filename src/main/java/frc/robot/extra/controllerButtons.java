package frc.robot.extra;

import edu.wpi.first.wpilibj.Joystick;

public class controllerButtons
{
    private Joystick joystick;

    public controllerButtons(int portNumber)
    {
        this.joystick = new Joystick(portNumber);
    }

    public double getL2()
    {
        return this.joystick.getRawAxis(2);
    }
    public double getR2()
    {
        return this.joystick.getRawAxis(3);
    }



    public double getRightAxisY()
    {
        return -this.joystick.getRawAxis(5);
    }
    public double getRightAxisX()
    {
        return -this.joystick.getRawAxis(4);
    }

    public double getLeftAxisY()
    {
        return -this.joystick.getRawAxis(1);
    }
    public double getLeftAxisX()
    {
        return -this.joystick.getRawAxis(0);
    }

    public boolean getYellowButton()
    {
        return this.joystick.getRawButton(4);
    }
    public boolean getRedButton()
    {
        return this.joystick.getRawButton(2);
    }
    public boolean getBlueButton()
    {
        return this.joystick.getRawButton(3);
    }
    public boolean getGreenButton()
    {
        return this.joystick.getRawButton(1);
    }
    public boolean getButton(int buttonNum)
    {
        return this.joystick.getRawButton(buttonNum);
    }

    public boolean getR1()
    {
        return this.joystick.getRawButton(6);
    }
    public boolean getL1()
    {
        return this.joystick.getRawButton(5);
    }

    public boolean getUpDPadButton()
    {
        return this.joystick.getPOV(0) == 0;
    }
    public boolean getDownDPadButton()
    {
        return this.joystick.getPOV(0) == 180;
    }
    public boolean getRightDPadButton()
    {
        return this.joystick.getPOV(0) == 90;
    }
    public boolean getLeftDPadButton()
    {
        return this.joystick.getPOV(0) == 270;
    }

    public boolean getStartButton()
    {
        return this.joystick.getRawButton(8);
    }
    public boolean getBackButton()
    {
        return this.joystick.getRawButton(7);
    }

    public Joystick getJoystick()
    {
        return this.joystick;
    }
















}
