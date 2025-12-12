package codigosTeste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
@Autonomous
public class testeApriltagOficial extends OpMode {

    DcMotor direitaFrente;
    DcMotor direitaTras;
    DcMotor esquerdaFrente;
    DcMotor esquerdaTras;

    testeApriltag testeApriltag = new testeApriltag();

    @Override
    public void init() {
        testeApriltag.init(hardwareMap, telemetry);

        direitaFrente = hardwareMap.get(DcMotor.class,"direitaFrente");
        direitaTras = hardwareMap.get(DcMotor.class, "direitaTras");
        esquerdaFrente = hardwareMap.get(DcMotor.class, "esquerdaFrente");
        esquerdaTras = hardwareMap.get(DcMotor.class,"esquerdaTras");

        direitaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        direitaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaFrente.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        esquerdaTras.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {
        //update the vision portal
        testeApriltag.update();
        AprilTagDetection id21 = testeApriltag.getTagBySpecificId(21);
        testeApriltag.displayDetectionTelemetry(id21);

        AprilTagDetection id22 = testeApriltag.getTagBySpecificId(22);
        testeApriltag.displayDetectionTelemetry(id22);

        AprilTagDetection id23 = testeApriltag.getTagBySpecificId(23);
        testeApriltag.displayDetectionTelemetry(id23);

        if (testeApriltag.getDetectedTags() == id22) {


        } else if (testeApriltag.getDetectedTags() == id21) {


        } else if (testeApriltag.getDetectedTags() == id23) {


        } else if (testeApriltag.getDetectedTags() == null) {


        }

    }
}
