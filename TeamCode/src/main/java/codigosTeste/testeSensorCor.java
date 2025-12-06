package codigosTeste;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class testeSensorCor {
    NormalizedColorSensor colorSensor;

    public enum DetectedColor {
        GREEN,
        PURPLE,
        UNKNOWN
    }
    public void init (HardwareMap hwmap) {
        colorSensor = hwmap.get(NormalizedColorSensor.class, "sensorCor");
        colorSensor.setGain(1);
    }
    public DetectedColor getDetectedColor(Telemetry telemetry) {
        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        float normRed, normGreen, normBlue;
        normRed  = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        //Colocar os valores medidos na telemetria

        /*
        vermelho, verde, azul

        GREEN = vm, vd, a
        PURPLE = vm, vd, a
         */

        return DetectedColor.UNKNOWN;
    }
}