/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ryc.restful.ws_restful.util;

/*import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
*/
import com.ryc.restful.ws_restful.modelo.Employees;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;

import java.util.UUID;
/**
 *
 * @author RyC
 */
public class CustomGeneratorToken {
    
    /*private Key key;
    private String jws;
    
    public String getToken(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        jws = Jwts.builder().setSubject("ryc").signWith(key).compact();
        
        System.out.println(jws);
        
        return jws;
    }*/
    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    
    public String getToken(Employees user){
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        
        String uid = String.valueOf(user.getId());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(uid)
                .setIssuedAt(now)
                .setSubject(user.getFirst_name())
                .setSubject(user.getEmail())
                .setSubject(user.getPassword())
                .setIssuer(Constantes.TOKEN_ISSUER)
                .signWith(signingKey, signatureAlgorithm);

        //if it has been specified, let's add the expiration
        if (Constantes.TIME_SESSION_TOKEN >= 0) {
            long expMillis = nowMillis + Constantes.TIME_SESSION_TOKEN;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
    
    public static Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .parseClaimsJws(jwt).getBody();
        return claims;
    }
    
    
}
