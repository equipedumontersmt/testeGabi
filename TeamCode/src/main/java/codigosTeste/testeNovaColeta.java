package codigosTeste;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "testeNovaColeta", group = "Robot")
@Config
public class testeNovaColeta extends LinearOpMode {

    DcMotor rodaUm;
    DcMotor rodaDois;

    DcMotor direitaTras;
    DcMotor direitaFrente;
    DcMotor esquerdaFrente;
    DcMotor esquerdaTras;

    CRServo heliceUm;
    CRServo heliceDois;
    CRServo heliceTres;

    public static double power = 1;
    public static double powerDois = 0.3;
    public static double powerTres = 1;

    @Override
    public void runOpMode() {

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras");
        direitaFrente = hardwareMap.get(DcMotor.class, "direitaFrente");
        esquerdaFrente = hardwareMap.get(DcMotor.class, "esquerdaFrente");
        esquerdaTras= hardwareMap.get(DcMotor.class, "esquerdaTras");

        direitaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direitaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        esquerdaFrente.setDirection(DcMotorSimple.Direction.REVERSE);
        direitaFrente.setDirection(DcMotorSimple.Direction.REVERSE);
        esquerdaTras.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaTras.setDirection(DcMotorSimple.Direction.REVERSE);

        rodaUm = hardwareMap.get(DcMotor.class, "rodaUm");
        rodaDois = hardwareMap.get(DcMotor.class, "rodaDois");

        heliceUm = hardwareMap.get(CRServo.class, "heliceUm");
        heliceDois = hardwareMap.get(CRServo.class, "heliceDois");
        heliceTres = hardwareMap.get(CRServo.class, "heliceTres");

        rodaUm.setDirection(DcMotorSimple.Direction.FORWARD);
        rodaDois.setDirection(DcMotorSimple.Direction.REVERSE);

        heliceUm.setDirection(CRServo.Direction.FORWARD);
        heliceDois.setDirection(CRServo.Direction.REVERSE);
        heliceTres.setDirection(CRServo.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {

            direitaTras.setPower(gamepad1.left_stick_y + (gamepad1.left_stick_x) + (-gamepad1.right_trigger) + (gamepad1.left_trigger));
            direitaFrente.setPower(gamepad1.left_stick_y + (gamepad1.left_stick_x) + (gamepad1.right_trigger) + (-gamepad1.left_trigger));
            esquerdaFrente.setPower(gamepad1.left_stick_y + (-gamepad1.left_stick_x) + (-gamepad1.right_trigger) + (gamepad1.left_trigger));
            esquerdaTras.setPower(gamepad1.left_stick_y + (-gamepad1.left_stick_x) + (gamepad1.right_trigger) + (-gamepad1.left_trigger));

            if (gamepad1.y) {
                rodaUm.setPower(-1);
                rodaDois.setPower(1);
            }else {
                rodaUm.setPower(0);
                rodaDois.setPower(0);
            }

            if (gamepad1.dpad_down) {
                heliceUm.setPower(power);
            } else if (gamepad1.dpad_right) {
                heliceDois.setPower(powerDois);
            } else if (gamepad1.dpad_up) {
                heliceTres.setPower(powerTres);
            } else if (gamepad1.a) {
                heliceUm.setPower(1);
                heliceDois.setPower(1);
                heliceTres.setPower(1);
            } else {
                heliceUm.setPower(0);
                heliceDois.setPower(0);
                heliceTres.setPower(0);
            }

        }
    }
}
