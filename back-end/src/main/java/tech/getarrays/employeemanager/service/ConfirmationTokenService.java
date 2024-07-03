package tech.getarrays.employeemanager.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import tech.getarrays.employeemanager.repo.ConfirmationTokenRepo;
import tech.getarrays.employeemanager.model.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private  ConfirmationTokenRepo confirmationTokenRepo;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepo.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepo.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepo.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
