package Busan.Matjib.dto.MatJib;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class MatJibBoardSearch {

    private String matJibMain_title; // MAIN_TITLE 음식점이름
    private String matJibMenu;//<RPRSNTV_MENU> 대표메뉴명
    private String matJibWard;//<GUGUN_NM> 지역구
    private String searchCondition;
    private String searchKeyword;
    public MatJibBoardSearch() {
    }
    public MatJibBoardSearch(MatJibBoardSearch matJibBoardSearch) {
            this.matJibMain_title = SearchKeyword(matJibBoardSearch, "main_title");
            this.matJibMenu = SearchKeyword(matJibBoardSearch, "menu");
            this.matJibWard = SearchKeyword(matJibBoardSearch, "ward");
    }

    private String SearchKeyword(MatJibBoardSearch matJibBoardSearch, String searchCondition) {
        return Objects.equals(searchCondition, matJibBoardSearch.getSearchCondition()) ? matJibBoardSearch.getSearchKeyword() : "";
    }



}
