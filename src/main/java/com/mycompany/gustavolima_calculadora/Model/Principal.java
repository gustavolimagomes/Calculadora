/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.gustavolima_calculadora.Model;

import com.mycompany.gustavolima_calculadora.View.Tela;

/**
 *
 * @author Gustavo Lima Gomes <gustavolimagomesgl@gmail.com>
 * @date 02-11-2024
 */
public class Principal {
    
        public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }
}
