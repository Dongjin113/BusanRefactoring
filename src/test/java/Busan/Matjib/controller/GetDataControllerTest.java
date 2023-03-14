package Busan.Matjib.controller;

import Busan.Matjib.domain.busan.matjib.MatJib;
import Busan.Matjib.repository.MatJib.MatJibRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
class GetDataControllerTest {

    private final GetDataController getDataController;
    private final MatJibRepository matJibRepository;

    @Autowired
    public GetDataControllerTest(GetDataController getDataController, MatJibRepository matJibRepository) {
        this.getDataController = getDataController;
        this.matJibRepository = matJibRepository;
    }

    @Test
    @Rollback(value = false)
    public void MatJibGetData() throws Exception {
        //given
        getDataController.foodData();
        //when
        List<MatJib> results = matJibRepository.findAll();
        //then
        Assertions.assertThat(results).hasSize(150);
    }

    @Test
    @Rollback(value = false)
    public void TravelFoodDto() throws Exception{

        //given
        getDataController.travelData();
        //when

        //then

    }
}