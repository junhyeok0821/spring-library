package spring.library.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private String name;
    private long idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

}