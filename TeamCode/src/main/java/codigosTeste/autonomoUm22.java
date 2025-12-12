package codigosTeste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous
public class autonomoUm22 extends LinearOpMode {

    public DcMotor esquerdaFrente, direitaFrente, esquerdaTras, direitaTras;

    AprilTagDetection tagFound = null;

    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTag;

    @Override
    public void runOpMode() throws InterruptedException {

        esquerdaFrente = hardwareMap.get(DcMotor.class, "esquerdaFrente");
        direitaFrente = hardwareMap.get(DcMotor.class, "direitaFrente");
        esquerdaTras = hardwareMap.get(DcMotor.class, "esquerdaTras");
        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras");

        esquerdaFrente.setDirection(DcMotor.Direction.REVERSE);
        esquerdaTras.setDirection(DcMotor.Direction.REVERSE);
        direitaFrente.setDirection(DcMotorSimple.Direction.FORWARD);
        direitaTras.setDirection(DcMotorSimple.Direction.FORWARD);


        aprilTag = new AprilTagProcessor.Builder().build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "camera")) // Webcam Logitech
                .addProcessor(aprilTag)
                .build();

        telemetry.addLine("Inicializado. Pressione PLAY.");
        telemetry.update();

        waitForStart();
        long startTime = System.currentTimeMillis();
        long timeout = 4000; // 4 segundos tentando ler


        while (opModeIsActive() && System.currentTimeMillis() - startTime < timeout) {
            List<AprilTagDetection> detections = aprilTag.getDetections();


            if (!detections.isEmpty()) {
                tagFound = detections.get(0); // primeira tag da lista
                telemetry.addData("Tag detectada:", "ID = " + tagFound.id);
                telemetry.update();
                break;
            }

            telemetry.addLine("Nenhuma tag detectada ainda...");
            telemetry.update();

            sleep(100);
        }

        stopAll();

        if (tagFound != null && tagFound.id == 22) {
            telemetry.addLine("Tag 22 detectada! Executando sequência...");
            telemetry.update();

            moveForward(0.5, 800);
            turnRight(0.5, 40);
            moveForward(0.5, 600);
            strafeLeft(0.5, 500);

        } if (tagFound.id == 21) {
            telemetry.addLine("Tag 21 detectada! Executando sequência...");
            telemetry.update();

            moveForward(0.5, 800);
            turnRight(0.5, 40);
            moveForward(0.5, 600);
            strafeLeft(0.5, 500);

        }

    }

    private void moveForward(double power, long time) {
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
