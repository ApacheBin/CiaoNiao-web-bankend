package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Resume;
import io.swagger.models.auth.In;

public interface ResumeMapper {

    Resume getEvaluationByUserId(int userId);

    public void addEvaluation(Resume resume);

    public void updateEvaluation(Resume resume);

    public Integer isExistEvaluation(Integer userId);
}
