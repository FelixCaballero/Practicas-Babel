package com.babel.crudfullstack.angular.repository;

import com.babel.crudfullstack.angular.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
}