package pl.aleksl.sensorsportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.aleksl.sensorsportal.model.Sensors;
import pl.aleksl.sensorsportal.model.SensorsDust;
import java.util.List;
import java.util.Optional;

import java.util.List;

@Repository
public interface SensorsDao extends CrudRepository<Sensors, Integer> {


    @Query(value = "select * from sensors s where s.name like CONCAT('%',:term,'%') or s.id like CONCAT('%',:term,'%')", nativeQuery = true)
    Optional<List<Sensors>> findBySensorByQuery(String term);
}
