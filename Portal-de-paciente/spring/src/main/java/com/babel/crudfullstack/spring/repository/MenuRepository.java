package com.babel.crudfullstack.spring.repository;

import com.babel.crudfullstack.spring.model.Menu;
import com.babel.crudfullstack.spring.model.MenuId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, MenuId> {
    @Query("SELECT MAX(m.idMenu) FROM Menu m")
    Integer findMaxIdMenu();

    @Transactional
    void deleteByIdMenu(Integer idMenu);

    @Transactional
    void deleteByIdMenuAndIdLang(Integer idMenu, String idLang);
}