package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class testegabioficial extends LinearOpMode {

    DcMotor direitaTras;
    DcMotor direitaFrente;
    DcMotor esquerdaFrente;
    DcMotor esquerdaTras;
    public static int poder = 1;

    @Override
    public void runOpMode() {

        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras"); //robertoAugusto
        direitaFrente = hardwareMap.get(DcMotor.class, "direitaFrente"); //pedrodivo
        esquerdaFrente = hardwareMap.get(DcMotor.class, "esquerdaFrente"); //gabidiva
        esquerdaTras = hardwareMap.get(DcMotor.class, "esquerdaTras");  //felipaoooo

        direitaFrente.setDirection(DcMotorSimple.Direction.REVERSE);

        direitaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direitaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()) {


            direitaTras.setPower(gamepad1.left_stick_y);
            direitaFrente.setPower(gamepad1.left_stick_y);
            esquerdaFrente.setPower(gamepad1.left_stick_y);
            esquerdaTras.setPower(gamepad1.left_stick_y);

            direitaTras.setPower(-gamepad1.left_stick_x);
            direitaFrente.setPower(-gamepad1.left_stick_x);
            esquerdaFrente.setPower(gamepad1.left_stick_x);
            esquerdaTras.setPower(gamepad1.left_stick_x);    //mexer na virada do robo

            telemetry.addData("potencia", direitaTras.getPower());
            telemetry.update();
        }
    }
}
