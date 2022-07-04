package com.msg.after_school.domain.after_school.controller;

import com.msg.after_school.domain.after_school.data.dto.CancelApplyAfterSchoolDto;
import com.msg.after_school.domain.after_school.data.dto.response.AfterSchoolListResponseDto;
import com.msg.after_school.domain.after_school.data.response.AfterSchoolResponse;
import com.msg.after_school.domain.after_school.service.AfterSchoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import com.msg.after_school.domain.after_school.data.dto.ApplyAfterSchoolDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/afterschool")
@RequiredArgsConstructor
@Slf4j
public class AfterSchoolController {
    private final AfterSchoolService afterSchoolService;

    @GetMapping//방과후 목록을 가져온다.
    @ResponseBody
    public ResponseEntity<List<AfterSchoolListResponseDto>> findAfterSchoolList() {
        //Request정보를 검색조건 Dto로 치환한다.
        List<AfterSchoolListResponseDto> dtoList = afterSchoolService.findAfterSchoolList();
        //받아온 목록을 응답값으로 치환한다.
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }
    @PostMapping("/apply") //방과후산청을 등록한다.
    public ResponseEntity<Void> applyAfterSchool(@RequestBody ApplyAfterSchoolDto applyAfterSchoolDto) {
        afterSchoolService.applyAfterSchool(applyAfterSchoolDto.getAfterSchoolId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/cancel") //방과후신청을 취소한다.
    public ResponseEntity<Void> cancelAfterSchool(@RequestBody CancelApplyAfterSchoolDto cancelApplyAfterSchoolDto) {
        //유저 이메일로 유저 찾기
        //유저 정보 클럽데이터로 수강테이블에있는 데이터 삭제
        afterSchoolService.cancelApplyAfterSchool(cancelApplyAfterSchoolDto.getAfterSchoolId());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/check") //오늘이 방과후 기간인지 확인한다.
    public ResponseEntity<Void> checkAfterSchoolDate() {
        return ResponseEntity.ok().build();
    }
}
