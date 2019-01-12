package kiosk;

import data.BiometricData;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;

import Exception.BiometricVerificationFailedException;

public class BiometricProcessor {
    BiometricReader bReader;
    BiometricScanner bScanner;
    BiometricSoftware bSofware;
    BiometricData bDataPassport;
    BiometricData bData;

    public BiometricProcessor() {
    }

    public void setbReader(BiometricReader bReader) {
        this.bReader = bReader;
    }

    public void setbScanner(BiometricScanner bScanner) {
        this.bScanner = bScanner;
    }

    public void setbSofware(BiometricSoftware bSofware) {
        this.bSofware = bSofware;
    }

    public boolean automaticVerification() throws BiometricVerificationFailedException {
        bDataPassport = bReader.readBiometricData();
        bData.setFaceKey(bScanner.scanFace());
        bData.setFingerprintKey(bScanner.scanFingerprint());

        return bSofware.verifyBiometricData(bDataPassport, bData);
    }
}
