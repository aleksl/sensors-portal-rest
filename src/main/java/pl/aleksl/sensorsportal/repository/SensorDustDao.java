package pl.aleksl.sensorsportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.aleksl.sensorsportal.model.SensorDust;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorDustDao extends CrudRepository<SensorDust, Integer> {

    @Query (value = "select * from sensors_dust s where s.sensor_id = :sensorId  and s.id = (select max(sd.id) from sensors_dust sd where sd.sensor_id = :sensorId)", nativeQuery = true)
    Optional<SensorDust> findBySensorIdLastMeasurement(int sensorId);

    @Query (value = "select * from sensors_dust s where s.sensor_id = :sensorId ORDER BY id DESC LIMIT :count", nativeQuery = true)
    Optional<List<SensorDust>> findBySensorIdLastMeasurement(int sensorId, int count);
}
