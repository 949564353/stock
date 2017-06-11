package com.zrf.stock.dao;

import com.zrf.stock.entity.CqsscDay;

public interface CqsscDayMapper {
    int insert(CqsscDay record);

    int insertSelective(CqsscDay record);
}