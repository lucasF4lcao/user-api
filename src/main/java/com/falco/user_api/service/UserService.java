package com.falco.user_api.service;

import com.falco.user_api.dto.UserRequest;
import com.falco.user_api.dto.UserResponse;
import com.falco.user_api.model.User;
import com.falco.user_api.model.Referral;
import com.falco.user_api.repository.UserRepository;
import com.falco.user_api.repository.ReferralRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ReferralRepository referralRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ReferralRepository referralRepository) {
        this.userRepository = userRepository;
        this.referralRepository = referralRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserResponse createUser(UserRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPoints(0);
        user.setReferralCode(generateReferralCode());

        userRepository.save(user);

        if (request.getReferralCode() != null && !request.getReferralCode().isEmpty()) {
            Optional<User> referrerOpt = userRepository.findByReferralCode(request.getReferralCode());
            if (referrerOpt.isPresent()) {
                User referrer = referrerOpt.get();
                referrer.setPoints(referrer.getPoints() + 1);
                userRepository.save(referrer);

                Referral referral = new Referral();
                referral.setReferrer(referrer);
                referral.setReferred(user);
                referralRepository.save(referral);
            }
        }

        return new UserResponse(user.getName(), user.getPoints(), user.getReferralCode());
    }

    public UserResponse getUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return new UserResponse(user.getName(), user.getPoints(), user.getReferralCode());
    }

    private String generateReferralCode() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
