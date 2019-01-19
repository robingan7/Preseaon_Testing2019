package frc.robot.commands;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
//import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;

import edu.wpi.first.networktables.NetworkTableEntry;

import edu.wpi.first.networktables.NetworkTableInstance;
/**
 * An example command.  You can replace me with your own command.
 */
public class Stop extends Command {
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  public Stop() {
    // Use requires() here to declare subsystem dependencies
   requires(Robot.drivebase);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      
      System.out.println("Start Stoping");
      Robot.drivebase.stop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
   return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
    System.out.println("Stop Found");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
