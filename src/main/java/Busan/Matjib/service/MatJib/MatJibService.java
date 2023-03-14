package Busan.Matjib.service.MatJib;

import Busan.Matjib.domain.busan.matjib.MatJib;
import Busan.Matjib.dto.Getdata.MatJibDto;
import Busan.Matjib.dto.MatJib.MatJibBoardDto;
import Busan.Matjib.dto.MatJib.MatJibBoardSearch;
import Busan.Matjib.dto.MatJib.MatJibWard;
import Busan.Matjib.repository.MatJib.MatJibRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatJibService {
    private final MatJibRepository matJibRepository;

    public Page<MatJibBoardDto> searchMatJib(
            MatJibBoardSearch condition,
            Pageable pageable
    ){
        return matJibRepository.searchMatJib(condition,pageable);
    }

    public List<MatJibBoardDto> MatJibTop3(){
        return matJibRepository.matJibTop3();
    }


    public List<MatJibWard> MatJibWard(){
        return matJibRepository.wardCount();
    }

    @Transactional
    public void MatJibBoardInsert(MatJibDto matJibDto){
        matJibRepository.save(new MatJib(matJibDto));
    }

    @Transactional
    public MatJibBoardDto findForEdit(Long id){
        MatJib matJib = matJibRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("MatJib id를 통해 찾을수 없습니다" + id));
        return new MatJibBoardDto(matJib);
    }


    @Transactional
    public void MatJibBoardUpdate(MatJibDto matJibDto){
        MatJib preMatJib = matJibRepository.findMatJibById(matJibDto.getMatJibId());
        preMatJib.updateMatJib(matJibDto);
    }

    @Transactional
    public void RemoveMatJib(Long id){
        matJibRepository.deleteMatJibById(id);
    }

    @Transactional
    public void MatJibCnt(Long id){
        matJibRepository.findMatJibById(id).cntPlus();
    }


    private String MatJibPath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("/FoodIMG/");
    }

    private String existImgFileName(String fileName){
        String onlyFileName = fileName.substring(0, fileName.lastIndexOf("."));
        String extension = fileName.substring(fileName.lastIndexOf("."));
        fileName = onlyFileName+"_"+LocalDateTime.now()+extension;
        return fileName;
    }




}
