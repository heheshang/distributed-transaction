package com.distributed.transaction.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 通用的JPA方法声明
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/07/18 下午 2:09
 * @since v1.0
 **/
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends BaseInternalRepository<T, ID>, JpaSpecificationExecutor<T> {




}
