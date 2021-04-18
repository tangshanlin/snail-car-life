package com.woniu.car.product.web.elasticsearch;

import com.woniu.car.product.model.library.CarProductIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Copyright (C), 2021, 温天宇
 *
 * @author WTY
 * Date: 2021/4/13 16:24
 * FileName: ProductRepostory
 */
public interface ProductRepository extends ElasticsearchRepository<CarProductIndex,Integer> {

}
