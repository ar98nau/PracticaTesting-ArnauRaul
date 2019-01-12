package Test;

import data.BiometricData;
import kiosk.BiometricProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BiometricReader;
import services.BiometricScanner;
import services.BiometricSoftware;
import Exception.BiometricVerificationFailedException;

import static org.junit.jupiter.api.Assertions.*;

class BiometricProcessorTest {
    BiometricProcessor bProc;
    BiometricReader bRd;
    BiometricScanner bScn;
    BiometricSoftware bSw;

    @BeforeEach
    void init() {
        bProc = new BiometricProcessor();
        bRd = new BiometricReaderTest();
        bScn = new BiometricScannerTest();
        bSw = new BiometricSoftwareTest();
    }

    @Test
    void setbReaderTest() {
        bProc.setbReader(bRd);
        assertEquals(bRd, bProc.getbReader());
    }

    @Test
    void setbScannerTest() {
        bProc.setbScanner(bScn);
        assertEquals(bScn, bProc.getbScanner());
    }

    @Test
    void setbSofwareTest() {
        bProc.setbSoftware(bSw);
        assertEquals(bSw, bProc.getbSofware());
    }

    @Test
    void automaticVerificationTest() throws BiometricVerificationFailedException {
        bProc.setbSoftware(bSw);
        bProc.setbReader(bRd);
        bProc.setbScanner(bScn);
        assertTrue(bProc.automaticVerification());
        assertTrue(((BiometricReaderTest)bRd).isReadPassport());
        assertTrue(((BiometricScannerTest)bScn).isFaceScanned());
        assertTrue(((BiometricScannerTest)bScn).isFingerScanned());
    }

    private class BiometricReaderTest implements BiometricReader {
        boolean Passportread = false;

        @Override
        public BiometricData readBiometricData() {
            Passportread = true;
            return new BiometricData();
        }

        public boolean isReadPassport() {
            return Passportread;
        }
    }

    private class BiometricScannerTest implements BiometricScanner {
        boolean faceScanned = false;
        boolean fingerScanned = false;

        @Override
        public int scanFace() {
            faceScanned = true;
            return 1234;
        }

        @Override
        public int scanFingerprint() {
            fingerScanned = true;
            return 5678;
        }

        public boolean isFaceScanned() {
            return faceScanned;
        }

        public boolean isFingerScanned() {
            return fingerScanned;
        }
    }

    private class BiometricSoftwareTest implements BiometricSoftware {
        boolean verified = true;

        @Override
        public boolean verifyBiometricData(BiometricData bPerson, BiometricData bPassport) throws BiometricVerificationFailedException {
            return verified;
        }
    }
}