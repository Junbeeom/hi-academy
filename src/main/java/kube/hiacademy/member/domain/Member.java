package kube.hiacademy.member.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import static java.util.Objects.requireNonNull;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends AbstractEntity {

    @Embedded
    @NaturalId
    private LoginKey loginKey;

    private String name;

    @Enumerated
    private Type type;

    @Enumerated
    private Status status;

    public static Member register(String name, Type type) {
        Member member = new Member();
        member.name = requireNonNull(name);
        member.type = requireNonNull(type);
        member.loginKey = LoginKey.generate();
        member.status = Status.PENDING;

        return member;
    }




}
