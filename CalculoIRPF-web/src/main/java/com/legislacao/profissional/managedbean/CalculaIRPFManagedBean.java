/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.legislacao.profissional.managedbean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Rafael_Schulte
 */
@ManagedBean(name = "calculaIRPF")
@RequestScoped
public class CalculaIRPFManagedBean {

    private Double inputRendimento;
    private Double inputNumeroDependentes;
    private Double inputOutrosDescontos;
    private Double inputValorIRPF;
    private Double inputValorIRPFAnual;
    private Double inputValorRestituicao;
    private Double inputValorRestituicaoAnual;
    private Double valorRestituicao;

    @PostConstruct
    public void init() {
        this.inputRendimento = 0d;
        this.inputOutrosDescontos = 0d;
        this.inputNumeroDependentes = 0d;
    }

    public void onClickCalcularIRPFButton() {
        inputValorIRPF = calculaIRPF(getInputRendimento(), getInputNumeroDependentes(), getInputOutrosDescontos());
        inputValorIRPFAnual = (inputValorIRPF * 12);
        inputValorRestituicao = valorRestituicao;
        inputValorRestituicaoAnual = (valorRestituicao * 12);
        System.out.println("Valor IRPF: " + inputValorIRPF);
        System.out.println("Valor IRPF Anual: " + inputValorIRPFAnual);
    }

    public void onClickLimparButton() {
        inputRendimento = 0d;
        inputValorIRPF = 0d;
        inputValorIRPFAnual = 0d;
        inputValorRestituicao = 0d;
    }

    public Double calculaIRPF(Double rendimento, Double nDependentes, Double outrosDescontos) {
        Double descontoINSS = calculaDescontosINSS(rendimento);
        Double rendimentoComDescontos;
        Double valorIRPF = 0d;
        nDependentes = (nDependentes * 189.59);

        rendimentoComDescontos = ((rendimento - descontoINSS) - (outrosDescontos));

        // calcula taxa de IR de acordo com o rendimento
        if (rendimentoComDescontos > 1903.99 && rendimentoComDescontos <= 2826.65) {
            valorRestituicao = 142.8;
            valorIRPF = ((rendimentoComDescontos * 0.075) - valorRestituicao);
        }
        if (rendimentoComDescontos >= 2826.66 && rendimentoComDescontos <= 3751.05) {
            valorRestituicao = 354.8;
            valorIRPF = ((rendimentoComDescontos * 0.15) - valorRestituicao);
        }
        if (rendimentoComDescontos >= 3751.06 && rendimentoComDescontos <= 4664.68) {
            valorRestituicao = 636.13;
            valorIRPF = ((rendimentoComDescontos * 0.225) - valorRestituicao);
        }
        if (rendimentoComDescontos > 4664.69) {
            valorRestituicao = 869.36;
            valorIRPF = ((rendimentoComDescontos * 0.275) - valorRestituicao);
        }
        valorIRPF = (valorIRPF - nDependentes);
        if (valorIRPF < 0) {
            return 0d;
        } else {
            return valorIRPF;
        }
    }

    public Double calculaDescontosINSS(Double rendimento) {
        Double valorINSSDescontado = 0d;
        if (rendimento > 0 && rendimento <= 1693.72) {
            valorINSSDescontado = ((rendimento * 0.08));
        } else if (rendimento >= 1693.73 && rendimento <= 2822.9) {
            valorINSSDescontado = ((rendimento * 0.09));
        } else if (rendimento >= 2822.91 && rendimento <= 5645.8) {
            valorINSSDescontado = ((rendimento * 0.11));
        } else if (rendimento > 5645.8) {
            valorINSSDescontado = 621.04;
        }
        return valorINSSDescontado;
    }

    public Double getInputRendimento() {
        return inputRendimento;
    }

    public void setInputRendimento(Double inputRendimento) {
        this.inputRendimento = inputRendimento;
    }

    public Double getInputOutrosDescontos() {
        return inputOutrosDescontos;
    }

    public void setInputOutrosDescontos(Double inputOutrosDescontos) {
        this.inputOutrosDescontos = inputOutrosDescontos;
    }

    public Double getInputNumeroDependentes() {
        return inputNumeroDependentes;
    }

    public void setInputNumeroDependentes(Double inputNumeroDependentes) {
        this.inputNumeroDependentes = inputNumeroDependentes;
    }

    public Double getInputValorIRPF() {
        return inputValorIRPF;
    }

    public void setInputValorIRPF(Double inputValorIRPF) {
        this.inputValorIRPF = inputValorIRPF;
    }

    public Double getInputValorIRPFAnual() {
        return inputValorIRPFAnual;
    }

    public void setInputValorIRPFAnual(Double inputValorIRPFAnual) {
        this.inputValorIRPFAnual = inputValorIRPFAnual;
    }

    public Double getInputValorRestituicao() {
        return inputValorRestituicao;
    }

    public void setInputValorRestituicao(Double inputValorRestituicao) {
        this.inputValorRestituicao = inputValorRestituicao;
    }

    public Double getInputValorRestituicaoAnual() {
        return inputValorRestituicaoAnual;
    }

    public void setInputValorRestituicaoAnual(Double inputValorRestituicaoAnual) {
        this.inputValorRestituicaoAnual = inputValorRestituicaoAnual;
    }

    public Double getValorRestituicao() {
        return valorRestituicao;
    }

    public void setValorRestituicao(Double valorRestituicao) {
        this.valorRestituicao = valorRestituicao;
    }

}
