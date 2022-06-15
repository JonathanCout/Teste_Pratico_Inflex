/*
Autor: Jonathan Quintal Coutinho
O código a seguir foi feito para a etapa de Teste Prático da vaga Desenvolvedor PL/SQL e Java
Email de contato: jqcoutinho.eng@gmail.com
 */
package com.company;

import netscape.javascript.JSObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Instanciando nossa Lista de funcionarios
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        // Criando todos os funcionarios
        Funcionario maria = new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        Funcionario joao = new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador");
        Funcionario caio = new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador");
        Funcionario miguel = new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor");
        Funcionario alice = new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista");
        Funcionario heitor = new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador");
        Funcionario arthur = new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador");
        Funcionario laura = new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente");
        Funcionario heloisa = new Funcionario("Heloísa", LocalDate.of(2003, 5, 25), new BigDecimal("1606.85"), "Eletricista");
        Funcionario helena = new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente");

        // Adicionando todos os funcionários na lista
        Collections.addAll(listaFuncionarios, maria, joao, caio, miguel, alice, heitor, arthur, laura, heloisa, helena);

        // Removendo o funcionario João. Caso se queira adicionar o João às comparações seguintes, comentar o código
        listaFuncionarios.remove(joao);

        // Laço for a seguir foi utilizado para diversos requisitos

        // Map criado para organizar os funcionarios por função
        System.out.println("Lista dos funcionarios:");
        Map<String,List<Funcionario>> mapRole = new HashMap<>();
        for (Funcionario funcionario : listaFuncionarios) {

            //Exibindo a lista completa de funcionarios, formatações constam no metodo toString da classe
            System.out.println(funcionario.toString());

            //Adicionando um aumento de 10% no salário de cada funcionario
            funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.valueOf(1.10)));

            /* Condicional para organização dos dados no map, caso a função não esteja presente no map, ele a adiciona, caso esteja a lista
            é recuperada e é adicionado o novo funcionário */
            if(!mapRole.containsKey(funcionario.getFuncao())){
                List<Funcionario> tmp = new ArrayList<>();
                tmp.add(funcionario);
                mapRole.put(funcionario.getFuncao(),tmp);
            } else {
                mapRole.get(funcionario.getFuncao()).add(funcionario);
            }
        }
        //Ordenando as keys por ordem alfabética para melhor leitura
        TreeMap<String, List<Funcionario>> sortedByKey = new TreeMap<>(mapRole);
        System.out.println("\nFuncionarios por funcao:");
        for(Map.Entry<String,List<Funcionario>> entry : sortedByKey.entrySet()){
            System.out.println(entry);
        }

        //Exibindo funcionarios com mes de aniversario 10 ou 12
        System.out.println("\nFuncionarios aniversariantes no mes 10 ou 12:");
        for(Funcionario func : listaFuncionarios){
            if(func.getDataNascimento().getMonthValue() == 10 || func.getDataNascimento().getMonthValue() == 12){
                System.out.println( func);
            }
        }

        // Exibindo o nome e a idade do funcionário de maior idade
        Map<String,Integer> mapAge = new HashMap<>();
        for(Funcionario func : listaFuncionarios){
            mapAge.put(func.getNome(),Period.between(func.getDataNascimento(),LocalDate.now()).getYears());
        }
        System.out.println("\nFuncionario de maior idade: \n" + mapAge.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null) + " anos");

        // Comparator criado para organizar a lista considerando somente o nome do funcionario
        Comparator<Funcionario> compareByName = Comparator.comparing(Funcionario::getNome);
        // Organizando a lista e exibindo-a depois
        listaFuncionarios.sort(compareByName);
        System.out.println("\nLista agrupada pelos nomes dos funcionários:");
        for(Funcionario func: listaFuncionarios){
            System.out.println(func);
        }

        // Lista criada para armazenar o valor de todos os salários e o reduzir para o valor máximo, exibindo-o
        List<BigDecimal> salaryList = new ArrayList<>();
        // Processo para adicionar todos os salarios na lista, iterando pela lista de funcionarios
        for(Funcionario func: listaFuncionarios){
            salaryList.add(func.getSalario());
        }
        System.out.println("\nSalario total = R$" + salaryList.stream().reduce(BigDecimal.ZERO,BigDecimal::add).setScale(2,RoundingMode.HALF_UP));


        // Map criado para exibir quantos salários minimos cada funcionário recebe, exibindo seu nome e a quantidade de salários mínimos recebidos
        System.out.println("\nFuncionários e a quantidade de salários minimos cada um ganha:\n");
        Map<String,BigDecimal> mapSalary = new HashMap<>();
        for(Funcionario func : listaFuncionarios){
            mapSalary.put(func.getNome(), func.getSalario().divide(BigDecimal.valueOf(1212.00),2, RoundingMode.HALF_DOWN));
        }
        System.out.println(mapSalary);



    }
}
