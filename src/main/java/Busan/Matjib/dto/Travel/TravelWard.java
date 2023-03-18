package Busan.Matjib.dto.Travel;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class TravelWard {

    private String ward;
    private int matJibWardCount;

    @QueryProjection
    public TravelWard(String ward, int matJibWardCount) {
        this.ward = ward;
        this.matJibWardCount = matJibWardCount;
    }
}
