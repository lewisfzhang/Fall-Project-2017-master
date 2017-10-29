package com.team254.frc2017.paths.profiles;

/**
 * Contains the measurements for the Daly field at St. Louis champs
 */
public class DalyField implements FieldProfile {

    @Override
    public double getRedCenterToDivide() {
        return 125.44;
    }

    @Override
    public double getRedWallToAirship() {
        return 114.5;
    }

    @Override
    public double getBlueCenterToBoiler() {
        return 126.76;
    }

    @Override
    public double getBlueWallToAirship() {
        return 113.75;
    }

    @Override
    public double getRedCenterToFar() {
        return 125.44;
    }

    @Override
    public double getBlueCenterToFar() {
        return 126.76;
    }

}
