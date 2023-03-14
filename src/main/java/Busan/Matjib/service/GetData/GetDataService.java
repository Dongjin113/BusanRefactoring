package Busan.Matjib.service.GetData;

import Busan.Matjib.domain.busan.matjib.MatJib;
import Busan.Matjib.domain.busan.travel.Travel;
import Busan.Matjib.repository.GetData.GetDataRepository;
import Busan.Matjib.repository.MatJib.MatJibRepository;
import Busan.Matjib.repository.Travel.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetDataService {

    private final MatJibRepository matJibRepository;
    private final TravelRepository travelRepository;
    private final GetDataRepository getDataRepository;

    @Transactional
    public void MatJibSave() throws Exception {
        getDataRepository.GetFoodData().stream().forEach(
                foodDto -> matJibRepository.save(new MatJib(foodDto))
        );
    }

    @Transactional
    public void TravelSave() throws Exception {
        getDataRepository.GetTravelData().stream().forEach(
                travelDto -> travelRepository.save(new Travel(travelDto))
        );
    }









}
