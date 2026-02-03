package prototiposTeleop;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp
public class prototipoTres extends LinearOpMode {

    private static final boolean USE_WEBCAM = true;

    private AprilTagProcessor aprilTag;

    private VisionPortal visionPortal;

    DcMotor direitaTras;
    DcMotor direitaFrente;
    DcMotor esquerdaFrente;
    DcMotor esquerdaTras;

    DcMotor motorDeposito;
    DcMotor motorColeta;

    CRServo servoUm;
    CRServo servoDois;

    @Override
    public void runOpMode() {

        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras");
        direitaFrente = hardwareMap.get(DcMotor.class, "direitaFrente");
        esquerdaFrente = hardwareMap.get(DcMotor.class, "esquerdaFrente");
        esquerdaTras= hardwareMap.get(DcMotor.class, "esquerdaTras");

        motorDeposito = hardwareMap.get(DcMotor.class,"motorDeposito");
        motorColeta = hardwareMap.get(DcMotor.class,"motorColeta");

        servoUm = hardwareMap.get(CRServo.class,"servoUm");
        servoDois = hardwareMap.get(CRServo.class,"servoDois");

        direitaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direitaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorDeposito.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorColeta.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        esquerdaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        esquerdaTras.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaTras.setDirection(DcMotorSimple.Direction.FORWARD);

        motorDeposito.setDirection(DcMotorSimple.Direction.FORWARD);
        motorColeta.setDirection(DcMotorSimple.Direction.FORWARD);


        initAprilTag();

        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch START to start OpMode");
        telemetry.update();

        waitForStart();


            while (opModeIsActive()) {

                direitaTras.setPower(-gamepad1.left_stick_y + (-gamepad1.left_stick_x) + (gamepad1.right_trigger) + (-gamepad1.left_trigger));
                direitaFrente.setPower(-gamepad1.left_stick_y + (-gamepad1.left_stick_x) + (-gamepad1.right_trigger) + (gamepad1.left_trigger));
                esquerdaFrente.setPower(-gamepad1.left_stick_y + (gamepad1.left_stick_x) + (gamepad1.right_trigger) + (-gamepad1.left_trigger));
                esquerdaTras.setPower(-gamepad1.left_stick_y + (gamepad1.left_stick_x) + (-gamepad1.right_trigger) + (gamepad1.left_trigger));

                if (gamepad1.b) {
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
                telemetry.addLine("ta bom");

            } else if (a == 22) {
                telemetry.addLine("viu que ta funcionando");

            } else if (a == 23) {
                telemetry.addLine("ihul ta certo o");

            } else if (a == 20) {
                telemetry.addLine("alinhar para lançamento");
            }

        }

        telemetry.update();
    }
}
