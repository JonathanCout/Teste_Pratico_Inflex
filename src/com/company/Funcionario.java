package com.company;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa{

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    // O método toString consta com 2 alterações, a adição do Nome e data de Nascimento, tanto como a formatação das datas e dos salários
    @Override
    public String toString() {
        return
                "Nome = " + this.getNome() +
                ", Data de Nascimento = " + this.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Salario = " + new DecimalFormat("##,##0.00").format(salario) +
                ", Funcao = '" + funcao + '\''
                ;
    }
}
