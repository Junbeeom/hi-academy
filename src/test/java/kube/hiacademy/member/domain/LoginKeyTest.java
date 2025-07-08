package kube.hiacademy.member.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoginKeyTest {
    @Test
    void createLoginKey_isNotNull() {
        LoginKey generatedKey = LoginKey.generate();

        assertNotNull(generatedKey);
    }

    @Test
    void loginkeySize_shouldBeEight() {
        LoginKey generatedKey = LoginKey.generate();

        assertThat(generatedKey.getSize()).isEqualTo(8);
    }

    @Test
    void createLoginKey_shouldBeUnique() {
        HashSet<Object> generatedKeys = new HashSet<>();

        for (int i = 0; i < 1000000; i++) {
            generatedKeys.add(LoginKey.generate());
        }

        assertThat(generatedKeys.size()).isEqualTo(1000000);
    }

}