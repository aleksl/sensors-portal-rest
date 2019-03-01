package pl.aleksl.sensorsportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.aleksl.sensorsportal.model.SensorsDust;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorDustDao extends CrudRepository<SensorsDust, Integer> {

    @Query (value = "select * from sensors_dust s where s.sensor_id = :sensorId  and s.id = (select max(sd.id) from sensors_dust sd where sd.sensor_id = :sensorId)", nativeQuery = true)
    Optional<SensorsDust> findBySensorIdLastMeasurement(int sensorId);

    @Query (value = "select * from sensors_dust s where s.sensor_id = :sensorId ORDER BY id DESC LIMIT :count", nativeQuery = true)
    Optional<List<SensorsDust>> findBySensorIdLastMeasurement(int sensorId, int count);
}
