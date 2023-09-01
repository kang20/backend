package com.example.aiwebservice.data.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemberDTO {


    @NotBlank(message = "이름을 입력해주세요")
    @Size(min=2,max=8,message = "이름의 길이를 확인해주세요")
    private String name; // 이름


    @NotBlank(message = "ID를 입력해주세요")
    @Size(min=2,max=8,message = "ID의 길이를 확인해주세요")
    private String id; // 아이디


    @NotBlank(message = "password를 입력해주세요")
    @Size(min=2,max=8,message = "비밀번호의 길이를 확인해주세요")
    private String password; // 패스워드


}
