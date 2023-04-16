package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    // 어노테이션으로 트랜잭션 적용
    // 프록시를 활용함, 이너 함수를 사용할 때 제대로 먹히지 않음 프록시 패턴을 찾아보자
    @Transactional
    public Member register(RegisterMemberCommand command) {
        /*
         * 목표
         * - 회원 정보(이메일, 닉네임, 생년월일)를 등록하는 것
         * - 닉네임은 10자를 넘길 수 없다
         *
         * 파라미터 memberRegisterCommand
         * val member = Member.of(memberRegisterCommand)
         * memberRepository.save(member)
         * */
        val member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

        // TransactionTemplate 직접 제어할 수도 있다. 거의 쓰지 않는다.
        var savedMember = memberRepository.save(member);

        saveMemberNicknameHistory(savedMember);
        return savedMember;
    }

    @Transactional
    public void changeNickname(Long memberId, String nickname) {
        /*
        1. 회원의 이름을 변경
        2. 변경 내역을 저장한다.
         */
        var member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);

        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory
                .builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
        memberNicknameHistoryRepository.save(history);
    }
}