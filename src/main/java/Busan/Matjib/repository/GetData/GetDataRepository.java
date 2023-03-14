package Busan.Matjib.repository.GetData;

import Busan.Matjib.dto.Getdata.MatJibDto;
import Busan.Matjib.dto.Getdata.TravelDto;

import java.util.List;

public interface GetDataRepository {

    List<MatJibDto> GetFoodData() throws Exception;
    List<TravelDto> GetTravelData() throws Exception;

}
