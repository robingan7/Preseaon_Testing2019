/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
/**
 * An example command.  You can replace me with your own command.
 */
public class Spin_Until_SeeTarget extends Command {
  private NetworkTable table;
 
  public Spin_Until_SeeTarget() {
    // Use requires() here to declare subsystem dependencies

   requires(Robot.drivebase);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      table =NetworkTableInstance.getDefault().getTable("limelight");
      System.out.println("Start Spin");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    table.getEntry("ledMode").setNumber(1);
    //NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    //Robot.drivebase.speedArcadeDrive(0,0.2);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    /*
    NetworkTableEntry tv = table.getEntry("tv");
    double isSeetarget = tv.getDouble(0.0);
    System.out.println(isSeetarget);
    if(Math.abs(isSeetarget)>1.6 || isSeetarget==0.0){
      System.out.println("Can't See the target");
       return false;
    }
    else{
      System.out.println("See the target");
      return true;*/
      return true;
      
    }
  

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivebase.speedArcadeDrive(0,0);
    System.out.println("LED OFF");

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    System.out.println("Robot Stop");
  }

}