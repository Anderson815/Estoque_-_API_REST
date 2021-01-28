package com.anderson.estoque.repository;

import com.anderson.estoque.resource.ProdutoResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository <ProdutoResource, String>{
    void findByMarca(String marca);
}
