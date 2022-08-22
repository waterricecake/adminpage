package com.example.study.controller;


import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //class의 주소가 겹쳐도 오류가 뜨지 않느다, method에서는 겹치면 안됨
public class PostController {

    //html <Form>
    //ajax 검색
    // parameter가 많을때
    // json,xml, multipart-form/ text-plain
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){

        return searchParam;
    }

    @PutMapping("/putMethod")
    public void put(){

    }

    @PatchMapping("/patchMethod")
    public void patch(){

    }


}
