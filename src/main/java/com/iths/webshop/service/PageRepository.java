package com.iths.webshop.service;

import com.iths.webshop.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

    Page findByUrl(String url);

    Page findByUrlAndIdNot(String url, int id);

}
