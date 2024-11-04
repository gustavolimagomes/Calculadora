/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.gustavolima_calculadora.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Gustavo Lima Gomes <gustavolimagomesgl@gmail.com>
 * @date 03-11-2024
 */
public class Log {
    private static final String LOG_FOLDER = "logs";
    private static final String LOG_FILE = "calculator_operations.csv";
    
    public Log() {
        criarPastaLog();
    }
    
    private void criarPastaLog() {
        File folder = new File(LOG_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
    
    public void registrarOperacao(String primeiroNumero, String operacao, String segundoNumero, String resultado) {
        String caminhoCompleto = LOG_FOLDER + File.separator + LOG_FILE;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto, true))) {
            File arquivo = new File(caminhoCompleto);
            if (arquivo.length() == 0) {
                writer.write("Data,Hora,Primeiro Número,Operação,Segundo Número,Resultado");
                writer.newLine();
            }
            
            LocalDateTime agora = LocalDateTime.now();
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            
            String linha = String.format("%s,%s,%s,%s,%s,%s",
                    agora.format(formatterData),
                    agora.format(formatterHora),
                    primeiroNumero,
                    operacao,
                    segundoNumero,
                    resultado);
            
            writer.write(linha);
            writer.newLine();
            
        } catch (IOException e) {
            System.err.println("Erro ao registrar operação: " + e.getMessage());
        }
    }
}
