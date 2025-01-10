package com.practiceSpring.springBasicPractice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practiceSpring.springBasicPractice.domain.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//Component 어노테이션을 통해 별도의 객체를 생성할 필요가 없는 싱글톤 객체를 생성.
//Contoller 어노테이션을 통해 쉽게 사용자의 http요청을 처리하고 http응답을 줄 수 있음.
@Controller
//클래스 차원에 url매핑시에는 RequestMapping을 사용한다.
@RequestMapping("/practice")
public class HelloController {

//    case1. 서버가 사용자에게 단순 String 데이터 return
//    사용자에게 화면을 보여주는 것이 아닌 데이터를 넘겨주므로 ResponceBody 어노테이션을 쓴다.
//    GetMapping 어노테이션에 url을 설계한다.
    @GetMapping("")
    @ResponseBody
    public String helloWolrd() {
        return "Hello World";
    }

//  case2. 서버가 사용자에게 화면을 return(get요청)
//    @ResponseBody가 없고, return타입이 String일 때, 서버는 templates 폴더 밑 html화면을 return.
    @GetMapping("/hellohtml")
    public String hellohtml() {
        return "hellohtml";
    }
//  case3. 서버가 사용자에게 json형식의 데이터를 return(get요청)
    @GetMapping("/helloMapper")
    @ResponseBody
    public String helloMapper() throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        Hello hi = new Hello("joon", "hyuk@naver.com");
        String str = obj.writeValueAsString(hi);
        return str;
    }
//        직접 json으로 직렬화 할 필요없이, return타입을 클래스로 할 경우. 자동으로 직렬화
    @GetMapping("/autoJson")
    @ResponseBody
    public Hello authoJson() {
        Hello hi = new Hello("good", "good@naver.com");
        return hi;
    }
//    case4. 사용자가 json데이터를 요청하되, parameter형식으로 특정객체 요청(get요청)
//    parameter 형식 : /hello/para1?name=hongildong
    @GetMapping("/param1")
    @ResponseBody
    public Hello param1(@RequestParam(value = "name")String inputName) {
        Hello hi = new Hello(inputName, "test@naver.com");
        return hi;
    }
//    @RequestParam을 통해 para? 뒤에있는 값을 꺼낼 수 있다. 이 때 value는 param뒤에 있는 밸류와 같아야한다!

//    param2개 이상 형식 : /hellp/param2?name=hong&email=hong@naver.com
    @GetMapping("/param2")
    @ResponseBody
    public Hello param2(@RequestParam(value = "name")String inputName, @RequestParam(value = "email")String inputEmail) {
        Hello hi = new Hello(inputName,inputEmail);
        return hi;
    }

//    case5. parameter가 많아질 경우, 데이터바인딩을 통해 input값 처리(get요청)
//    parameter 1개 이상 형식 : /hello/param
//    각 파라미터의 값이 Hello 클래스의  각 변수에 Mapping된다
//    @ModelAttribute는 데이터바인딩 할 때 사용.
//    안넣어도 되지만 명시적으로 표현하기 위해 넣는다.
    @GetMapping("/binding")
    @ResponseBody
    public Hello binding(@ModelAttribute Hello hello) {
        return hello;
    }

//    case6. 화면을 return해 주되, 특정변수값을 동적으로 세팅
    @GetMapping("/moving")
    public String moving(@RequestParam(value = "name")String inputName, Model model) {
        model.addAttribute("modelName", inputName);
        return "moving";
    }
//    case7. 화면을 return해 주되, 객체를 화면에 동적으로 세팅
    @GetMapping("movingClass")
    public String movingClass(@ModelAttribute Hello hi, Model model) {
        model.addAttribute("modelMoving", hi);
        return "movingClass";
    }
//    case8. pathvariable방식을 통해 사용자로부터 값을 받아 화면 return
//    형식 : /hello/model-path/hongildong
//    예시 : /author/list/1
    @GetMapping("/path/{inputName}")
    public String path(@PathVariable String inputName, Model model) {
        model.addAttribute("modelName",inputName);
        return "path";
    }


    @GetMapping("/form-view")
    public String formView() {
        return "form-view";
    }
//    case1. form데이터 형식의 post요청처리(텍스트만 있는 application/x-www~)
//    형식 : ?name=xxx&email=yyy 데이터가 http body에 들어옴.
    @PostMapping("/form-view")
    @ResponseBody
    public String formViewPost(Hello hello) {
        System.out.println(hello);
        return "OK";
    }

//    case2. form데이터 형식의 post요청 처리(file + text 형식)
//    url패턴 : form-file-view
    @GetMapping("/form-file-view")
    public String formFileView() {
        return "form-file-view";
    }

    @PostMapping("/form-file-view")
    @ResponseBody
    public String formFileViewPost(Hello hello, @RequestParam(value = "photo")MultipartFile photo) {
        System.out.println(hello);
        System.out.println(photo.getOriginalFilename());
        return "OK";
    }

//    case3. js를 활용한 form데이터 전송(text만)
    @GetMapping("axios-form-view")
    public String axiosFormView() {
        return "axios-form-view";
    }

    @PostMapping("/axios-form-view")
    @ResponseBody
    public String axiosFormViewPost(Hello hello) {
        System.out.println(hello);
        return "OK";
    }

//    case4. js를 활용한 form데이터 전송(text + file)
    @GetMapping("/axios-form-file-view")
    public String axiosFormFileView() {
        return "axios-form-file-view";
    }

    @PostMapping("/axios-form-file-view")
    @ResponseBody
    public String axiosFormFileViewPost(Hello hello, @RequestParam(value = "photo")MultipartFile photo) {
        System.out.println(hello);
        System.out.println(photo.getOriginalFilename());
        return "OK";
    }

//    case5. js를 활용한 form데이터 전송(text + 멀티file)
    @GetMapping("/axios-form-multi-file-view")
    public String axiosFormMultiFileView () {
        return "axios-form-multi-file-view";
    }

    @PostMapping("/axios-form-multi-file-view")
    @ResponseBody
    public String axiosFormMultiFileViewPost(Hello hello, @RequestParam(value = "photos") List<MultipartFile> photos) {
        System.out.println(hello);
        for (int i = 0; i < photos.size(); i++) {
            System.out.println(photos.get(i).getOriginalFilename());
        }
        return "OK";
    }

//    case6. js를 활용한 json데이터 전송
//    형식 : {name:"hong", email:"hong@naver.com}
    @GetMapping("/axios-json-view")
    public String axiosJsonView() {
        return "axios-json-view";
    }

    @PostMapping("/axios-json-view")
    @ResponseBody
    public String axiosJsonViewPost(@RequestBody Hello hello) {
        System.out.println(hello);
        return "OK";
    }
}



