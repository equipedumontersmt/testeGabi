package codigosTeste;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "movimento", group = "Robot")
public class movimento extends LinearOpMode {

    DcMotor direitaTras;
    DcMotor direitaFrente;
    DcMotor esquerdaFrente;
    DcMotor esquerdaTras;

    @Override
    public void runOpMode() {

        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras");
        direitaFrente = hardwareMap.get(DcMotor.class, "direitaFrente");
        esquerdaFrente = hardwareMap.get(DcMotor.class, "esquerdaFrente");
        esquerdaTras= hardwareMap.get(DcMotor.class, "esquerdaTras");

        direitaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direitaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        esquerdaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaFrente.setDirection(DcMotorSimple.Direction.REVERSE);
        esquerdaTras.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaTras.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {

            direitaTras.setPower(gamepad1.left_stick_y);
            direitaFrente.setPower(gamepad1.left_stick_y);
            esquerdaFrente.setPower(gamepad1.left_stick_y);
            esquerdaTras.setPower(gamepad1.left_stick_y);

        }
    }
}
