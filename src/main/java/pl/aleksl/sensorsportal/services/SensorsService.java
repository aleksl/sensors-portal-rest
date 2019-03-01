package pl.aleksl.sensorsportal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.aleksl.sensorsportal.model.Sensors;
import pl.aleksl.sensorsportal.model.SensorsDust;
import pl.aleksl.sensorsportal.repository.SensorsDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "sensorsService")
public class SensorsService {

    @Autowired
    SensorsDao sensorsDao;

    public List<Sensors> findAll() {
        List list = new ArrayList<>();
        sensorsDao.findAll().iterator().forEachRemaining(list::add);
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
}
