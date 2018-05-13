package com.cainiaoshixi.service;

import com.cainiaoshixi.entity.Reward;
import java.util.List;

public interface IRewardService {

    List<Reward> getReward(int userId, int eduId);

    void addReward(Reward reward);

    void updateReward(Reward reward);

    void deleteReward(int id);
}
