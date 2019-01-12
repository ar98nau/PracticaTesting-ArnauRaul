package services;

import Exception.BiometricVerificationFailedException;
import data.Nif;

public interface BiometricSoftware {
    void verifyBiometricData(Nif passport) throws BiometricVerificationFailedException;
}
