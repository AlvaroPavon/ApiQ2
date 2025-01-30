package org.example;


import org.example.QrCodeController;
import org.example.QrCodeRepository;
import org.example.QrCodeService;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(8080);

        // Crear instancias del repositorio y del servicio
        QrCodeRepository qrCodeRepository = new QrCodeRepository();
        QrCodeService qrCodeService = new QrCodeService(qrCodeRepository);

        // Registrar el controlador
        new QrCodeController(qrCodeService);

        get("/hello", (req, res) -> "¡Hola, mundo!");
    }
}
