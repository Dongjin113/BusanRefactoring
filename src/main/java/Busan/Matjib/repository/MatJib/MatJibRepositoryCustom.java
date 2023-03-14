package Busan.Matjib.repository.MatJib;

import Busan.Matjib.dto.MatJib.MatJibBoardDto;
import Busan.Matjib.dto.MatJib.MatJibBoardSearch;
import Busan.Matjib.dto.MatJib.MatJibWard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatJibRepositoryCustom {

    Page<MatJibBoardDto> searchMatJib(MatJibBoardSearch condition, Pageable pageable);
    List<MatJibBoardDto> matJibTop3();
    List<MatJibWard> wardCount();

}
