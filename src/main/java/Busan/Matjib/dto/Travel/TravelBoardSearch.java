package Busan.Matjib.dto.Travel;

import Busan.Matjib.dto.MatJib.MatJibBoardSearch;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class TravelBoardSearch {

    private String travelPlace;
    private String travelTitle;
    private String travelWard;
    private String searchCondition;
    private String searchKeyword;

    public TravelBoardSearch() {
    }

    public TravelBoardSearch(TravelBoardSearch travelBoardSearch){
        this.travelPlace = SearchKeyword(travelBoardSearch, "place");
        this.travelTitle = SearchKeyword(travelBoardSearch, "title");
        this.travelWard = SearchKeyword(travelBoardSearch, "ward");
    }



    private String SearchKeyword(TravelBoardSearch travelBoardSearch, String searchCondition) {
        return Objects.equals(searchCondition, travelBoardSearch.getSearchCondition()) ? travelBoardSearch.getSearchKeyword() : "";
    }


}
