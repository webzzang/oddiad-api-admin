package com.exflyer.oddi.admin.share;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class AesEncryptorTest {

  @Autowired
  private AesEncryptor aesEncryptor;

  @DisplayName("")
  @Test
  public void encrypt() throws Exception {
    String phone = "jisoon0923@gmail.com";

    // given
    String encryptString = aesEncryptor.encrypt(phone);
    String encryptString2 = aesEncryptor.encrypt(phone);
    String twiceEncryptedString = aesEncryptor.encrypt(encryptString);

    log.info("encryptString : {}", encryptString);
    log.info("encryptString2 : {}", encryptString2);
    log.info("twiceEncryptedString : {}", twiceEncryptedString);

    String encryptString220 = "8DWwYEXXhdK3/JXNxjawFQ==";
    String decryptString = aesEncryptor.decrypt(encryptString220);
    String decryptString2 = aesEncryptor.decrypt(encryptString220);
    String twiceDecryptedString = aesEncryptor.decrypt(decryptString);
    log.info("decryptString : {}", decryptString);
    log.info("decryptString2 : {}", decryptString2);
    log.info("twiceDecryptedString : {}", twiceDecryptedString);
  }

}
