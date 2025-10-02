package codigosTeste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "servoRoda")
public class servoRoda extends LinearOpMode {

    CRServo rolete;

    @Override
    public void runOpMode(){

        rolete = hardwareMap.get(CRServo.class, "Rolete");

        waitForStart();
        while (opModeIsActive()) {

            rolete.setPower(1);
        }
    }
}
