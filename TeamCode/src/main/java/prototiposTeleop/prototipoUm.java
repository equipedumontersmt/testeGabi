package prototiposTeleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "prototipoUm", group = "Robot")
public class prototipoUm extends LinearOpMode {

    DcMotor direitaTras;
    DcMotor direitaFrente;
    DcMotor esquerdaFrente;
    DcMotor esquerdaTras;

    DcMotor rodaUm;
    DcMotor rodaDois;

    CRServo servoUm;
    CRServo servoDois;
    CRServo servoTres;

    DcMotor encoderLeft;
    DcMotor encoderRight;
    DcMotor encoderAux;

    @Override
    public void runOpMode() {

        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras");
        direitaFrente = hardwareMap.get(DcMotor.class, "direitaFrente");
        esquerdaFrente = hardwareMap.get(DcMotor.class, "esquerdaFrente");
        esquerdaTras= hardwareMap.get(DcMotor.class, "esquerdaTras");

        rodaUm = hardwareMap.get(DcMotor.class, "rodaUm");
        rodaDois = hardwareMap.get(DcMotor.class, "rodaDois");

        encoderLeft = esquerdaTras;
        encoderRight = direitaFrente;
        encoderAux = direitaTras;

        servoUm = hardwareMap.get(CRServo.class, "servoUm");
        servoDois = hardwareMap.get(CRServo.class, "servoDois");
        servoTres = hardwareMap.get(CRServo.class, "servoTres");

        direitaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direitaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        esquerdaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        esquerdaTras.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaTras.setDirection(DcMotorSimple.Direction.FORWARD);

        rodaUm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rodaDois.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        servoUm.setDirection(DcMotorSimple.Direction.REVERSE);
        servoDois.setDirection(DcMotorSimple.Direction.REVERSE);
        servoTres.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {

            direitaTras.setPower(-gamepad1.left_stick_y + (-gamepad1.left_stick_x) + (gamepad1.right_trigger) + (-gamepad1.left_trigger));
            direitaFrente.setPower(gamepad1.left_stick_y + (gamepad1.left_stick_x) + (gamepad1.right_trigger) + (-gamepad1.left_trigger));
            esquerdaFrente.setPower(gamepad1.left_stick_y + (-gamepad1.left_stick_x) + (-gamepad1.right_trigger) + (gamepad1.left_trigger));
            esquerdaTras.setPower(gamepad1.left_stick_y + (-gamepad1.left_stick_x) + (gamepad1.right_trigger) + (-gamepad1.left_trigger));

            telemetry.addData("encoder", encoderLeft.getCurrentPosition());
            telemetry.addData("encoder", encoderRight.getCurrentPosition());
            telemetry.addData("encoder", encoderAux.getCurrentPosition());
            telemetry.update();

            if (gamepad1.a) {
                servoUm.setPower(1);
                servoDois.setPower(1);
                servoTres.setPower(1);

            } else {
                servoUm.setPower(0);
                servoDois.setPower(0);
                servoTres.setPower(0);
            }
            if (gamepad1.x) {
                rodaUm.setPower(1);
                rodaDois.setPower(1);

            } else {
                rodaUm.setPower(0);
                rodaDois.setPower(0);
            }
        }
    }
}
