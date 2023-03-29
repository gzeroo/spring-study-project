package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.domain.Member;
import hello.servlet.web.domain.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3{

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = Member.builder()
                .username(username)
                .age(age).build();

        memberRepository.save(member); // 클라 입력 받은 값 저장

        // ModelView 에 데이터 담기
        ModelView mv = new ModelView("save");
        //  request.setAttribute("member",member); 에서 변경
        mv.getModel().put("member", member);

        return mv;
    }
}
