package com.example.aiwebservice.data.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateMemberDTO {


    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자 이름은 필수 입니다.")
    private String name;

    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String id;

    @Size(min = 3, max = 25)
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

}
