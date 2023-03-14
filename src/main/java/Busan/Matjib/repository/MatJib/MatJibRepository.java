package Busan.Matjib.repository.MatJib;

import Busan.Matjib.domain.busan.matjib.MatJib;
import Busan.Matjib.dto.MatJib.MatJibBoardDto;
import Busan.Matjib.dto.MatJib.MatJibWard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatJibRepository extends JpaRepository<MatJib,Long>, MatJibRepositoryCustom {

    MatJib findMatJibById(Long id);

    void deleteMatJibById(Long id);

}
