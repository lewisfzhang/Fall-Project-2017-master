package com.team254.frc2017.paths;

import com.team254.frc2017.paths.PathBuilder.Waypoint;
import com.team254.lib.util.control.Path;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Translation2d;

import java.util.ArrayList;

/**
 * Path from the red alliance wall to the red hopper.
 * 
 * Used in RamHopperShootModeRed
 * 
 * @see RamHopperShootModeRed
 * @see PathContainer
 */
public class StraightLine implements PathContainer {
    @Override
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(0, 0, 0, 60));
        sWaypoints.add(new Waypoint(100, 0, 0, 60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints, -60, 60);
    }

    @Override
    public RigidTransform2d getStartPose() {
        return new RigidTransform2d(new Translation2d(0, 0), Rotation2d.fromDegrees(180.0));
    }

    @Override
    public boolean isReversed() {
        return true;
    }
    
    public static void main(String[] args) {
        Path p = new StraightLine().buildPath();
        System.out.println(p);
    }
    // WAYPOINT_DATA:
    // [{"position":{"x":16,"y":90},"speed":0,"radius":0,"comment":""},{"position":{"x":92,"y":90},"speed":90,"radius":54,"comment":""},{"position":{"x":92,"y":29},"speed":90,"radius":0,"comment":""},{"position":{"x":92,"y":24},"speed":40,"radius":0,"comment":""},{"position":{"x":92,"y":4},"speed":40,"radius":0,"comment":""}]
    // IS_REVERSED: false
    // FILE_NAME: StartToHopperRed
}