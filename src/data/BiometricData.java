package data;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;

public class BiometricData implements BiometricReader, BiometricScanner, BiometricSoftware {

    int faceKey;
    int fingerprintKey;

    public BiometricData(){
        faceKey = 0;
        fingerprintKey = 0;
    }


    @Override
    public BiometricData readBiometricData() {
        return null;
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
