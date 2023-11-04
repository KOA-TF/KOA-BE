package com.koa.coremodule.auth.domain.jwt;

import com.koa.commonmodule.exception.Error;
import com.koa.coremodule.auth.domain.entity.TokenType;
import com.koa.coremodule.auth.domain.jwt.exception.ExpiredTokenException;
import com.koa.coremodule.auth.domain.jwt.exception.InvalidTokenException;
import com.koa.coremodule.auth.domain.service.TokenDeleteService;
import com.koa.coremodule.auth.domain.service.TokenQueryService;
import com.koa.coremodule.auth.domain.service.TokenSaveService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JWTProvider {
    private final JWTProperties jwtProperties;
    private final TokenSaveService tokenSaveService;
    private final TokenQueryService tokenQueryService;
    private final TokenDeleteService tokenDeleteService;
    /**
     * access token 생성
     *
     */
    public String generateAccessToken(final String email) {
        final Claims claims = createPrivateClaims(email, TokenType.ACCESS_TOKEN);
        return generateToken(claims, jwtProperties.getAccessTokenExpirationTime());
    }

    /**
     * refershToken 생성
     * refreshToken 만료 기간이 길기 때문에 redis에서 관리할 필요성이 떨어져 DB에 저장함
     */
    public String generateRefreshToken(final String email) {
        final Claims claims = createPrivateClaims(email, TokenType.REFRESH_TOKEN);
        final String refreshToken = generateToken(claims, jwtProperties.getRefreshTokenExpirationTime());
        tokenSaveService.saveToken(refreshToken, email, TokenType.REFRESH_TOKEN);
        return refreshToken;
    }

    /**
     * refresh token을 이용하여 token 재발급
     *
     */
    public String reIssueAccessToken (final String refreshToken) {
        validateToken(refreshToken);
        final String email = extractEmailFromRefreshToken(refreshToken);
        return generateAccessToken(email);
    }

    public String reIssueRefreshToken (final String refreshToken) {
        validateToken(refreshToken);
        Date expireDate = getExpiration(refreshToken);
        Date currentDate = new Date();
        if (expireDate.getTime() - currentDate.getTime() < jwtProperties.getReissueRefreshTokenExpirationTime()) {
            tokenDeleteService.deleteTokenByValue(refreshToken);
            final String email = extractEmailFromRefreshToken(refreshToken);
            return generateRefreshToken(email);
        }
        else return refreshToken;
    }

    /**
     * accessToken에서 email 추출
     */
    public String extractEmailFromAccessToken(final String accessToken){
        return initializeJwtParser()
                .parseClaimsJws(accessToken)
                .getBody()
                .get(JWTConsts.EMAIL)
                .toString();
    }


    /**
     * DB에서 refresh token(value)를 이용한 email(key) 추출
     *
     */
    private String extractEmailFromRefreshToken(String refreshToken) {
        final String email = tokenQueryService.findEmailByValue(refreshToken, TokenType.REFRESH_TOKEN);
        return email;
    }

    /**
     * 기본 jwt claims 스팩에서 개발자가 더 추가한 claim을 private claim이라고 함
     * <br>private claims : email, tokenType + issuer
     *
     * @param email     토큰에 담길 email
     * @param tokenType 토큰 타입(ACCESS_TOKEN, REFRESH_TOKEN)
     */
    private Claims createPrivateClaims(final String email, final TokenType tokenType) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(JWTConsts.EMAIL, email);
        claims.put(JWTConsts.TOKEN_TYPE, tokenType.name());
        return Jwts.claims(claims)
                .setIssuer(JWTConsts.TOKEN_ISSUER);
    }

    /**
     * 토큰 생성 메소드
     *
     */
    private String generateToken(final Claims claims, final Long expirationTime) {
        final Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    /**
     * @return 서명에 사용할 Key 반환
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
    }

    public void validateToken(final String token) {
        final JwtParser jwtParser = initializeJwtParser();
        try {
            jwtParser.parse(token);
        } catch (MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new InvalidTokenException(Error.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException(Error.EXPIRED_TOKEN);
        }
    }

    /**
     * iss, key 설정
     */
    private JwtParser initializeJwtParser() {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .requireIssuer(JWTConsts.TOKEN_ISSUER)
                .build();
    }

    private Date getExpiration(final String token) {
        return initializeJwtParser()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
}
