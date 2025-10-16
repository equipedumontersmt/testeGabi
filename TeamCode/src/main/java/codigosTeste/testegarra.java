package codigosTeste;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class testegarra extends LinearOpMode {

    CRServo servoUm;
    CRServo servoDois;
    CRServo servoTres;

    DcMotor rodaUm;
    DcMotor rodaDois;
    public static int poder = 1;

    @Override
    public void runOpMode() {

        rodaUm = hardwareMap.get(DcMotor.class, "rodaUm");
        rodaDois = hardwareMap.get(DcMotor.class, "rodaDois");

        servoUm = hardwareMap.get(CRServo.class, "servoUm");
        servoDois = hardwareMap.get(CRServo.class, "servoDois");
        servoTres = hardwareMap.get(CRServo.class, "servoTres");

        rodaUm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rodaDois.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rodaUm.setDirection(DcMotorSimple.Direction.REVERSE);
        rodaDois.setDirection(DcMotorSimple.Direction.REVERSE);

        servoUm.setDirection(DcMotorSimple.Direction.FORWARD);
        servoDois.setDirection(DcMotorSimple.Direction.REVERSE);
        servoTres.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {

            rodaUm.setPower(1);
            rodaDois.setPower(1);

            if (gamepad1.a) {
                servoUm.setPower(1);
                servoDois.setPower(0.25);
                servoTres.setPower(1);
            } else {
                servoUm.setPower(0);
                servoDois.setPower(0);
                servoTres.setPower(0);
            }
        }
    }
}
