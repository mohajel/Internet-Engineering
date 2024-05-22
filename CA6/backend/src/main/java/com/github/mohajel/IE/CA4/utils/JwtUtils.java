package com.github.mohajel.IE.CA4.utils;

import io.jsonwebtoken.*;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.List;

public class JwtUtils {

    int accessExpirationMs = 9600000;

    public String generateAccessToken(String userName, List<String> roleArray, String jwtPrivateKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuer("MIZDOONI")
                .claim("roles", roleArray)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + accessExpirationMs))
                .signWith(SignatureAlgorithm.RS256, generateJwtKeyEncryption(jwtPrivateKey))
                .compact();
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

    public boolean validateJwtToken(String authToken, String jwtPublicKey) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(generateJwtKeyDecryption(jwtPublicKey))
                    .parseClaimsJws(authToken);
            System.out.println("CLAIM:" + claims);
            System.out.println("Subject:" + claims.getBody().getSubject());
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {}" + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("no such algorithm exception");
        } catch (InvalidKeySpecException e) {
            System.out.println("invalid key exception");
        }

        return false;
    }

    // call it after validateJwtToken
    public String getSubject(String authToken, String jwtPublicKey) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(generateJwtKeyDecryption(jwtPublicKey))
                    .parseClaimsJws(authToken);
            return claims.getBody().getSubject();
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {}" + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("no such algorithm exception");
        } catch (InvalidKeySpecException e) {
            System.out.println("invalid key exception");
        } catch (Exception e) {
            System.out.println("UNKNOWN Exception :" + e.getMessage());
        }
        return "";
    }
}