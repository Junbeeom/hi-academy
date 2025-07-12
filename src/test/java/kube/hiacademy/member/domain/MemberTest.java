package kube.hiacademy.member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static kube.hiacademy.member.domain.MemberFixture.createMemberRegisterRequest;
import static kube.hiacademy.member.domain.Status.*;
import static kube.hiacademy.member.domain.Type.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemberTest {
    Member generatedMember;
    
    @BeforeEach
    void setUp() {
        generatedMember = Member.register(createMemberRegisterRequest());
    }

    @Test
    void Member_isNotNull() {
        assertNotNull(generatedMember);
        assertThat(generatedMember.getType()).isEqualTo(ADMIN);
        assertThat(generatedMember.getLoginKey().getSize()).isEqualTo(8);
        assertThat(generatedMember.getName()).isEqualTo("junbeom");
        assertThat(generatedMember.getStatus()).isEqualTo(PENDING);
    }

    @Test
    void pendingMember_ShouldBeActive() {
        generatedMember.activate(generatedMember.getStatus());

        assertThat(generatedMember.getStatus()).isEqualTo(ACTIVE);
    }

    @Test
    void deActivate_ActivateMember_ShouldBeDeActivate() {
        generatedMember.activate(generatedMember.getStatus());
        generatedMember.deActivate(generatedMember.getStatus());

        assertThat(generatedMember.getStatus()).isEqualTo(DEACTIVATED);
    }

    @Test
    void deActivate_NotActivateMember_ShouldBeThrowException() {
        assertThatThrownBy(() -> generatedMember.deActivate(generatedMember.getStatus()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("회원의 상태가 ACTIVATE 상태가 아닙니다.");
    }
    
    @Test
    void changeName_ShouldBeChangeName() {
        generatedMember.changeName("cho");

        assertThat(generatedMember.getName()).isEqualTo("cho");
    }

    @Test
    void changeType_ShouldBeChangeType() {
        generatedMember.changeType(Type.TEACHER);

        assertThat(generatedMember.getType()).isEqualTo(Type.TEACHER);
    }

}