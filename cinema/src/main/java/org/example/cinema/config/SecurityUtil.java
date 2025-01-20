package org.example.cinema.config;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public final class SecurityUtil {

  public static Long getCurrentUserId() {
    Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return (Long) object;
  }
}
