<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.13.1</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <version>2.6.3</version>
        <scope>test</scope>
    </dependency>
</dependencies>
















package com.example.bankcardauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class BankCardApplication {

    private static final Map<String, String> bankCodes = new HashMap<>();
    static {
        bankCodes.put("1234", "Bank A");
        bankCodes.put("5678", "Bank B");
        bankCodes.put("9101", "Bank C");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody BankCardAuthenticationRequest request) {
        String cardNumber = request.getCardNumber();
        String bankCode = cardNumber.substring(0, 4);

        if (bankCodes.containsKey(bankCode) && bankCodes.get(bankCode).equals(request.getBankCode())) {
            return new ResponseEntity<>(new BankCardAuthenticationResponse("Authentication successful!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new BankCardAuthenticationResponse("Authentication failed!"), HttpStatus.UNAUTHORIZED);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BankCardApplication.class, args);
    }
}

class BankCardAuthenticationRequest {
    private String cardNumber;
    private String bankCode;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}

class BankCardAuthenticationResponse {
    private String message;

    public BankCardAuthenticationResponse(String message) {
        this.message = message;
    }

    public

