package pl.aleksl.sensorsportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.aleksl.sensorsportal.model.ApiResponse;
import pl.aleksl.sensorsportal.model.SensorTempHumPress;
import pl.aleksl.sensorsportal.services.SensorTempHumPressService;

import java.util.List;

@RestController
public class SensorTempHumPressController {

    @Autowired
    SensorTempHumPressService sensorTempHumPressService;


    @Value("${security-app-key}")
    private String securityAppKey;

    @GetMapping("/sensorTempHumPress/list")
    public ApiResponse<List<SensorTempHumPress>> listSensorTempHumPress() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor temp hum press list fetched successfully.", sensorTempHumPressService.findAll());
    }

    @GetMapping("/sensorTempHumPress/add")
    public ApiResponse getSensorTempHumPressWithRequestParam(
            @RequestParam(value = "apiKey", required = true) String apiKey,
            @RequestParam(value = "sensorId", required = true) Integer sensorId,
            @RequestParam(value = "temp1", required = false) Integer temp1,
            @RequestParam(value = "temp2", required = false) Integer temp2,
            @RequestParam(value = "humidity", required = false) Integer humidity,
            @RequestParam(value = "pressure", required = false) Integer pressure) {
        if (apiKey != null && !apiKey.equals(securityAppKey)) {
            return new ApiResponse<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), "Wrong apikey.", "Failure");
        }
        if (temp1 == null) temp1 = 0;
        if (temp2 == null) temp2 = 0;
        if (humidity == null) humidity = 0;
        if (pressure == null) pressure = 0;
        SensorTempHumPress sensorTempHumPress = new SensorTempHumPress();
        sensorTempHumPress.setSensor_id(sensorId);
        sensorTempHumPress.setTemp1(temp1);
        sensorTempHumPress.setTemp2(temp2);
        sensorTempHumPress.setHumidity(humidity);
        sensorTempHumPress.setPressure(pressure);
        sensorTempHumPressService.save(sensorTempHumPress);
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor values added successfully.", "Added");
    }

    @GetMapping("/sensorTempHumPress/list/{sensorId}")
    public ApiResponse<List<SensorTempHumPress>> listSensorTempHumPressBySensorId(@PathVariable int sensorId
    ) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor temp hum press list fetched successfully.", sensorTempHumPressService.getBySensorId(sensorId));
    }

    @GetMapping("/sensorTempHumPress/last/{sensorId}")
    public ApiResponse<SensorTempHumPress> getLastSensorTempHumPressInfo(@PathVariable int sensorId
    ) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor temp hum press list fetched successfully.", sensorTempHumPressService.getBySensorIdLastMeasurement(sensorId));
    }

    @GetMapping("/sensorTempHumPress/last/{sensorId}/{count}")
    public ApiResponse<List<SensorTempHumPress>> getLastTwentySensorTempHumPressInfo(@PathVariable int sensorId, @PathVariable int count
    ) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor temp hum press list fetched successfully.", sensorTempHumPressService.getBySensorIdLastMeasurements(sensorId, count));
    }
}
