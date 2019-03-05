package pl.aleksl.sensorsportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.aleksl.sensorsportal.model.ApiResponse;
import pl.aleksl.sensorsportal.model.SensorDust;
import pl.aleksl.sensorsportal.services.SensorDustService;

import java.util.List;

@RestController
public class SensorDustController {

    @Autowired
    SensorDustService sensorDustService;


    @Value( "${security-app-key}" )
    private String securityAppKey;

    @GetMapping("/sensorDust/list")
    public ApiResponse<List<SensorDust>> listSensorDust() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor dust list fetched successfully.", sensorDustService.findAll());
    }

    @GetMapping("/sensorDust/add")
    public ApiResponse getSensorDustWithRequestParam(
            @RequestParam(value = "apiKey", required = true) String apiKey,
            @RequestParam(value = "sensorId", required = true) Integer sensorId,
            @RequestParam(value = "pm1", required = false) Integer pm1,
            @RequestParam(value = "pm25", required = false) Integer pm25,
            @RequestParam(value = "pm10", required = false) Integer pm10) {
        if(apiKey != null && !apiKey.equals(securityAppKey)){
            return new ApiResponse<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(), "Wrong apikey.", "Failure");
        }
        if (pm1 == null) pm1 = 0;
        if (pm25 == null) pm25 = 0;
        if (pm10 == null) pm10 = 0;
        SensorDust sensorsDust = new SensorDust();
        sensorsDust.setSensor_id(sensorId);
        sensorsDust.setPm1(pm1);
        sensorsDust.setPm25(pm25);
        sensorsDust.setPm10(pm10);
        sensorDustService.save(sensorsDust);
        System.out.println(securityAppKey);
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor values added successfully.", "Added");
    }

    @GetMapping("/sensorDust/list/{sensorId}")
    public ApiResponse<List<SensorDust>> listSensorDustBySensorId(@PathVariable int sensorId
    ) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor dust list fetched successfully.", sensorDustService.getBySensorId(sensorId));
    }

    @GetMapping("/sensorDust/last/{sensorId}")
    public ApiResponse<SensorDust> getLastSensorDustInfo(@PathVariable int sensorId
    ) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor dust list fetched successfully.", sensorDustService.getBySensorIdLastMeasurement(sensorId));
    }

    @GetMapping("/sensorDust/last/{sensorId}/{count}")
    public ApiResponse<List<SensorDust>> getLastTwentySensorDustInfo(@PathVariable int sensorId, @PathVariable int count
    ) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor dust list fetched successfully.", sensorDustService.getBySensorIdLastMeasurements(sensorId, count));
    }
}
