package WaterDepo.auth;

import Models.User.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private final SecretKey secret_key = Jwts.SIG.HS256.key().build();
  private long accessTokenValididy = 60 * 60 * 100;

  private final JwtParserBuilder jwtParser;

  private final String TOKEN_HEADER = "Authorization";
  private final String TOKEN_PREFIX = "Bearer ";

  public JwtUtil() {
    this.jwtParser = Jwts.parser().decryptWith(secret_key);
  }

  public String createToken(User user) {
    Date tokenCreateTime = new Date();
    Date tokenValidity =
        new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValididy));
    return Jwts.builder()
        .claim("firstName", user.getName())
        .subject(user.getEmail())
        .expiration(tokenValidity)
        .signWith(secret_key)
        .compact();
  }

  private Claims parseJwtClaims(String token) {
    return jwtParser.build().parseUnsecuredClaims(token).getPayload();
  }

  public Claims resolveClaims(HttpServletRequest req) {
    try {
      String token = resolveToken(req);
      if (token != null) {
        return parseJwtClaims(token);
      }
      return null;
    } catch (ExpiredJwtException ex) {
      req.setAttribute("expired", ex.getMessage());
      throw ex;
    } catch (Exception ex) {
      req.setAttribute("invalid", ex.getMessage());
      throw ex;
    }
  }

  public String resolveToken(HttpServletRequest request) {

    String bearerToken = request.getHeader(TOKEN_HEADER);
    if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
      return bearerToken.substring(TOKEN_PREFIX.length());
    }
    return null;
  }

  public List<String> getEmail(Claims claims) {
    return (List<String>) claims.get("roles");
  }
}
