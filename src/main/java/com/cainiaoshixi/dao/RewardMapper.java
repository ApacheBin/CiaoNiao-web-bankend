package com.cainiaoshixi.dao;

import com.cainiaoshixi.entity.Reward;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RewardMapper {
    List<Reward> getReward(@Param("userId") Integer userId, @Param("rewardId") Integer rewardId);

    void addReward(Reward reward);

    void updateReward(Reward reward);

    void deleteReward(int id);
}