package com.team254.frc2017.paths.profiles;

/**
 * Contains the measurements for the practice field at the 254 lab
 */
public class PracticeField implements FieldProfile {

    @Override
    public double getRedCenterToDivide() {
        return 55.5;
    }

    @Override
    public double getRedWallToAirship() {
        return 118;
    }

    @Override
    public double getBlueCenterToBoiler() {
        return 125.5;
    }

    @Override
    public double getBlueWallToAirship() {
        return 114.0;
    }

    @Override
    public double getRedCenterToFar() {
        return 127.5;
    }

    @Override
    public double getBlueCenterToFar() {
        return 125.5;
    }

}
