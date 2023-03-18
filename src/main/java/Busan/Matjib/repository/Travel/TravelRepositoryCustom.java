package Busan.Matjib.repository.Travel;

import Busan.Matjib.dto.Travel.TravelBoardDto;
import Busan.Matjib.dto.Travel.TravelBoardSearch;
import Busan.Matjib.dto.Travel.TravelWard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TravelRepositoryCustom {

    Page<TravelBoardDto> searchTravel(TravelBoardSearch condition, Pageable pageable);
    List<TravelBoardDto> travelTop3();
    List<TravelWard> wardCount();
}
