/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveBase extends Subsystem{

    private WPI_TalonSRX frontLeft, frontRight;
    public WPI_VictorSPX rearLeft, rearRight;

    public DifferentialDrive m_drive ; 
    //public Solenoid driveSpeed;

    boolean hasntHappened = true;

    public final int TURN_DEGREE = 275;

    public int direction = 1;

    public DriveBase () {
        frontLeft = new WPI_TalonSRX(10);
        frontRight = new WPI_TalonSRX(12);
        rearLeft = new WPI_VictorSPX(11);
        rearRight = new WPI_VictorSPX(13);
        
        

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        frontRight.setInverted(true);
        rearRight.setInverted(true);
        //driveSpeed = new Solenoid(2);
        frontLeft.config_kF(0, 0.2, 10);
        frontLeft.config_kP(0, 0.5, 10);
        frontLeft.config_kI(0, 0, 10);
        frontLeft.config_kD(0, 0.0, 10);
        frontRight.config_kF(0, 0.2, 10);

        frontRight.config_kP(0, 0.5, 10);

        frontRight.config_kI(0, 0, 10);

        frontRight.config_kD(0, 0.0, 10);
    
        rearLeft.follow(frontLeft);

        rearRight.follow(frontRight);

        frontLeft.configMotionCruiseVelocity(8000, 0); //Motion Magic

        frontLeft.configMotionAcceleration(8000, 0);

        frontRight.configMotionCruiseVelocity(8100, 0); //Motion Magic

        frontRight.configMotionAcceleration(8100, 0);

        frontLeft.setSelectedSensorPosition(0, 0, 10);

        frontRight.setSelectedSensorPosition(0, 0, 10);
        SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeft, rearLeft);
        m_left.setInverted(true);        
     SpeedControllerGroup m_right = new SpeedControllerGroup(frontRight, rearRight);
     m_drive = new DifferentialDrive(m_left , m_right);

        this.setBraking(NeutralMode.Brake);

    }

   

    public void arcadeDrive(double forward, double turn) {

        forward = forward * direction;

        double leftMotorOutput;

        double maxInputL = Math.copySign(Math.max(Math.abs(forward), Math.abs(turn)), forward);

       

        //find percent -1 to 1

        if (forward >= 0.0) {

            // First quadrant, else second quadrant

            if (turn >= 0.0) {

              leftMotorOutput = maxInputL;

            } else {

              leftMotorOutput = forward + turn;

            }

          } else {

            // Third quadrant, else fourth quadrant

            if (turn >= 0.0) {

              leftMotorOutput = forward + turn;

            } else {

              leftMotorOutput = maxInputL;

            }

          }

          frontLeft.set(ControlMode.PercentOutput, leftMotorOutput);

        double rightMotorOutput;

        double maxInputR = Math.copySign(Math.max(Math.abs(forward), Math.abs(turn)), forward);
        //find percent -1 to 1

        if (forward >= 0.0) {

            // First quadrant, else second quadrant

            if (turn >= 0.0) {

              rightMotorOutput = forward - turn;

            } else {

              rightMotorOutput = maxInputR;

            }

          } else {

            // Third quadrant, else fourth quadrant

            if (turn >= 0.0) {

              rightMotorOutput = maxInputR;

            } else {

              rightMotorOutput = forward - turn;

            }

          }

          frontRight.set(ControlMode.PercentOutput, rightMotorOutput);
    }
    
    public void speedArcadeDrive(double iForward, double iTurn){
        m_drive.arcadeDrive(iForward, iTurn);
    }
    public void stop() {

        frontLeft.set(ControlMode.Velocity, 0);

        frontRight.set(ControlMode.Velocity, 0);

    }
    protected void initDefaultCommand() {
    }

    public void setBraking(NeutralMode brake) {

        frontLeft.setNeutralMode(brake);

        frontRight.setNeutralMode(brake);

        rearLeft.setNeutralMode(brake);

        rearRight.setNeutralMode(brake);

    }

   

    public void setGearing(boolean high){

      //  driveSpeed.set(high);

    }

   

    public void driveDistance(double distanceL, double distanceR) {

       

        frontLeft.setSelectedSensorPosition(0, 0, 10);

        frontRight.setSelectedSensorPosition(0, 0, 10);

        frontLeft.set(ControlMode.MotionMagic, distanceL);

        frontRight.set(ControlMode.MotionMagic, distanceR);

        }

   

        public void turn (double turnVal){
            frontLeft.set(ControlMode.PercentOutput,turnVal);

            frontRight.set(ControlMode.PercentOutput,-turnVal);
        }
    /* public void turn(int degrees){

        frontLeft.setSelectedSensorPosition(0, 0, 10);

        frontRight.setSelectedSensorPosition(0, 0, 10);

        frontLeft.configMotionCruiseVelocity(15000, 0); //Motion Magic

        frontLeft.configMotionAcceleration(15000, 0);

               

        frontRight.configMotionCruiseVelocity(15000, 0); //Motion Magic

        frontRight.configMotionAcceleration(15000, 0);

        if(degrees >= 0){

            frontLeft.set(ControlMode.MotionMagic, -degrees * TURN_DEGREE);

            frontRight.set(ControlMode.MotionMagic, degrees * TURN_DEGREE);

            //rearLeft.set(ControlMode.Follower, 10);

            //rearRight.set(ControlMode.Follower, 12);

        }

        else {

            degrees = Math.abs(degrees);

            frontLeft.set(ControlMode.MotionMagic, degrees * TURN_DEGREE);

            frontRight.set(ControlMode.MotionMagic, -degrees * TURN_DEGREE);

            //rearLeft.set(ControlMode.Follower, 10);

            //rearRight.set(ControlMode.Follower, 12);

        }

       

    }
*/
   

    public void setDefaultConfig(){

        frontLeft.configMotionCruiseVelocity(8000, 0); //Motion Magic

        frontLeft.configMotionAcceleration(8000, 0);

               

        frontRight.configMotionCruiseVelocity(8100, 0); //Motion Magic

        frontRight.configMotionAcceleration(8100, 0);

    }

   

    public void changeDirection(){

        direction = direction * -1;

    }

   

    public double getEncoderPositionL() {

        return frontLeft.getSelectedSensorPosition(0);

    }

   

    public double getEncoderPositionR() {

        return frontRight.getSelectedSensorPosition(0);

    }

   

    public void zeroSensor(){

        System.out.println("ZERO THE SENSOR");

        frontLeft.setSelectedSensorPosition(0, 0, 10);

        frontRight.setSelectedSensorPosition(0, 0, 10);

    }

   
