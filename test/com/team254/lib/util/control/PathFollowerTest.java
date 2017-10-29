package com.team254.lib.util.control;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.team254.frc2017.Constants;
import com.team254.frc2017.paths.PathBuilder;
import com.team254.frc2017.paths.PathContainer;
import com.team254.frc2017.paths.StartToBoilerGearRed;
import com.team254.frc2017.paths.PathBuilder.Waypoint;
import com.team254.lib.util.ReflectingCSVWriter;
import com.team254.lib.util.control.PathFollower;
import com.team254.lib.util.math.RigidTransform2d;
import com.team254.lib.util.math.Rotation2d;
import com.team254.lib.util.math.Translation2d;
import com.team254.lib.util.math.Twist2d;

public class PathFollowerTest {

    static final PathFollower.Parameters kParameters = new PathFollower.Parameters(
            new Lookahead(16.0, 16.0, 0.0, 120.0),
            0.0, // Inertia gain
            0.75, // Profile kp
            0.03, // Profile ki
            0.02, // Profile kv
            1.0, // Profile kffv
            0.0, // Profile kffa
            Constants.kPathFollowingMaxVel, // Profile max abs vel
            Constants.kPathFollowingMaxAccel, // Profile max abs accel
            Constants.kPathFollowingGoalPosTolerance,
            Constants.kPathFollowingGoalVelTolerance,
            Constants.kPathStopSteeringDistance
    );

    @Test
    public void testStartToBoilerGearRed() {
        PathContainer container = new StartToBoilerGearRed();
        PathFollower controller = new PathFollower(container.buildPath(), container.isReversed(), kParameters);

        ReflectingCSVWriter<PathFollower.DebugOutput> writer = new ReflectingCSVWriter<PathFollower.DebugOutput>(
                "temp.csv", PathFollower.DebugOutput.class);

        final double dt = 0.01;

        RigidTransform2d robot_pose = container.getStartPose();
        double t = 0;
        double displacement = 0.0;
        double velocity = 0.0;
        while (!controller.isFinished() && t < 10.0) {
            // Follow the path
            Twist2d command = controller.update(t, robot_pose, displacement, velocity);
            writer.add(controller.getDebug());
            robot_pose = robot_pose.transformBy(RigidTransform2d.exp(command.scaled(dt)));

            t += dt;
            final double prev_vel = velocity;
            velocity = command.dx;
            displacement += velocity * dt;

            System.out.println("t = " + t + ", displacement " + displacement + ", lin vel " + command.dx + ", lin acc "
                    + (velocity - prev_vel) / dt + ", ang vel " + command.dtheta + ", pose " + robot_pose + ", CTE "
                    + controller.getCrossTrackError() + ", ATE " + controller.getAlongTrackError());
        }
        writer.flush();
        System.out.println(robot_pose);
        assertTrue(controller.isFinished());
        assertTrue(controller.getAlongTrackError() < 1.0);
        assertTrue(controller.getCrossTrackError() < 1.0);
    }

    @Test
    public void testUpdatePath() {
        boolean updated = false;
        
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(0, 0, 0, 60));
        sWaypoints.add(new Waypoint(120, 0, 0, 60, "midpoint"));
        sWaypoints.add(new Waypoint(150, 30, 0, 60));

        Path p = PathBuilder.buildPathFromWaypoints(sWaypoints);

        PathFollower controller = new PathFollower(p, false, kParameters);

        ReflectingCSVWriter<PathFollower.DebugOutput> writer = new ReflectingCSVWriter<PathFollower.DebugOutput>(
                "temp.csv", PathFollower.DebugOutput.class);

        final double dt = 0.01;

        RigidTransform2d robot_pose = new RigidTransform2d();
        double t = 0;
        double displacement = 0.0;
        double velocity = 0.0;
        while (!controller.isFinished() && t < 10.0) {
            // Follow the path
            Twist2d command = controller.update(t, robot_pose, displacement, velocity);
            writer.add(controller.getDebug());
            robot_pose = robot_pose.transformBy(RigidTransform2d.exp(command.scaled(dt)));

            t += dt;
            final double prev_vel = velocity;
            velocity = command.dx;
            displacement += velocity * dt;

            System.out.println("t = " + t + ", displacement " + displacement + ", lin vel " + command.dx + ", lin acc "
                    + (velocity - prev_vel) / dt + ", ang vel " + command.dtheta + ", pose " + robot_pose + ", CTE "
                    + controller.getCrossTrackError() + ", ATE " + controller.getAlongTrackError());
            
            if(controller.hasPassedMarker("midpoint") && !updated) {
                updated = true;
                
                ArrayList<Waypoint> s1Waypoints = new ArrayList<Waypoint>();
                s1Waypoints.add(new Waypoint(0, 0, 0, 60));
                s1Waypoints.add(new Waypoint(120, 0, 0, 60, "midpoint"));
                s1Waypoints.add(new Waypoint(150, 20, 0, 60));

                controller.updatePath(PathBuilder.buildPathFromWaypoints(sWaypoints));
                
            }
            
        }
        writer.flush();
        System.out.println(robot_pose);
        assertTrue(controller.isFinished());
        System.out.println(robot_pose);
//        assertTrue(controller.getAlongTrackError() < 1.0);
//        assertTrue(controller.getCrossTrackError() < 1.0);
    }
}