package data;

final public class BiometricData {

    int faceKey;
    int fingerprintKey;

    public BiometricData(){
        faceKey = 0;
        fingerprintKey = 0;
    }

    public void setFaceKey(int key) {
        faceKey = key;
    }

    public void setFingerprintKey(int key) {
        fingerprintKey = key;
    }

    public int getFaceKey() {
        return faceKey;
    }

    public int getFingerprintKey() {
        return fingerprintKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiometricData bio = (BiometricData) o;
        return (bio.getFingerprintKey() == this.fingerprintKey) && (bio.getFaceKey() == this.faceKey);
    }
}
