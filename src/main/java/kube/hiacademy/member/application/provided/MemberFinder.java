package kube.hiacademy.member.application.provided;

import kube.hiacademy.member.domain.Member;

/**
 * Member 조회 기능을 제공한다.
 */
public interface MemberFinder {
    Member find(Long memberId);
}
