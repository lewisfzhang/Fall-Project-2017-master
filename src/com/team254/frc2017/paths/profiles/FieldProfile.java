package com.team254.frc2017.paths.profiles;

/**
 * Interface that holds all the field measurements required by the PathAdapter
 * 
 * @see PathAdapter
 */
public interface FieldProfile {

    public double getRedWallToAirship();
    
    public double getRedCenterToDivide();
    
    public double getRedCenterToFar();

    public double getBlueWallToAirship();
    
    public double getBlueCenterToBoiler();
    
    public double getBlueCenterToFar();

}
