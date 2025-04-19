package nl.davefemi.weatherdashboard.etl.utility;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Slf4j
public class EncryptorUtil {
    @Value("${spring.security.encryption.password}")
    private String password;
    @Value("${spring.security.encryption.salt}")
    private String salt;
    private TextEncryptor encryptor;

    @PostConstruct
    private void init(){
        if (password == null || salt == null){
            log.info("Password or Salt may not be null");
        }
        encryptor = Encryptors.text(password, salt);
    }

    public String encrypt(String plainText){
        return encryptor.encrypt(plainText);
    }

    public String decrypt(String cipherText){
        return encryptor.decrypt(cipherText);
    }
}
