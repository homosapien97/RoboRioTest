
package org.usfirst.frc.team1124.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1124.robot.Robot;

/**
 *
 */
public class GenericDriveWithJoystick extends Command {

    public GenericDriveWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        this.requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.isArcade) {
    		Robot.driveTrain.driveA(Robot.oi.getJoystick());
    	} else {
    		Robot.driveTrain.driveM(Robot.oi.getJoystick());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
