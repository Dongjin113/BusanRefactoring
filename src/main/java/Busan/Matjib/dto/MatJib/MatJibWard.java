package Busan.Matjib.dto.MatJib;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MatJibWard {

    private String ward;
    private int matJibWardCount;

    @QueryProjection
    public MatJibWard(String matJibWard, int MatJibWardCount) {
        ward = matJibWard;
        matJibWardCount = MatJibWardCount;
    }
}
