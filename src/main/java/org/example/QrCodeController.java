package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Spark;

import java.time.LocalDateTime;

import static spark.Spark.*;


public class QrCodeController {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public QrCodeController(final QrCodeService qrCodeService) {
        Spark.port(Integer.parseInt(System.getenv().getOrDefault("PORT","4567")));

        // Endpoint para crear un nuevo QR Code
        post("/qr-codes", (req, res) -> {
            QrCode qrCode = gson.fromJson(req.body(), QrCode.class);
            qrCodeService.saveQrCode(qrCode);
            res.status(201);
            return gson.toJson(qrCode);
        });

        // Endpoint para obtener todos los QR Codes
        get("/qr-codes", (req, res) -> {
            return gson.toJson(qrCodeService.getAllQrCodes());
        });

        // Endpoint para obtener un QR Code por su ID
        get("/qr-codes/:id", (req, res) -> {
            Long id = Long.parseLong(req.params(":id"));
            QrCode qrCode = qrCodeService.getQrCodeById(id);
            if (qrCode != null) {
                return gson.toJson(qrCode);
            } else {
                res.status(404);
                return "QR Code no encontrado";
            }
        });

        // Endpoint para actualizar un QR Code
        put("/qr-codes/:id", (req, res) -> {
            Long id = Long.parseLong(req.params(":id"));
            QrCode qrCode = gson.fromJson(req.body(), QrCode.class);
            qrCode.setId(id);
            qrCodeService.updateQrCode(qrCode);
            return gson.toJson(qrCode);
        });

        // Endpoint para eliminar un QR Code
        delete("/qr-codes/:id", (req, res) -> {
            Long id = Long.parseLong(req.params(":id"));
            QrCode qrCode = qrCodeService.getQrCodeById(id);
            if (qrCode != null) {
                qrCodeService.deleteQrCode(qrCode);
                return "QR Code eliminado";
            } else {
                res.status(404);
                return "QR Code no encontrado";
            }
        });
    }
}
