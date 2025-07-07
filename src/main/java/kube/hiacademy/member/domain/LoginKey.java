package kube.hiacademy.member.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Lombok 사용 시
public class LoginKey {
    private String loginKey;

    // 생성자: 외부에서 직접 key를 주입할 때만 사용 (테스트 등)
    public LoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    // 정적 팩토리 메서드: 새로운 LoginKey 생성
    public static LoginKey generate() {
        return new LoginKey(createLoginKey());
    }

    private static String createLoginKey() {
        UUID uuid = UUID.randomUUID();
        return shortenUUID(uuid, 8);
    }

    private static String shortenUUID(UUID uuid, int length) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(uuid.toString().getBytes());
            String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
            return base64.substring(0, length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // equals, hashCode, toString 등도 구현 권장 (값 객체이므로)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginKey)) return false;
        LoginKey that = (LoginKey) o;
        return loginKey != null && loginKey.equals(that.loginKey);
    }

    @Override
    public int hashCode() {
        return loginKey != null ? loginKey.hashCode() : 0;
    }

    @Override
    public String toString() {
        return loginKey;
    }
}
