package pl.aleksl.sensorsportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.aleksl.sensorsportal.model.Sensors;
import pl.aleksl.sensorsportal.repository.SensorsDao;
import sun.management.Sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "sensorsService")
public class SensorsService {

    @Autowired
    SensorDustService sensorDustService;
    @Autowired
    SensorTempHumPressService sensorTempHumPressService;

    @Autowired
    SensorsDao sensorsDao;

    public List<Sensors> findAll() {
        List list = new ArrayList<>();
        sensorsDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public List<Sensors> findAllWithLastMeasurement() {
        List list = new ArrayList<>();
        sensorsDao.findAll().iterator().forEachRemaining(sensors -> {
            enrichSensorOfLastMeasurement(sensors);
            list.add(sensors);
        });
        return list;
    }

    public Sensors getById(int id) {
        Optional<Sensors> sensor = sensorsDao.findById(id);
        if (!sensor.isPresent())
            return null;
        return sensor.get();
    }

    public List<Sensors> getByQueryOfNameOrId(String term) {
        Optional<List<Sensors>> sensor = sensorsDao.findBySensorByQuery(term);
        if (!sensor.isPresent())
            return null;
        return sensor.get();
    }

    public Sensors getByIdWithLastMeasurement(int id) {
        Optional<Sensors> sensorsOptional = sensorsDao.findById(id);
        if (!sensorsOptional.isPresent())
            return null;
        Sensors sensor = sensorsOptional.get();
        enrichSensorOfLastMeasurement(sensor);
        return sensor;
    }

    private void enrichSensorOfLastMeasurement(Sensors sensor){
        List sensorsDust = sensorDustService.getBySensorIdLastMeasurements(sensor.getId(), 1);
        sensor.setSensorsDustList(sensorsDust);
        List sensorTempHumPress = sensorTempHumPressService.getBySensorIdLastMeasurements(sensor.getId(), 1);
        sensor.setSensorTempHumPress(sensorTempHumPress);
    }

}
