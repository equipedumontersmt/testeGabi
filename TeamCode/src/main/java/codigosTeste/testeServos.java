package codigosTeste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class testeServos extends LinearOpMode {

    //CRServo servoUm;
    CRServo servoDois;
    CRServo servoTres;

    @Override
    public void runOpMode() {

        //servoUm = hardwareMap.get(CRServo.class,"servoUm");
        servoDois = hardwareMap.get(CRServo.class,"servoDois");
        servoTres = hardwareMap.get(CRServo.class,"servoTres");

        //servoUm.setDirection(DcMotorSimple.Direction.FORWARD);
        servoDois.setDirection(DcMotorSimple.Direction.FORWARD);
        servoTres.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                //servoUm.setPower(1);
                servoDois.setPower(1);
                servoTres.setPower(1);
                //servoUm.setDirection(DcMotorSimple.Direction.FORWARD);
                servoDois.setDirection(DcMotorSimple.Direction.FORWARD);
                servoTres.setDirection(DcMotorSimple.Direction.FORWARD);

            } else {
                //servoUm.setPower(0);
                servoDois.setPower(0);
                servoTres.setPower(0);
            }
            if ((gamepad1.b)) {
                //servoUm.setPower(1);
                servoDois.setPower(1);
                servoTres.setPower(1);
                //servoUm.setDirection(DcMotorSimple.Direction.REVERSE);
                servoDois.setDirection(DcMotorSimple.Direction.REVERSE);
                servoTres.setDirection(DcMotorSimple.Direction.REVERSE);
            }

        }
    }
}