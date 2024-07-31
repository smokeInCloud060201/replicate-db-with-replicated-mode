package vn.com.smoke.masterservice.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {

    private String id;

    private String name;

    private String email;

    private String address;
}