/*
    public void autopickupball() {

               NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
               NetworkTableEntry tx = table.getEntry("tx");
               NetworkTableEntry ty = table.getEntry("ty");
               NetworkTableEntry ta = table.getEntry("ta");
               NetworkTableEntry tv = table.getEntry("tv");

               //read values periodically

               double x = tx.getDouble(0.0);
               double y = ty.getDouble(0.0);
               double area = ta.getDouble(0.0);
               double isSeetarget = tv.getDouble(0.0);
               SmartDashboard.putNumber("LimelightX", x);
              SmartDashboard.putNumber("LimelightY", y);
               SmartDashboard.putNumber("LimelightArea", area);
               double steering_adjust = 0.0f;

               if (isSeetarget == 0.0f)
               {
                       // We don't see the target, seek for the target by spinning in place at a safe speed.                            
                      frontRight.set(ControlMode.PercentOutput, 1);
            rearRight.set(ControlMode.PercentOutput, 1 );
            frontLeft.set(ControlMode.PercentOutput, -1);
            rearLeft.set(ControlMode.PercentOutput, -1 );
               }
               else
               {
                       // We do see the target, execute aiming code
                       double heading_error = x;
                      double Kp = -1.0f;
                       steering_adjust = Kp * heading_error;
               }
               double KpDistance = -0.1f;
                frontRight.set(ControlMode.PercentOutput, steering_adjust);
         rearRight.set(ControlMode.PercentOutput, steering_adjust );
         frontLeft.set(ControlMode.PercentOutput, -steering_adjust);
         rearLeft.set(ControlMode.PercentOutput, -steering_adjust );

          double driving_adjust = KpDistance * y;
         frontRight.set(ControlMode.PercentOutput, driving_adjust);
         rearRight.set(ControlMode.PercentOutput, driving_adjust );
         frontLeft.set(ControlMode.PercentOutput, driving_adjust);
         rearLeft.set(ControlMode.PercentOutput, driving_adjust );
    }

   */

    public void periodic(){

        SmartDashboard.putNumber("Left Position: ", frontLeft.getSelectedSensorPosition(0));

        SmartDashboard.putNumber("Left Velocity: ", frontLeft.getSelectedSensorVelocity(0));

        SmartDashboard.putNumber("Right Position: ", frontRight.getSelectedSensorPosition(0));

        SmartDashboard.putNumber("Right Velocity: ", frontRight.getSelectedSensorVelocity(0));

       

        SmartDashboard.putNumber("DBError: ", frontLeft.getSelectedSensorPosition(0) - frontRight.getSelectedSensorPosition(0));

    }

 

   

}