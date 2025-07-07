package kube.hiacademy.member.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

@Embeddable
public record LoginKey(String loginKey) {

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
}
