
package org.usfirst.frc.team1124.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team1124.robot.Robot;

/**
 *
 */
public class IndexDown extends Command {
    public IndexDown(int index) {
        // Use requires() here to declare subsystem dependencies
        this.requires(Robot.belt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.belt.enable();
    	Robot.belt.indexDown();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.belt.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.belt.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
