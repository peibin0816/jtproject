package com.jt.service;

import com.jt.mapper.RightsMapper;
import com.jt.pojo.Rights;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RightsService {

    List<Rights> findall();

}
