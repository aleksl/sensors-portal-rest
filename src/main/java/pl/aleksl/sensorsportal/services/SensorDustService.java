package pl.aleksl.sensorsportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.aleksl.sensorsportal.model.Sensors;
import pl.aleksl.sensorsportal.model.SensorsDust;
import pl.aleksl.sensorsportal.repository.SensorDustDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "sensorDustService")
public class SensorDustService {


    @Autowired
    SensorDustDao sensorDustDao;

    public List<SensorsDust> findAll() {
        List list = new ArrayList<>();
        sensorDustDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public List<SensorsDust> getBySensorId(int sensorId) {
        List list = new ArrayList<>();
        sensorDustDao.findAll().iterator().forEachRemaining(sensorsDust -> {
            if (sensorsDust.getSensor_id() == sensorId) {
                list.add(sensorsDust);
            }
        });

        return list;
    }

    public SensorsDust getBySensorIdLastMeasurement(int sensorId) {
       List<SensorsDust> sensors = getBySensorIdLastMeasurements(sensorId, 1);
        if(sensors != null && sensors.size() == 1)
            return sensors.get(0);
        return null;
    }

    public List<SensorsDust> getBySensorIdLastMeasurements(int sensorId, int count) {
        Optional<List<SensorsDust>> sensor = sensorDustDao.findBySensorIdLastMeasurement(sensorId, count);
        if (!sensor.isPresent())
            return null;
        return sensor.get();
    }

    public void save(SensorsDust s){
        sensorDustDao.save(s);
    }


}
