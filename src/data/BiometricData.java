package data;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;

import java.util.Random;

public class BiometricData implements BiometricReader, BiometricScanner, BiometricSoftware {

    int faceKey;
    int fingerprintKey;

    public BiometricData(){
        faceKey = 0;
        fingerprintKey = 0;
    }


    @Override
    public BiometricData readBiometricData() {

    }

    @Override
    public void scanFace() {

    }

    @Override
    public void scanFingerprint() {

    }

    @Override
    public void verifyBiometricData() {

    }
}
