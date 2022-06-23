package com.msg.after_school.controller;

import com.msg.after_school.domain.AfterSchool;
import com.msg.after_school.dto.SearchConditionDto;
import com.msg.after_school.response.AfterSchoolResponse;
import com.msg.after_school.service.AfterSchoolService;
import com.msg.after_school.type.SeasonType;
import com.msg.after_school.util.AfterSchoolConverter;
import com.msg.after_school.util.SearchConditionConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/afterschool")
@RequiredArgsConstructor
@Slf4j
public class AfterSchoolController {

    private final SearchConditionConverter searchConditionConverter;
    private final AfterSchoolService afterSchoolService;
    private final AfterSchoolConverter afterSchoolConverter;

    @GetMapping//방과후 목록을 가져온다.
    @ResponseBody
    public ResponseEntity<List<AfterSchoolResponse>> findAfterSchoolList(@RequestParam SeasonType season,
                                      @RequestParam String week,
                                      @RequestParam Long grade) {
        //Request정보를 검색조건 Dto로 치환한다.
        SearchConditionDto dto = searchConditionConverter.toDto(season, week, grade);
        //검색조건 Dto를 통해 방과후 목록을 가져온다.
        List<AfterSchool> dtoList = afterSchoolService.findAfterSchoolListBySearchCondition(dto);
        //받아온 목록을 응답값으로 치환한다.
        List<AfterSchoolResponse> responseList = afterSchoolConverter.toResponse(dtoList);
        log.info(String.valueOf(responseList));
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
    @GetMapping("/find") //조건에 맞는 방과후 목록을 가져온다.
    public ResponseEntity searchAfterSchoolList() {
        return ResponseEntity.ok().build();
    }
    @PostMapping("/apply") //방과후산청을 등록한다.
    public ResponseEntity applyAfterSchool() {
        return ResponseEntity.ok().build();
    }
    @PostMapping("/cancel") //방과후신청을 취소한다.
    public ResponseEntity cancelAfterSchool() {
        return ResponseEntity.ok().build();
    }
    @GetMapping("/check") //오늘이 방과후 기간인지 확인한다.
    public ResponseEntity checkAfterSchoolDate() {
        return ResponseEntity.ok().build();
    }
}
