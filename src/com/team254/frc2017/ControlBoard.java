package com.team254.frc2017;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Contains the button mappings for the competition control board. Like the drive code, one instance of the ControlBoard
 * object is created upon startup, then other methods request the singleton ControlBoard instance. Implements the
 * ControlBoardInterface.
 * 
 * @see ControlBoardInterface.java
 */
public class ControlBoard {
    private static ControlBoard mInstance = null;

    private static final boolean kUseGamepad = false;

    public static ControlBoard getInstance() {
        if (mInstance == null) {
            mInstance = new ControlBoard();
        }
        return mInstance;
    }

    private final Joystick mThrottleStick;
    private final Joystick mTurnStick;
    private final Joystick mButtonBoard;

    protected ControlBoard() {
        mThrottleStick = new Joystick(0);
        mTurnStick = new Joystick(1);
        mButtonBoard = new Joystick(2);
    }

    // DRIVER CONTROLS
    public double getThrottle() {
        return -mThrottleStick.getRawAxis(0);
    }

    
    public double getTurn() {
        return -mTurnStick.getY();
    }

    
    public boolean getQuickTurn() {
        return mTurnStick.getRawButton(1);
    }

    
    public boolean getLowGear() {
        return mThrottleStick.getRawButton(2);
    }

    
    public boolean getAimButton() {
        return mTurnStick.getRawButton(2);
    }

    public boolean getDriveAimButton() {
        return mThrottleStick.getRawButton(1);
    }

    // OPERATOR CONTROLS
    public boolean getFeedButton() {
        return mButtonBoard.getRawAxis(1) < -0.1;
    }
    
    public boolean getIntakeButton() {
        return mButtonBoard.getRawAxis(2) < -0.1;
    }
    
    public boolean getShooterOpenLoopButton() {
        return false;
    }
    
    public boolean getExhaustButton() {
        return mButtonBoard.getRawAxis(0) < -0.1;
    }
    
    public boolean getLeftGearButton() {
        return mButtonBoard.getRawButton(4);
    }
    
    public boolean getShooterClosedLoopButton() {
        return mButtonBoard.getRawButton(8);
    }
    
    public boolean getFlywheelSwitch() {
        return mButtonBoard.getRawAxis(3) < -0.1;
    }
    
    public boolean getHangButton() {
        return mButtonBoard.getRawButton(10);
    }
    
    public boolean getGrabGearButton() {
        return mButtonBoard.getRawButton(2);
    }
    
    public boolean getScoreGearButton() {
        return mButtonBoard.getRawButton(1);
    }
    
    public boolean getActuateHopperButton() {
        return mButtonBoard.getRawButton(3);
    }

    public boolean getBlinkLEDButton() {
        return mButtonBoard.getRawButton(9);
    }

    public boolean getRangeFinderButton() {
        return mButtonBoard.getRawButton(7);
    }
    
    public boolean getWantGearDriveLimit() {
        return mButtonBoard.getRawButton(12);
    }
    
    public boolean getCenterGearButton() {
        return mButtonBoard.getRawButton(5);
    }
    
    public boolean getRightGearButton() {
        return mButtonBoard.getRawButton(6);
    }
}
