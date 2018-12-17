package org.daredevils2512.powerup.commands;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.SwerveModifier;
import jaci.pathfinder.modifiers.TankModifier;
import org.daredevils2512.powerup.Robot;
import org.daredevils2512.powerup.RobotMap;

public class PathfinderTest extends Command {
    EncoderFollower left;
    EncoderFollower right;
    @Override
    protected void initialize() {
        double pidPValue = 0.055;
        double pidIValue = 0.00001;
        double pidDValue = 0.045;

        Waypoint[] points = new Waypoint[]{
            new Waypoint(0, 0.5, Pathfinder.d2r(0)),
            new Waypoint(0, 0, Pathfinder.d2r(0))
            // new Waypoint(0,0, 0)
        };
        // Waypoint point = new Waypoint(0, 1, 0);

        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.0, 2.0, 60.0);
        Trajectory trajectory = Pathfinder.generate(points, config);
        // TankModifier modifier = new TankModifier(trajectory).modify(0.5);
        // left = new EncoderFollower(modifier.getLeftTrajectory());
        // right = new EncoderFollower(modifier.getRightTrajectory());

        System.out.print(trajectory.length());
        for (int i = 0; i < trajectory.length(); i++) {
            Trajectory.Segment seg = trajectory.get(i);
            
            System.out.printf("%f,%f,%f,%f,%f,%f,%f,%f\n", 
                seg.dt, seg.x, seg.y, seg.position, seg.velocity, 
                    seg.acceleration, seg.jerk, seg.heading);
        }

        // left.configureEncoder(Robot.m_drivetrain.getLeftEncoderValue(), 128, 0.1);
        // left.configurePIDVA(pidPValue, pidIValue, pidDValue, 1 / 1.7, 0);
        // right.configureEncoder(Robot.m_drivetrain.getRightEncoderValue(), 128, 0.1);
        // right.configurePIDVA(pidPValue, pidIValue, pidDValue, 1 / 1.7, 0);
    }

    @Override
    protected void execute() {
        // double l = left.calculate(Robot.m_drivetrain.getLeftEncoderValue());
        // double r = right.calculate(Robot.m_drivetrain.getRightEncoderValue());

        // double gyro_heading = Robot.m_navX.navx.getCompassHeading();    // Assuming the gyro is giving a value in degrees
        // double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

        // double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading); // set to -180 to 180
        // double turn = 0.8 * (-1.0/80.0) * angleDifference;

        // Robot.m_drivetrain.driveRobotTank(l + turn,r - turn);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
