package com.bagel.buzzierbees.common.entities.goals;

import com.bagel.buzzierbees.common.entities.HoneySlimeEntity;
import com.bagel.buzzierbees.common.entities.controllers.HoneySlimeMoveHelperController;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public class HoneySlimeHopGoal extends Goal {
    private final HoneySlimeEntity slime;

    public HoneySlimeHopGoal(HoneySlimeEntity slimeIn) {
        this.slime = slimeIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        return !this.slime.isPassenger() && this.slime.getMoveHelper() instanceof HoneySlimeMoveHelperController;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        ((HoneySlimeMoveHelperController) this.slime.getMoveHelper()).setSpeed(1.0D);
    }
}