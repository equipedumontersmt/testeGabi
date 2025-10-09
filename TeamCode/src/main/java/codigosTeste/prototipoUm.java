package codigosTeste;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class prototipoUm extends LinearOpMode {

    DcMotor direitaTras;
    DcMotor direitaFrente;
    DcMotor esquerdaFrente;
    DcMotor esquerdaTras;

    CRServo servoUm;
    CRServo servoDois;
    CRServo servoTres;
    CRServo servoQuatro;

    DcMotor encoderLeft;
    DcMotor encoderRight;
    DcMotor encoderAux;

    @Override
    public void runOpMode() {

        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras");
        direitaFrente = hardwareMap.get(DcMotor.class, "direitaFrente");
        esquerdaFrente = hardwareMap.get(DcMotor.class, "esquerdaFrente");
        esquerdaTras= hardwareMap.get(DcMotor.class, "esquerdaTras");

        encoderLeft = esquerdaTras;
        encoderRight = direitaFrente;
        encoderAux = direitaTras;

        servoUm = hardwareMap.get(CRServo.class, "servoUm");
        servoDois = hardwareMap.get(CRServo.class, "servoDois");
        servoTres = hardwareMap.get(CRServo.class, "servoTres");
        servoQuatro = hardwareMap.get(CRServo.class, "servoQuatro");

        direitaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direitaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        esquerdaFrente.setDirection(DcMotorSimple.Direction.REVERSE);
        direitaFrente.setDirection(DcMotorSimple.Direction.REVERSE);
        esquerdaTras.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaTras.setDirection(DcMotorSimple.Direction.REVERSE);

        servoUm.setDirection(DcMotorSimple.Direction.REVERSE);
        servoDois.setDirection(DcMotorSimple.Direction.REVERSE);
        servoTres.setDirection(DcMotorSimple.Direction.FORWARD);
        servoQuatro.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {

            direitaTras.setPower(gamepad1.left_stick_y + (gamepad1.left_stick_x) + (-gamepad1.right_trigger) + (gamepad1.left_trigger));
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
                servoQuatro.setPower(1);
            } else {
                servoUm.setPower(0);
                servoDois.setPower(0);
                servoTres.setPower(0);
                servoQuatro.setPower(0);
            }
        }
    }
}
