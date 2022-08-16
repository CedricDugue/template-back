package br.com.trackertemplateback.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Cedric Christian on 28/12/2021
 */
@SwaggerDefinition(
        tags = {
                @Tag(name = "usersecurity", description = "Controller for User Security")
        }
)

@RestController
@RequestMapping("/v1/trackertemplateback/usersecurity")
@Api(tags = {"usersecurity"})
public class UserSecurityEndPoint {
    /**
     * Efetua logout.
     * Exemplo: http://localhost:8080/v1/trackertemplateback/usersecurity/logout
     */
    @PostMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> fetchSignoutSite(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();

        session = request.getSession(false);
        if (session != null)
            session.invalidate();

        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}