package pedroPathing;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.DriveEncoderConstants;

import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    public static Follower follower = Constants.createFollower(hardwareMap);

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(7)
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(true)
            .useSecondaryDrivePIDF(true)
            .lateralZeroPowerAcceleration(0) // TODO colocar valor
            .forwardZeroPowerAcceleration(0); //TODO colocar valor

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .driveEncoderLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }

    public static DriveEncoderConstants localizerConstants = new DriveEncoderConstants()
            .rightFrontMotorName("direitaFrente")
            .rightRearMotorName("direitaTras")
            .leftRearMotorName("esquerdaTras")
            .leftFrontMotorName("direitaFrente")
            .leftFrontEncoderDirection(Encoder.FORWARD)
            .leftRearEncoderDirection(Encoder.FORWARD)
            .rightFrontEncoderDirection(Encoder.FORWARD)
            .rightRearEncoderDirection(Encoder.FORWARD)
            .forwardTicksToInches(0)//TODO colocar valor
            .strafeTicksToInches(0)//TODO colocar valor
            .turnTicksToInches(0) //TODO colocar valor
            .robotWidth(14.5669) //largura rodas esquerda e direita TODO medir
            .robotLength(9.05512); //comprimento rodas frente e trás TODO medir

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("direitaFrente")
            .rightRearMotorName("direitaTras")
            .leftRearMotorName("esquerdaTras")
            .leftFrontMotorName("esquerdaFrente")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .yVelocity(0) //TODO colocar valor
            .xVelocity(0); //TODO colocar valor



}