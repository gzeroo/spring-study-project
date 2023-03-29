package hello.servlet.web.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 멤버 정보를 db와 소통하는 클래스
// 데이터 저장, 조회, 삭제용 CRUD 용
public class MemberRepository {
    private static Map<Long, Member> store =new HashMap<>(); // 추후 DB를 사용할 수 있음

    private static long sequence = 0l;

    static {
        store.put(sequence, Member.builder().id(sequence).username("추성훈").age(43).build());
    }

    private  static final MemberRepository instance = new MemberRepository(); // 싱글톤 / 상수임.

    public static MemberRepository getInstance(){
        return instance; // 위에 상수 접근하기 위해서 생성
    }

    // 싱글톤 생성 못하게 프라이빗으로 막기!
    private MemberRepository(){

    }

    // save 만들기 creat
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // id 가져오기
    public Member findById(Long id){
        return store.get(id);
    }

    // 전체 정보 보기
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

    public void init(){
        save(Member.builder().age(25).username("윤지수").build());
        save(Member.builder().age(20).username("이정은").build());
        save(Member.builder().age(20).username("박가경").build());
    }

}
