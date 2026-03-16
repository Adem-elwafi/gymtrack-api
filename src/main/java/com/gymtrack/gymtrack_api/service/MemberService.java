package com.gymtrack.gymtrack_api.service;

import com.gymtrack.gymtrack_api.dto.MemberRequest;
import com.gymtrack.gymtrack_api.dto.MemberResponse;
import com.gymtrack.gymtrack_api.model.Member;
import com.gymtrack.gymtrack_api.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse createMember(MemberRequest request) {
        // 1. Mapping Manuel : Request -> Entity
        Member member = new Member();
        member.setFirstName(request.firstName());
        member.setLastName(request.lastName());
        member.setEmail(request.email());
        member.setMembershipType(request.membershipType());
        member.setRegistrationDate(LocalDate.now());

        // 2. Sauvegarde
        Member savedMember = memberRepository.save(member);

        // 3. Mapping Manuel : Entity -> Response
        return new MemberResponse(
                savedMember.getId(),
                savedMember.getFirstName() + " " + savedMember.getLastName(),
                savedMember.getEmail(),
                savedMember.getMembershipType(),
                savedMember.getRegistrationDate(),
                savedMember.isActive()
        );
    }
}