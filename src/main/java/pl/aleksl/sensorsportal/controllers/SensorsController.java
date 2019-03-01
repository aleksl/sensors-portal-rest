package pl.aleksl.sensorsportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.aleksl.sensorsportal.model.ApiResponse;
import pl.aleksl.sensorsportal.model.Sensors;
import pl.aleksl.sensorsportal.model.SensorsDust;
import pl.aleksl.sensorsportal.services.SensorDustService;
import pl.aleksl.sensorsportal.services.SensorsService;

import java.util.List;

@RestController
public class SensorsController {

    @Autowired
    SensorsService sensorsService;


    @GetMapping("/sensors/list")
    public ApiResponse<List<Sensors>> listSensors() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensors list fetched successfully.", sensorsService.findAll());
    }

    @GetMapping("/sensors/{id}")
    public ApiResponse<Sensors> getSensorById(@PathVariable int id) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensor fetched successfully.", sensorsService.getById(id));
    }

    @GetMapping("/sensors/list/{term}")
    public ApiResponse<List<Sensors>> listSensorsByTerm(@PathVariable String term) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Sensors list fetched successfully.", sensorsService.getByQueryOfNameOrId(term));
    }
}
