package com.cainiaoshixi.service.Impl;

import com.cainiaoshixi.dao.RewardMapper;
import com.cainiaoshixi.entity.Reward;
import com.cainiaoshixi.service.IRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RewardServiceImpl implements IRewardService {

    private final RewardMapper rewardMapper;

    @Autowired
    public RewardServiceImpl(RewardMapper rewardMapper) {
        this.rewardMapper = rewardMapper;
    }

    @Override
    public List<Reward> getReward(int userId, int eduId){
        return rewardMapper.getReward(userId,eduId);
    }

    @Override
    public void addReward(Reward reward){
        Date now = new Date();
        reward.setCreateTime(now);
        reward.setUpdateTime(now);
        rewardMapper.addReward(reward);
    }

    @Override
    public void updateReward(Reward reward) {
        Date now = new Date();
        reward.setUpdateTime(now);
        rewardMapper.updateReward(reward);
    }

    @Override
    public void deleteReward(int id) {
        rewardMapper.deleteReward(id);
    }

}
