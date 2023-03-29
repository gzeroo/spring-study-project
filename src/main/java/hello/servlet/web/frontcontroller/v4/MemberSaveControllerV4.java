package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = Member.builder()
                .username(username)
                .age(age).build();
        memberRepository.save(member);

        model.put("member", member);

        return "save";
    }
}
