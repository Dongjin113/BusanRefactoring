package Busan.Matjib.repository.Travel;

import Busan.Matjib.domain.busan.travel.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    Travel findTravelById(Long id);
    void deleteTravelById(Long id);
}
