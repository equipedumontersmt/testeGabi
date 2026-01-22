package autonomos;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous
public class semOdometria extends LinearOpMode {

    DcMotor direitaFrente = null;
    DcMotor direitaTras = null;
    DcMotor esquerdaFrente = null;
    DcMotor esquerdaTras = null;

    private ElapsedTime runtime = new ElapsedTime();

    static final double FORWARD_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    private static final boolean USE_WEBCAM = true;

    private AprilTagProcessor aprilTag;

    private VisionPortal visionPortal;

    @Override
    public void runOpMode() {

        direitaFrente = hardwareMap.get(DcMotor.class, "direitaFrente");
        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras");
        esquerdaFrente = hardwareMap.get(DcMotor.class, "esquerdaFrente");
        esquerdaTras = hardwareMap.get(DcMotor.class, "esquerdaTras");

        direitaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaTras.setDirection(DcMotorSimple.Direction.FORWARD);
        esquerdaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        esquerdaTras.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        initAprilTag();

        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch START to start OpMode");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {

            direitaFrente.setPower(-FORWARD_SPEED);
            direitaTras.setPower(-FORWARD_SPEED);
            esquerdaFrente.setPower(-FORWARD_SPEED);
            esquerdaTras.setPower(-FORWARD_SPEED);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.7)) {

                telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
                telemetry.update();

            }

            direitaFrente.setPower(TURN_SPEED);
            direitaTras.setPower(TURN_SPEED);
            esquerdaFrente.setPower(-TURN_SPEED);
            esquerdaTras.setPower(-TURN_SPEED);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.4)) {

                telemetry.addData("Path", "Leg 2: %4.1f S Elapsed", runtime.seconds());
                telemetry.update();
            }

            direitaFrente.setPower(-FORWARD_SPEED);
            direitaTras.setPower(-FORWARD_SPEED);
            esquerdaFrente.setPower(-FORWARD_SPEED);
            esquerdaTras.setPower(-FORWARD_SPEED);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.7)) {

                telemetry.addData("Path", "Leg 3: %4.1f S Elapsed", runtime.seconds());
                telemetry.update();
            }

            direitaFrente.setPower(0);
            direitaTras.setPower(0);
            esquerdaFrente.setPower(0);
            esquerdaTras.setPower(0);
            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 0.5)) {
                telemetry.addData("Path", "Leg 4: %4.1f S Elapsed", runtime.seconds());
                telemetry.update();
            }



            while (opModeIsActive()) {

                telemetryApriltag();

                telemetry.update();

                if ((gamepad1.dpad_down)) {
                    visionPortal.stopStreaming();
                } else if (gamepad1.dpad_up) {
                    visionPortal.resumeStreaming();
                }

                sleep(20);

            }

        }

        visionPortal.close();

    }
    private void initAprilTag() {
        aprilTag = new AprilTagProcessor.Builder()
                .setDrawAxes(false)
                .setDrawCubeProjection(false)
                .setDrawTagOutline(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                .setLensIntrinsics(578.272, 578.272, 402.145, 221.506)

                .build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "camera"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        builder.setCameraResolution(new Size(640, 480));

        builder.enableLiveView(true);

        builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        builder.setAutoStopLiveView(false);

        builder.addProcessor(aprilTag);

        visionPortal = builder.build();

        visionPortal.setProcessorEnabled(aprilTag, true);

    }

    private void telemetryApriltag() {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        for (AprilTagDetection detection : currentDetections) {
            telemetry.addData("Apriltag ID", detection.id);
            int a = detection.id;
            if (a == 21) {
                telemetry.addLine("PPG");

                direitaFrente.setPower(TURN_SPEED);
                direitaTras.setPower(TURN_SPEED);
                esquerdaFrente.setPower(-TURN_SPEED);
                esquerdaTras.setPower(-TURN_SPEED);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.4)) {
                    telemetry.addData("Path", "Leg 4: %4.1f S Elapsed", runtime.seconds());
                    telemetry.update();
                }

                direitaFrente.setPower(FORWARD_SPEED);
                direitaTras.setPower(FORWARD_SPEED);
                esquerdaFrente.setPower(FORWARD_SPEED);
                esquerdaTras.setPower(FORWARD_SPEED);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.9)) {
                    telemetry.addData("Path", "Leg 5: %4.1f S Elapsed", runtime.seconds());
                    telemetry.update();
                }

                //coleta
                //ir para área de lançamento
                //lançar
                //pegar fileira 2
                //ir para área de lançamento
                //lançar
                //pegar fileira 3
                //ir para área de lançamento
                //lançar

                direitaFrente.setPower(0);
                direitaTras.setPower(0);
                esquerdaFrente.setPower(0);
                esquerdaTras.setPower(0);
                runtime.reset();


            } else if (a == 22) {
                telemetry.addLine("PGP");

                direitaFrente.setPower(TURN_SPEED);
                direitaTras.setPower(TURN_SPEED);
                esquerdaFrente.setPower(-TURN_SPEED);
                esquerdaTras.setPower(-TURN_SPEED);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.7)) {

                    telemetry.addData("Path", "Leg 4: %4.1f S Elapsed", runtime.seconds());
                    telemetry.update();
                }

                direitaFrente.setPower(FORWARD_SPEED);
                direitaTras.setPower(FORWARD_SPEED);
                esquerdaFrente.setPower(FORWARD_SPEED);
                esquerdaTras.setPower(FORWARD_SPEED);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 1.6)) {

                    telemetry.addData("Path", "Leg 5: %4.1f S Elapsed", runtime.seconds());
                    telemetry.update();
                }
                //coletar

                direitaFrente.setPower(-TURN_SPEED);
                direitaTras.setPower(-TURN_SPEED);
                esquerdaFrente.setPower(TURN_SPEED);
                esquerdaTras.setPower(TURN_SPEED);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.4)) {

                    telemetry.addData("Path", "Leg 4: %4.1f S Elapsed", runtime.seconds());
                    telemetry.update();
                }

                //coletar
                //ir para zona de lançamento
                //lançar
                //coletar fileira 1
                //ir para zona de lançamento
                //lançar
                //coletar fileira 3
                //ir para zona de lançamento
                //lançar

                direitaFrente.setPower(0);
                direitaTras.setPower(0);
                esquerdaFrente.setPower(0);
                esquerdaTras.setPower(0);
                runtime.reset();

            } else if (a == 23) {
                telemetry.addLine("GPP");

                direitaFrente.setPower(TURN_SPEED);
                direitaTras.setPower(TURN_SPEED);
                esquerdaFrente.setPower(TURN_SPEED);
                esquerdaTras.setPower(TURN_SPEED);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.3)) {

                    telemetry.addData("Path", "Leg 4: %4.1f S Elapsed ", runtime.seconds());
                    telemetry.update();
                }

                direitaFrente.setPower(FORWARD_SPEED);
                direitaTras.setPower(FORWARD_SPEED);
                esquerdaFrente.setPower(FORWARD_SPEED);
                esquerdaTras.setPower(FORWARD_SPEED);
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 0.8)) {

                    telemetry.addData("Path", "Leg 5: %4.1f S Elapsed", runtime.seconds());
                    telemetry.update();
                }

                direitaFrente.setPower(0);
                direitaTras.setPower(0);
                esquerdaFrente.setPower(0);
                esquerdaTras.setPower(0);
                runtime.reset();
            }

        }

        telemetry.update();

    }
    }

                /* direitaFrente.setPower(0);
            direitaTras.setPower(0);
            esquerdaFrente.setPower(0);
            esquerdaTras.setPower(0);

            telemetry.addData("Path", "Complete");
            telemetry.update();
            sleep(1000);*/