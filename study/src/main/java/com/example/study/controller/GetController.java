package com.example.study.controller;

import com.example.study.controller.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")//Localhost:8080/api
public class GetController {
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")//Localhost:8080/api/getMethod
    public String getRequest() {

        return "Hi getMethod";
    }

    @GetMapping("/getParameter") //Localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam String password){
        //spring에서는 password라는 변수가 아니면 mapping이 안됨 그럴경우 변수선언에 @RequestParam(name = "password") String pwd)를 넣어주면된다
        System.out.println("id : "+ id);
        System.out.println("pw : "+ password);

        return id + password;
    }
    //Localhost:8080/api/multiParameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        //{"account":"","email":"","page":0}
        return searchParam;
    }


}

