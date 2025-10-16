package webcam;

import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp (name = "apriltagFollow", group = "Camera")
public class apriltagFollow extends LinearOpMode {

    @Override
    public void runOpMode() {

        AprilTagProcessor processador = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawTagID(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .build();
        VisionPortal camera = new VisionPortal.Builder()
                .addProcessor(processador)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .build();

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        waitForStart();
        while (opModeIsActive()) {

            dashboard.startCameraStream(camera, 30);

            if (gamepad1.dpad_down) {
                camera.stopStreaming();
            } else if (gamepad1.dpad_up) {
                camera.resumeStreaming();
            }

            if (!processador.getDetections().isEmpty()) {
                AprilTagDetection deteccao = processador.getDetections().get(0);

                telemetry.addData("x", deteccao.ftcPose.x);
                telemetry.addData("y", deteccao.ftcPose.y);
                telemetry.addData("z", deteccao.ftcPose.z);

                telemetry.addData("roll", deteccao.ftcPose.roll);
                telemetry.addData("pitch", deteccao.ftcPose.pitch);
                telemetry.addData("yaw", deteccao.ftcPose.yaw);

                telemetry.addData("range", deteccao.ftcPose.range);
                telemetry.addData("bearing", deteccao.ftcPose.bearing);
                telemetry.addData("elevation", deteccao.ftcPose.elevation);

                telemetry.addData("id", deteccao.id);
            } else {
                telemetry.addLine("Nada Detectado");
            }
            telemetry.update();
        }
        camera.close();
    }
}
