package com.KissTech.crm.service;

import com.KissTech.crm.jwt.JwtUtils;
import com.KissTech.crm.model.Session;
import com.KissTech.crm.model.User;
import com.KissTech.crm.repository.SessionRepository;
import com.KissTech.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionService {

private final UserRepository userRepository;
private final SessionRepository activeSessionRepository;


    public SessionService(UserRepository userRepository, SessionRepository activeSessionRepository) {
        this.userRepository = userRepository;
        this.activeSessionRepository = activeSessionRepository;
    }


    @Autowired
    private JwtUtils jwtUtils;

    // Get the active session for a user
    public String getActiveSession(UUID userId) {
        Session session = activeSessionRepository.findByUserId(userId);
        return session != null ? session.getToken() : null;
    }



    // Save a new session
    public void saveActiveSession(UUID userId, String token) {
        Session activeSession = new Session();
        activeSession.setUser(userRepository.findById(userId).get());
        activeSession.setToken(token);
        activeSessionRepository.save(activeSession);
    }

    // Invalidate (delete) the active session
    public void invalidateSession(UUID userId) {
        Session session = activeSessionRepository.findByUserId(userId);
        if (session != null) {
            activeSessionRepository.delete(session);
        }
    }

    // Check if a token is valid for a user
    public boolean isTokenValidForUser(String username, String token) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false; // User not found
        }

        String activeToken = getActiveSession(user.getId());

        // Validate the token
        return activeToken != null && activeToken.equals(token) && jwtUtils.validateJwtToken(token);
    }


}
