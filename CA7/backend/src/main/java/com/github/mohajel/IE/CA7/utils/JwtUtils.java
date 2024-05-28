package com.github.mohajel.IE.CA7.utils;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyPairGenerator;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyPair;
import java.util.Date;
import java.util.List;

public class JwtUtils {

    static final int ACCESS_EXPIRATION_MS = 9600000;
    private static JwtUtils single_instance = null;
    Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    protected String publicKey;
    protected String privateKey;

    private JwtUtils() {
        this.generateKeys();
    }

    public static synchronized JwtUtils getInstance() {
        if (single_instance == null) {
            single_instance = new JwtUtils();
        }
        return single_instance;
    }

    public String generateAccessToken(String userName, List<String> roleArray) {
        return this.generateAccessToken(userName, roleArray, this.privateKey);
    }

    public boolean validateJwtToken(String authToken) {
        return this.validateJwtToken(authToken, this.publicKey);
    }

    // call it after validateJwtToken
    public String getSubject(String authToken) {
        return this.getSubject(authToken, this.publicKey);
    }

    private void generateKeys() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();

            this.publicKey = Base64.encodeBase64String(kp.getPublic().getEncoded());
            this.privateKey = Base64.encodeBase64String(kp.getPrivate().getEncoded());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateAccessToken(String userName, List<String> roleArray, String jwtPrivateKey) {
        try {
            return Jwts.builder()
                    .setSubject(userName)
                    .setIssuer("MIZDOONI")
                    .claim("roles", roleArray)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + ACCESS_EXPIRATION_MS))
                    .signWith(SignatureAlgorithm.RS256, generateJwtKeyEncryption(jwtPrivateKey))
                    .compact();
        } catch (Exception e) {
            logger.warn("Cannot create JWT token: " + e.getMessage());
        }
        return "NO_VALID_TOKEN";
    }

    private PublicKey generateJwtKeyDecryption(String jwtPublicKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] keyBytes = Base64.decodeBase64(jwtPublicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    private PrivateKey generateJwtKeyEncryption(String jwtPrivateKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] keyBytes = Base64.decodeBase64(jwtPrivateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    private boolean validateJwtToken(String authToken, String jwtPublicKey) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(generateJwtKeyDecryption(jwtPublicKey))
                    .parseClaimsJws(authToken);
            logger.info("CLAIM:" + claims);
            return true;
        } catch (SignatureException e) {
            logger.warn("Invalid JWT signature: {}" + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.warn("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.warn("JWT token is expired: {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.warn("JWT token is unsupported: {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.warn("JWT claims string is empty: {}" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            logger.warn("no such algorithm exception");
        } catch (InvalidKeySpecException e) {
            logger.warn("invalid key exception");
        }
        return false;
    }

    private String getSubject(String authToken, String jwtPublicKey) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(generateJwtKeyDecryption(jwtPublicKey))
                    .parseClaimsJws(authToken);
            return claims.getBody().getSubject();
        } catch (Exception e) {
            logger.warn("UNKNOWN Exception (call it after validateJwtToken):" + e.getMessage());
        }
        return "";
    }
}