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

    CRServo heliceUm;
    CRServo heliceDois;
    CRServo heliceTres;

    public static double power = 0.5;
    public static double powerDois = 0.18;
    public static double powerTres = 0.5;

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

            rodaUm.setPower(-1);
            rodaDois.setPower(1);

            if (gamepad1.a) {
                heliceUm.setPower(power);
                heliceDois.setPower(powerDois);
                heliceTres.setPower(powerTres);

            } else {
                heliceUm.setPower(0.0);
                heliceDois.setPower(0.0);
                heliceTres.setPower(0.0);
            }

        }
    }
}
