package com.team254.frc2017.auto.modes;

import edu.wpi.first.wpilibj.Timer;

import com.team254.frc2017.auto.AutoModeBase;
import com.team254.frc2017.auto.AutoModeEndedException;
import com.team254.frc2017.auto.actions.Action;
import com.team254.frc2017.auto.actions.AutoScoreGearAction;
import com.team254.frc2017.auto.actions.DrivePathAction;
import com.team254.frc2017.auto.actions.ForceEndPathAction;
import com.team254.frc2017.auto.actions.ParallelAction;
import com.team254.frc2017.auto.actions.PrintDebugAction;
import com.team254.frc2017.auto.actions.ResetPoseFromPathAction;
import com.team254.frc2017.auto.actions.SeriesAction;
import com.team254.frc2017.auto.actions.WaitAction;
import com.team254.frc2017.auto.actions.WaitUntilSeesTargetAction;
import com.team254.frc2017.paths.PathContainer;
import com.team254.frc2017.paths.StartToBoilerGearRed; 
import com.team254.lib.util.math.Rotation2d;

import java.util.Arrays;


public class BoilerGearRed extends AutoModeBase {

    @Override
    protected void routine() throws AutoModeEndedException {
        PathContainer boilerGearPath = new StartToBoilerGearRed();
        double start = Timer.getFPGATimestamp();
        runAction(new ResetPoseFromPathAction(boilerGearPath));
        runAction(new ParallelAction(Arrays.asList(new Action[] {
                new DrivePathAction(boilerGearPath),
                new SeriesAction(Arrays.asList(new Action[] {
                        new WaitUntilSeesTargetAction(), new PrintDebugAction("YO I SEES GEER"), new ForceEndPathAction(), new AutoScoreGearAction(Rotation2d.fromDegrees(240))
                }))
        })));
        System.out.println("Score Gear Time: " + (Timer.getFPGATimestamp() - start));
    }
}
