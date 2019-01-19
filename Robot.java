/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
//package org.usfirst.frc.team5805.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveBase;
import frc.robot.OI;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.commands.Stop;
import frc.robot.commands.Spin_Until_SeeTarget;
import frc.robot.commands.Adjust_After_SeeTarget;
import frc.robot.commands.Auto_Getclose_ReleaseHatch;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /*
  public TalonSRX frontLeft, frontRight;
  public VictorSPX rearLeft, rearRight;*/
  public static final DriveBase drivebase = new DriveBase();
  //public static OI m_oi;
  public static Joystick joy, joy2;
  //Command m_autonomousCommand;
 // SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
   // m_oi = new OI();
    //m_chooser.addDefault("Default Auto", new ExampleCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
    joy = new Joystick(0);
    //joy2 = new Joystick(1);
	//	m_oi = new OI();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
   // m_autonomousCommand = m_chooser.getSelected();
   //NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
   
  }

  @Override
  public void teleopInit() {
    

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //if (m_autonomousCommand != null) {
     // m_autonomousCommand.cancel();
    //}
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    
    //Robot.drivebase.speedArcadeDrive(-Robot.joy.getRawAxis(1), Robot.joy.getRawAxis(4));
   // Robot.drivebase.speedArcadeDrive(Robot.joy.getRawAxis(1), Robot.joy.getRawAxis(4));
    //Button autopick_ball = new JoystickButton(Robot.joy, 3);
    //autopick_ball.whenPressed();
    // autopick_ball.whenPressed(new Spin_Until_SeeTarget());

    
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
     Button adjust = new JoystickButton(Robot.joy, 2);
    //autopick_ball.whenPressed();
    adjust.whenPressed(new Adjust_After_SeeTarget());

    Button distance_adjust = new JoystickButton(Robot.joy, 4);
    //autopick_ball.whenPressed();
    distance_adjust.whenPressed(new Auto_Getclose_ReleaseHatch());

      if(Robot.joy.getRawButton(6)){
        table.getEntry("ledMode").setNumber(1);
      }

      if(Robot.joy.getRawButton(5)){
        table.getEntry("ledMode").setNumber(0);
      }
      
      
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
