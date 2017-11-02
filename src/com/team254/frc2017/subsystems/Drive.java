package com.team254.frc2017.subsystems;

import com.ctre.CANTalon;

import com.team254.frc2017.Constants;

public class Drive {

    private static Drive mInstance = new Drive();

    public static Drive getInstance() {
        return mInstance;
    }

    private CANTalon mLeftMaster, mRightMaster, mLeftSlave, mRightSlave;

    private Drive() {
        initializeTalons();
    }
    
    private void initializeTalons() {
        mLeftMaster = new CANTalon(Constants.kLeftDriveMasterId);
        mLeftMaster.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        mLeftMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

        mLeftSlave = new CANTalon(Constants.kLeftDriveSlaveId);
        mLeftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
        mLeftSlave.set(Constants.kLeftDriveMasterId);

        mRightMaster = new CANTalon(Constants.kRightDriveMasterId);
        mRightMaster.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
        mRightMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

        mRightSlave = new CANTalon(Constants.kRightDriverSlaveId);
        mRightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
        mRightSlave.set(Constants.kRightDriveMasterId);

    }
    

}
