package com.anderson.estoque.repository;

import com.anderson.estoque.resource.ProdutoResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository <ProdutoResource, String>{
    boolean existsByMarca(String marca);
    List<ProdutoResource> findAllByMarca(String marca);
}
