package br.com.soma.contadigital.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.soma.contadigital.portal.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
