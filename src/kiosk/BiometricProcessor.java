package kiosk;

import Exception.BiometricVerificationFailedException;
import data.BiometricData;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;

public class BiometricProcessor {
    BiometricReader bReader;
    BiometricScanner bScanner;
    BiometricSoftware bSoftware;
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

    public void setbSoftware(BiometricSoftware bSoftware) {
        this.bSoftware = bSoftware;
    }

    public BiometricReader getbReader() {
        return bReader;
    }

    public BiometricScanner getbScanner() {
        return bScanner;
    }

    public BiometricSoftware getbSofware() {
        return bSofware;
    }

    public boolean automaticVerification() throws BiometricVerificationFailedException {
        bDataPassport = bReader.readBiometricData();
        bData.setFaceKey(bScanner.scanFace());
        bData.setFingerprintKey(bScanner.scanFingerprint());

        return bSoftware.verifyBiometricData(bDataPassport, bData);
    }
}
