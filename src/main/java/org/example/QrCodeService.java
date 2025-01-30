package org.example;


import org.example.QrCode;
import org.example.QrCodeRepository;

import java.util.List;

public class QrCodeService {
    private QrCodeRepository qrCodeRepository;

    public QrCodeService(QrCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    public void saveQrCode(QrCode qrCode) {
        qrCodeRepository.save(qrCode);
    }

    public List<QrCode> getAllQrCodes() {
        return qrCodeRepository.findAll();
    }

    public QrCode getQrCodeById(Long id) {
        return qrCodeRepository.findById(id);
    }

    public void updateQrCode(QrCode qrCode) {
        qrCodeRepository.update(qrCode);
    }

    public void deleteQrCode(QrCode qrCode) {
        qrCodeRepository.delete(qrCode);
    }
}

