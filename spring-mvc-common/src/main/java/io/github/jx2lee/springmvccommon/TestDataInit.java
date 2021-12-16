package io.github.jx2lee.springmvccommon;

import io.github.jx2lee.springmvccommon.domain.item.Item;
import io.github.jx2lee.springmvccommon.domain.item.ItemRepository;
import io.github.jx2lee.springmvccommon.domain.member.Member;
import io.github.jx2lee.springmvccommon.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test");
        member.setName("테스터");

        memberRepository.save(member);
    }

}
