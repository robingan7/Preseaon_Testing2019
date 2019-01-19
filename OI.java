/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.Stop;
import frc.robot.commands.Spin_Until_SeeTarget;
import frc.robot.commands.Adjust_After_SeeTarget;
//import frc.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
public class OI {
  public OI(){
  Button autopick_ball = new JoystickButton(Robot.joy, 3);
    //autopick_ball.whenPressed();
     //autopick_ball.whenPressed(new Spin_Until_SeeTarget());
   // autopick_ball.whenReleased(new Stop());

  }
}
