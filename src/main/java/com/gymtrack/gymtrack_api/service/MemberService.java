package com.gymtrack.gymtrack_api.service;

import com.gymtrack.gymtrack_api.dto.MemberRequest;
import com.gymtrack.gymtrack_api.dto.MemberResponse;
import com.gymtrack.gymtrack_api.model.Member;
import com.gymtrack.gymtrack_api.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List ;
import java.util.stream.Collectors;

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
    public MemberResponse getMemberById(Long id) {
        return memberRepository.findById(id)
                .map(member -> new MemberResponse(
                        member.getId(),
                        member.getFirstName() + " " + member.getLastName(),
                        member.getEmail(),
                        member.getMembershipType(),
                        member.getRegistrationDate(),
                        member.isActive()
                ))
                .orElseThrow(() -> new RuntimeException("Membre non trouvé avec l'id : " + id));
    }
    public MemberResponse updateMember(Long id, MemberRequest request) {
        // 1. Chercher le membre ou lancer une erreur s'il n'existe pas
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossible de modifier : Membre non trouvé avec l'id " + id));

        // 2. Mise à jour des champs avec les données du DTO
        existingMember.setFirstName(request.firstName());
        existingMember.setLastName(request.lastName());
        existingMember.setEmail(request.email());
        existingMember.setMembershipType(request.membershipType());

        // 3. Sauvegarder les modifications
        Member updatedMember = memberRepository.save(existingMember);

        // 4. Retourner le DTO de réponse
        return mapToResponse(updatedMember);
    }

    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(member -> new MemberResponse(
                        member.getId(),
                        member.getFirstName() + " " + member.getLastName(),
                        member.getEmail(),
                        member.getMembershipType(),
                        member.getRegistrationDate(),
                        member.isActive()
                ))
                .collect(Collectors.toList());
    }
}