package kube.hiacademy.member.domain;

import kube.hiacademy.member.application.provided.MemberRegister;

public class MemberFixture {
    public static MemberCreateReqeust createMemberRegisterRequest(String name, Type type) {
        return new MemberCreateReqeust(name, type);
    }

    public static MemberCreateReqeust createMemberRegisterRequest() {
        return createMemberRegisterRequest("junbeom", Type.ADMIN);
    }
}
