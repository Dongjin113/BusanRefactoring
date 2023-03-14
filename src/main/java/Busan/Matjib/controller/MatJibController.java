package Busan.Matjib.controller;

import Busan.Matjib.domain.busan.matjib.MatJib;
import Busan.Matjib.dto.Getdata.MatJibDto;
import Busan.Matjib.dto.MatJib.MatJibBoardDto;
import Busan.Matjib.dto.MatJib.MatJibBoardSearch;
import Busan.Matjib.repository.MatJib.MatJibRepository;
import Busan.Matjib.service.MatJib.MatJibService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MatJibController {
    private final MatJibService matJibService;
    private final MatJibRepository matJibRepository;

    @RequestMapping("matjib.do")
    public String MatJibList(
            MatJibBoardSearch condition, Model model,
            @PageableDefault(page = 0, size = 5, sort = "matjibId", direction = Sort.Direction.ASC)
            Pageable pageable) {

        Page<MatJibBoardDto> matJibBoard = matJibService.searchMatJib(
                new MatJibBoardSearch(condition), pageable);

        int nowPage =  pageable.getPageNumber();
        int startPage = nowPage / pageable.getPageSize() * pageable.getPageSize() +1;
        int endPage = Math.min(startPage + pageable.getPageSize() -1, matJibBoard.getTotalPages());


        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("TotalPage", matJibBoard.getTotalPages());

        model.addAttribute("totalCount", matJibBoard.getTotalElements());
        model.addAttribute("matJibWard", matJibService.MatJibWard());
        model.addAttribute("matJibBoard", matJibBoard);
        model.addAttribute("searchCondition", condition.getSearchCondition());
        model.addAttribute("searchKeyWord", condition.getSearchKeyword());

        return "matjib/matjib";
    }



    @RequestMapping(value = "matjibwrite.do")
    public String MatJibWrite(Model model, HttpSession session) {
        model.addAttribute("li",matJibService.MatJibWard());
        if (session.getAttribute("role") == null) {
            return "home/error.jsp";
        } else {
            return session.getAttribute("role").equals("관리자") ? "matjib/matjibwrite.jsp" : "home/error.jsp";
        }
    }

    @RequestMapping(value="matjibInsert.do")
    public String MatJibInsert(MatJibDto matJibDto, HttpServletRequest request, HttpSession session) throws Exception{

        String path = MatJibPath(request);
        //      넘어온 실제파일
        MultipartFile updateFile = matJibDto.getImgFile();
        //      넘어온 파일명
        String fileName = updateFile.getOriginalFilename();
        File f = new File(path+fileName);


        //파일이 있으면 실행
        if(!updateFile.isEmpty()){
            //중복파일이 있으면 처리
            if(f.exists()){
                updateFile.transferTo(new File(path + existImgFileName(fileName)));
            } else { //중복 파일이 없으면 처리하는 영역
                updateFile.transferTo(new File(path + fileName));
            }

        }else {
            fileName = "둘리.png";
        }

        matJibDto.setMatJibImgAddr(fileName);
        matJibService.MatJibBoardInsert(matJibDto);

        if(session.getAttribute("role")==null) {
            return "home/error.jsp";
        }else {
            return  session.getAttribute("role").equals("관리자") ? "matjib.do" : "home/error.jsp";
        }

    }

    @RequestMapping(value="matjibEdit.do")
    public String matJibEdit(MatJibBoardDto matjib, Model model, HttpSession session){

        model.addAttribute("li", matJibService.MatJibWard());
        model.addAttribute("m", matJibService.findForEdit(matjib.getMatJibId()));


        if(session.getAttribute("role")==null) {
            return "home/error.jsp";
        }else{
            return session.getAttribute("role").equals("관리자") ? "matjib/matjibEdit.jsp" : "home/error.jsp";
        }
    }

    @RequestMapping(value="UpdateMatjib.do")
    public String UpdateMatJib(MatJibDto matJibDto, HttpServletRequest request, Model model,HttpSession session) throws Exception{

        String path = MatJibPath(request);
        MultipartFile updateFile =matJibDto.getImgFile(); // 실제파일
        String fileName = updateFile.getOriginalFilename(); // 넘어온 파일명

        MatJib preMatJib = matJibRepository.findMatJibById(matJibDto.getMatJibId());


        File f = new File(path+fileName);

        if(!updateFile.isEmpty()) {
            // 첨부파일이 넘어 왔으면 실행
            File fileDel = new File(path + preMatJib.getMatJibintroduce().getImgAddr());

            //기존 파일 체크
            if (!preMatJib.getMatJibintroduce().getImgAddr().equals("둘리.png")) {
                //기존파일 존재시 삭제
                if(fileDel.exists()) {
                    fileDel.delete() ;
                }
            }

            //중복파일이 있으면 처리
            if(f.exists()){
                fileName = existImgFileName(fileName);
                updateFile.transferTo(new File(path+fileName));
                matJibDto.setMatJibImgAddr(fileName);
            } else { //중복 파일이 없으면 처리하는 영역
                updateFile.transferTo(new File(path + fileName));
            }

        }
        matJibService.MatJibBoardUpdate(matJibDto);

//        if(session.getAttribute("role")==null) {
//            return "/home/error.jsp";
//        }else if(session.getAttribute("role").equals("관리자")) {
//            if(vo.getSEQ() <= 150) {
//                if(!updateFile.isEmpty()) {
//                    service.matjibUpdate2(vo);
//                }else {
//                    service.matjibUpdate(vo);
//                }
//            }else {
//                service.matjibUpdate(vo);
//            }
//            return "matjib.do";
//        }else{
//            return "/home/error.jsp";
//        }
        return "";
    }

//    맛집 삭제
    @RequestMapping(value="delFood.do")
    public String DeleteMatJib(MatJibDto matJibDto, HttpServletRequest request,HttpSession session){

        String path = MatJibPath(request);
        String delFile = matJibService.findForEdit(matJibDto.getMatJibId()).getMatJibAddr();

        File f = new File(path + delFile);

//      사진이 등록되어있는지 확인
        if (!delFile.equals("둘리.png")) {
//      사진이  존재하면 삭제
            if(f.exists()) {
                f.delete() ;
            }
        }

        if(session.getAttribute("role")==null) {
            return "home/error.jsp";
        }else if(session.getAttribute("role").equals("관리자")) {
            matJibService.RemoveMatJib(matJibDto.getMatJibId());
            return "matjib.do";
        }else{
            return "home/error.jsp";
        }
    }

//    맛집 상세보기
    @RequestMapping(value="matjibdetail.do")
    public String matJibDetail(@RequestParam(name = "matJibNum") Long matJibId, Model model){
        matJibService.MatJibCnt(matJibId);
        MatJibBoardDto forEdit = matJibService.findForEdit(matJibId);
        model.addAttribute("matjib", forEdit);

        return "matjib/matjibDetail";
    }


    private String MatJibPath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("/FoodIMG/");
    }

    private String existImgFileName(String fileName){
        String onlyFileName = fileName.substring(0, fileName.lastIndexOf("."));
        String extension = fileName.substring(fileName.lastIndexOf("."));
        fileName = onlyFileName+"_"+ LocalDateTime.now()+extension;
        return fileName;
    }


}
