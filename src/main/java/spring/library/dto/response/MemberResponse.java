package spring.library.dto.response;

import lombok.*;
import spring.library.dto.Dto.MemberDto;

@Getter
@Setter
@Builder
public class MemberResponse {
    private long id;
    private String name;
    private long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    public static MemberResponse from(MemberDto memberDto) {
        return MemberResponse.builder()
                .id(memberDto.getId())
                .name(memberDto.getName())
                .idNumber(memberDto.getIdNumber())
                .feature(memberDto.getFeature())
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }
}