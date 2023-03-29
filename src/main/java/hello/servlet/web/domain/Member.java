package hello.servlet.web.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Member {
    private long id; // 유저 pk 고유 번호

    private String username;

    private int age;

}
