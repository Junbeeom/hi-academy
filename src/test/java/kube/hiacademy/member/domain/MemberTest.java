package kube.hiacademy.member.domain;

import org.junit.jupiter.api.Test;

import static java.beans.Beans.isInstanceOf;
import static kube.hiacademy.member.domain.Status.*;
import static kube.hiacademy.member.domain.Type.ADMIN;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemberTest {
    @Test
    void Member_isNotNull() {
        Member generatedMember = Member.register("junbeom", ADMIN);

        assertNotNull(generatedMember);
        assertThat(generatedMember.getType()).isEqualTo(ADMIN);
        assertThat(generatedMember.getLoginKey().getSize()).isEqualTo(8);
        assertThat(generatedMember.getName()).isEqualTo("junbeom");
        assertThat(generatedMember.getStatus()).isEqualTo(PENDING);
    }

    @Test
    void pendingMember_ShouldBeActive() {
        Member junbeom = Member.register("junbeom", ADMIN);
        junbeom.activate(junbeom.getStatus());

        assertThat(junbeom.getStatus()).isEqualTo(ACTIVE);
    }

    @Test
    void deActivate_NotActivateMember_ShouldBeThrowException() {
        Member junbeom = Member.register("junbeom", ADMIN);

        assertThatThrownBy(() -> junbeom.deActivate(junbeom.getStatus()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("회원의 상태가 ACTIVATE 상태가 아닙니다.");
    }

}