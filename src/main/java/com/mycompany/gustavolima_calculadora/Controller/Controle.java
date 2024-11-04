/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gustavolima_calculadora.Controller;

import com.mycompany.gustavolima_calculadora.Model.Log;
import com.mycompany.gustavolima_calculadora.enums.EnumOperacao;

/**
 *
 * @author Gustavo Lima Gomes <gustavolimagomesgl@gmail.com>
 * @date 02-11-2024
 */
public class Controle {
    private Double total;
    private Double primeiroNumero;
    private boolean esperandoSegundoNumero;
    private EnumOperacao operacaoAtual;
    private Log log;

    public Controle() {
        zerar();
        log = new Log();
    }

      public double realizarOperacao(EnumOperacao operacao, Double valor) {
        // Se for operação de raiz quadrada, calcular imediatamente
        if (operacao == EnumOperacao.RAIZQUADRADA) {
            total = Math.sqrt(valor);
            log.registrarOperacao(
                String.valueOf(valor),
                "RAIZ",
                "",
                String.valueOf(total)
            );
            primeiroNumero = total;
            esperandoSegundoNumero = false;
            operacaoAtual = null;
            return total;
        }
        
        // Se estamos esperando o segundo número
        if (esperandoSegundoNumero) {
            calcular(valor);
            // Registra a operação completa
            log.registrarOperacao(
                String.valueOf(primeiroNumero),
                operacaoAtual != null ? operacaoAtual.toString() : "",
                String.valueOf(valor),
                String.valueOf(total)
            );
            operacaoAtual = operacao;
            if (operacao != null) {
                esperandoSegundoNumero = true;
                primeiroNumero = total;
            }
        } else {
            // Primeira operação ou após pressionar igual
            primeiroNumero = valor;
            total = valor;
            operacaoAtual = operacao;
            esperandoSegundoNumero = true;
        }
        return total;
    }

    private void calcular(Double segundoNumero) {
        if (operacaoAtual == null || primeiroNumero == null) {
            total = segundoNumero;
            return;
        }

        switch (operacaoAtual) {
            case ADICAO:
                total = primeiroNumero + segundoNumero;
                break;
            case SUBTRACAO:
                total = primeiroNumero - segundoNumero;
                break;
            case MULTIPLICACAO:
                total = primeiroNumero * segundoNumero;
                break;
            case DIVISAO:
                if (segundoNumero != 0) {
                    total = primeiroNumero / segundoNumero;
                }
                break;
            case PORCENTAGEM:
                total = (primeiroNumero * segundoNumero) / 100;
                break;
            case EXPONENCIACAO:
                total = Math.pow(primeiroNumero, segundoNumero);
                break;
            default:
                total = segundoNumero;
        }
    }

    public void zerar() {
        total = 0.0;
        primeiroNumero = null;
        esperandoSegundoNumero = false;
        operacaoAtual = null;
    }

    public double getTotal() {
        return this.total;
    }
}
