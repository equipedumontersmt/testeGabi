package codigosTeste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;


import java.util.List;

@Autonomous
public class autonomoSemIMU extends LinearOpMode {

    DcMotor direitaFrente;
    DcMotor direitaTras;
    DcMotor esquerdaFrente;
    DcMotor esquerdaTras;

    private static final boolean USE_WEBCAM = true;
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    public void initAprilTag() {
        aprilTag = new AprilTagProcessor.Builder().build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "CAM"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        builder.addProcessor(aprilTag);
        visionPortal = builder.build();
    }

    private void telemetryAprilTag() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                if (detection.id == 21) {
                    telemetry.addData("TAG", "21");
                } else if (detection.id == 22) {
                    telemetry.addData("TAG", "22");
                } else if (detection.id == 23) {
                    telemetry.addData("TAG", "23");
                }
            } else {
                telemetry.addData("TAG", "Não");
            }
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {

        direitaFrente = hardwareMap.get(DcMotor.class,"direitaFrente");
        direitaTras = hardwareMap.get(DcMotor.class,"direitaTras");
        esquerdaFrente = hardwareMap.get(DcMotor.class,"esquerdaFrente");
        esquerdaTras = hardwareMap.get(DcMotor.class,"esquerdaTras");

        direitaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaTras.setDirection(DcMotorSimple.Direction.FORWARD);
        esquerdaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        esquerdaTras.setDirection(DcMotorSimple.Direction.FORWARD);

        direitaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direitaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        initAprilTag();

        telemetry.addData(">", "aperte START para começar");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetryAprilTag();
            telemetry.update();

            moveForward("1.0","1500");
            sleep(5000);
            turnRight(0.5, 40);


            List<AprilTagDetection> currentDetections = aprilTag.getDetections();
            telemetry.addData("# AprilTags Detected", currentDetections.size());

            for (AprilTagDetection detection : currentDetections) {
                if (detection.metadata != null) {
                    if (detection.id == 21) {
                        telemetry.addData("TAG", "21");

                        moveForward("1.0", "3000");



                    } else if (detection.id == 22) {
                        telemetry.addData("TAG", "22");
                    } else if (detection.id == 23) {
                        telemetry.addData("TAG", "23");
                    }
                } else {
                    telemetry.addData("TAG", "Não");
                }


                visionPortal.close();

            }
        }
    }
    private void moveForward(String power, String time) {
        esquerdaFrente.setPower(1);
        direitaFrente.setPower(1);
        esquerdaTras.setPower(1);
        direitaTras.setPower(1);
        sleep(60);
        stopAll();
    }

    private void strafeLeft(double power, long time) {
        esquerdaFrente.setPower(-power);
        direitaFrente.setPower(power);
        esquerdaTras.setPower(power);
        direitaTras.setPower(-power);
        sleep(time);
        stopAll();
    }

    private void turnRight(double power, long degrees) {
        long time = (long)(degrees * 10); // ajuste fino necessário
        esquerdaFrente.setPower(power);
        esquerdaTras.setPower(power);
        direitaFrente.setPower(-power);
        direitaTras.setPower(-power);
        sleep(time);
        stopAll();
    }

    private void stopAll() {
        esquerdaFrente.setPower(0);
        direitaFrente.setPower(0);
        esquerdaTras.setPower(0);
        direitaTras.setPower(0);
    }
}