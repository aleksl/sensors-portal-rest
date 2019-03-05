package pl.aleksl.sensorsportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.aleksl.sensorsportal.model.SensorTempHumPress;
import pl.aleksl.sensorsportal.repository.SensorTempHumPressDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "sensorTempHumPressService")
public class SensorTempHumPressService {


    @Autowired
    SensorTempHumPressDao sensorTempHumPressDao;

    public List<SensorTempHumPress> findAll() {
        List list = new ArrayList<>();
        sensorTempHumPressDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public List<SensorTempHumPress> getBySensorId(int sensorId) {
        List list = new ArrayList<>();
        sensorTempHumPressDao.findAll().iterator().forEachRemaining(sensorTempHumPress -> {
            if (sensorTempHumPress.getSensor_id() == sensorId) {
                list.add(sensorTempHumPress);
            }
        });

        return list;
    }

    public SensorTempHumPress getBySensorIdLastMeasurement(int sensorId) {
        List<SensorTempHumPress> sensors = getBySensorIdLastMeasurements(sensorId, 1);
        if (sensors != null && sensors.size() == 1)
            return sensors.get(0);
        return null;
    }

    public List<SensorTempHumPress> getBySensorIdLastMeasurements(int sensorId, int count) {
        Optional<List<SensorTempHumPress>> sensor = sensorTempHumPressDao.findBySensorIdLastMeasurement(sensorId, count);
        if (!sensor.isPresent())
            return null;
        return sensor.get();
    }

    public void save(SensorTempHumPress s) {
        sensorTempHumPressDao.save(s);
    }
}
