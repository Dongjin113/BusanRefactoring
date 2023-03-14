package Busan.Matjib.controller;

import Busan.Matjib.dto.Getdata.TravelDto;
import Busan.Matjib.dto.MatJib.MatJibBoardDto;
import Busan.Matjib.dto.Travel.TravelBoardDto;
import Busan.Matjib.service.MatJib.MatJibService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class homeController {

    private final MatJibService matJibService;

    @GetMapping("/")
    public String main(Model model) {
        return "home/start";
    }

    @RequestMapping("index.do")
    public  String   select5 (TravelBoardDto travelBoardDto  , Model model) {
        System.out.println("여기1");
        List<MatJibBoardDto> matJibTop3 = matJibService.MatJibTop3();

        System.out.println("여기2");

        for (MatJibBoardDto matJibBoardDto : matJibTop3) {
            System.out.println("맛집리스트 ="+ matJibBoardDto.getMatJibId());
        }
        System.out.println("여기3");

        System.out.println("index.do");
        model.addAttribute("matJib", matJibService.MatJibTop3());
//        model.addAttribute("li", matJibService.MatJibTop3());

        return "home/index";
    }

}
