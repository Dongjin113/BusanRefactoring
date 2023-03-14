package Busan.Matjib.controller;

import Busan.Matjib.service.GetData.GetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class GetDataController {
    private final GetDataService getDataService;

    @RequestMapping("foodData.do")
    public String foodData() throws Exception {
        getDataService.MatJibSave();
        return "index.do";
    }

    @RequestMapping("travelData.do")
    public  String   travelData() throws Exception {
        getDataService.TravelSave();
        return "index.do";
    }
}
