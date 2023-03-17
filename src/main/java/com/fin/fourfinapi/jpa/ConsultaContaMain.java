package com.fin.fourfinapi.jpa;

import com.fin.fourfinapi.FourFinApiApplication;
import com.fin.fourfinapi.domain.model.Conta;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class ConsultaContaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext =new SpringApplicationBuilder(FourFinApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);


        CadastroConta cadastroConta = applicationContext.getBean(CadastroConta.class);

        //Teste de listar
        List<Conta> contas = cadastroConta.listar();

        for(Conta conta : contas) {
            System.out.println(conta.getNome());
        }

        //Teste de adicionar
        Conta conta1 = new Conta();
        conta1.setNome("Caixa");
        conta1.setValorInicial(BigDecimal.valueOf(200.00));

        conta1 = cadastroConta.salvar(conta1);

        //teste de buscar

        Conta conta2 = cadastroConta.buscar(1L);

        System.out.println(conta2.getNome());

        //teste de editar
        Conta conta3 = new Conta();
        conta3.setValorInicial(contas.get(2).getValorInicial());
        conta3.setId(3L);
        conta3.setNome("Bradesco");
        cadastroConta.salvar(conta3);

        //teste de excluir
        Conta conta4 = new Conta();
        conta4.setId(2L);
        cadastroConta.remover(conta4);

        //Lista final
        List<Conta> contas2 = cadastroConta.listar();

        for(Conta conta : contas2) {
            System.out.println(conta.getNome());
        }

    }
}
