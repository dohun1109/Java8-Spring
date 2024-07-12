package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {


    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping(value = "/new-form")
    public String newForm() {
        return "new-form"; //Model 과 View 정보를 담아서 반환
        //RequestMappingHandlerMapping 은 스프링 빈 중에서 @RequestMapping 또는 @Controller 가 클래스 레벨에 붙어 있는 경우에 매핑정보로 인식한다.


    }

    @PostMapping(value = "/save")
    public String save(@RequestParam("username")String username ,
                       @RequestParam("age") int age,
                       Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();



        model.addAttribute("members", members);
        return "members";
    }}
