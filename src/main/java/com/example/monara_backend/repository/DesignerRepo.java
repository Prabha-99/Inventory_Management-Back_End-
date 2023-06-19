package com.example.monara_backend.repository;

import com.example.monara_backend.model.Designer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepo extends CrudRepository<Designer,Integer> {

}
