package hello.servlet;

import hello.servlet.web.domain.MemberRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication //was 역할
@ServletComponentScan
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);

		MemberRepository memberRepository = MemberRepository.getInstance();
		memberRepository.init();
	}

}
