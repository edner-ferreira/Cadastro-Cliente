package com.cadastroCliente.cadastroClienteAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cadastroCliente.cadastroClienteAPI.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long> {

}
