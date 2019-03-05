package pl.aleksl.sensorsportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.aleksl.sensorsportal.model.SensorDust;
import pl.aleksl.sensorsportal.repository.SensorDustDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "sensorDustService")
public class SensorDustService {


    @Autowired
    SensorDustDao sensorDustDao;

    public List<SensorDust> findAll() {
        List list = new ArrayList<>();
        sensorDustDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public List<SensorDust> getBySensorId(int sensorId) {
        List list = new ArrayList<>();
        sensorDustDao.findAll().iterator().forEachRemaining(sensorsDust -> {
            if (sensorsDust.getSensor_id() == sensorId) {
                list.add(sensorsDust);
            }
        });

        return list;
    }

    public SensorDust getBySensorIdLastMeasurement(int sensorId) {
       List<SensorDust> sensors = getBySensorIdLastMeasurements(sensorId, 1);
        if(sensors != null && sensors.size() == 1)
            return sensors.get(0);
        return null;
    }

    public List<SensorDust> getBySensorIdLastMeasurements(int sensorId, int count) {
        Optional<List<SensorDust>> sensor = sensorDustDao.findBySensorIdLastMeasurement(sensorId, count);
        if (!sensor.isPresent())
            return null;
        return sensor.get();
    }

    public void save(SensorDust s){
        sensorDustDao.save(s);
    }


}
