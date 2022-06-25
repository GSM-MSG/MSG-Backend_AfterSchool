package com.msg.after_school.domain.after_school.controller;

import com.msg.after_school.domain.after_school.data.entity.DayOfWeek;
import com.msg.after_school.domain.after_school.data.dto.AfterSchoolDto;
import com.msg.after_school.domain.after_school.data.dto.ApplyAfterSchoolDto;
import com.msg.after_school.domain.after_school.data.dto.SearchConditionDto;
import com.msg.after_school.domain.after_school.data.response.AfterSchoolResponse;
import com.msg.after_school.domain.after_school.service.AfterSchoolService;
import com.msg.after_school.domain.after_school.data.type.SeasonType;
import com.msg.after_school.domain.after_school.util.AfterSchoolConverter;
import com.msg.after_school.domain.after_school.util.SearchConditionConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/afterschool")
@RequiredArgsConstructor
public class AfterSchoolController {

    private final SearchConditionConverter searchConditionConverter;
    private final AfterSchoolService afterSchoolService;
    private final AfterSchoolConverter afterSchoolConverter;

    @GetMapping//방과후 목록을 가져온다.
    public ResponseEntity<List<AfterSchoolResponse>> findAfterSchoolList(@RequestParam SeasonType season,
                                                                         @RequestParam DayOfWeek week,
                                                                         @RequestParam Integer grade) {
        //Request정보를 검색조건 Dto로 치환한다.
        SearchConditionDto dto = searchConditionConverter.toDto(season, week, grade);
        //검색조건Dto를 통해 방과후 목록을 가져온다.
        List<AfterSchoolDto> dtoList = afterSchoolService.findAfterSchoolListBySearchCondition(dto);
        //받아온 목록을 응답값으로 치환한다.
        List<AfterSchoolResponse> responseList = afterSchoolConverter.toResponse(dtoList);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/apply") //방과후산청을 등록한다.
    public ResponseEntity applyAfterSchool(@RequestBody ApplyAfterSchoolDto applyAfterSchoolDto) {
        afterSchoolService.applyAfterSchool(applyAfterSchoolDto.getAfterSchoolId());
        return new ResponseEntity(HttpStatus.CREATED);
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
