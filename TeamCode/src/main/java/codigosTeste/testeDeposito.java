package codigosTeste;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "testeDeposito", group = "Robot")
@Config
public class testeDeposito extends LinearOpMode {

    DcMotor rodaUm;
    DcMotor rodaDois;

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
