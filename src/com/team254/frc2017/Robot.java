package com.team254.frc2017;

import com.team254.frc2017.subsystems.Drive;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {
    private Drive mDrive = Drive.getInstance();
    private ControlBoard mControlBoard = ControlBoard.getInstance();

    public Robot() {
    }

    @Override
    public void robotInit() {
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        double throttle = mControlBoard.getThrottle();
        double turn = mControlBoard.getTurn();
        
        // Add your code here!
        
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }
}
