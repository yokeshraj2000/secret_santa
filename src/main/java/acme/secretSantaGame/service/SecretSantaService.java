package acme.secretSantaGame.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import acme.secretSantaGame.exceptions.SecretSantaException;
import acme.secretSantaGame.model.CompanyEmployee;

public class SecretSantaService {
    private static final int MAX_ATTEMPTS = 100;

    public List<CompanyEmployee> assignSecretSantas(List<CompanyEmployee> employees,
            List<CompanyEmployee> previousAssignments) throws SecretSantaException {
        List<CompanyEmployee> result = new ArrayList<>(employees);
        Random random = new Random();

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            Collections.shuffle(result, random);

            boolean hasConflict = false;

            for (int i = 0; i < result.size(); i++) {
                CompanyEmployee giver = result.get(i);
                CompanyEmployee receiver = result.get((i + 1) % result.size());

                boolean isAssignedToSelf = giver.getEmail().equals(receiver.getEmail());
                boolean hadPreviousAssignment = previousAssignments.stream()
                        .anyMatch(pa -> pa.getEmail().equals(giver.getEmail()) &&
                                receiver.getEmail().equals(pa.getSecretChildEmail()));

                if (isAssignedToSelf || hadPreviousAssignment) {
                    System.out.println("Attempt " + (attempt + 1) + " - Conflict detected");
                    System.out.println("Giver: " + giver.getName() + " (" + giver.getEmail() + ")");
                    System.out.println("Receiver: " + receiver.getName() + " (" + receiver.getEmail() + ")");
                    System.out.println("Self-assignment: " + isAssignedToSelf);
                    System.out.println("Previous assignment conflict: " + hadPreviousAssignment);

                    hasConflict = true;
                    break;
                }

                giver.setSecretChild(receiver.getName(), receiver.getEmail());
            }

            if (!hasConflict) {
                return result;
            }
        }

        throw new SecretSantaException("Unable to assign Secret Santa without conflicts after multiple attempts.");
    }
}
