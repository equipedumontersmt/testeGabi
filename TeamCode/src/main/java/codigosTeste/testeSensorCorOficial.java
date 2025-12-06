package codigosTeste;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class testeSensorCorOficial extends OpMode {

    testeSensorCor bench = new testeSensorCor();

    @Override
    public void init() {
        bench.init(hardwareMap);

    }

    @Override
    public void loop() {
        bench.getDetectedColor(telemetry);

    }
}