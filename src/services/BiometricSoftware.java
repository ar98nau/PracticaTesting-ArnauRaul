package services;

import Exception.BiometricVerificationFailedException;
import data.BiometricData;

public interface BiometricSoftware {
    boolean verifyBiometricData(BiometricData bPerson, BiometricData bPassport) throws BiometricVerificationFailedException;
}
