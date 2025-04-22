package com.example.jpaboard.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpaboard.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	// CrudRepository : insert, select one, select all, update, delete
	
	// JpaRepository(CrudRepository를 상속받는다) : CrudRepository + select limit, select order by....
	
	Page<Article> findByTitleContaining(Pageable pageable,String word);
	
	@Query(nativeQuery = true,
			value= "select min(id) minId, max(id) maxId, count(*) cnt "
					+ "from article "
					+ "where title like :word")
	Map<String,Object> getMinMaxCount(String word); 
}
