package utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static class PlayerConstants {
        public static final int ATTACK_1 = 0;
        public static final int ATTACK_2 = 1;
        public static final int ATTACK_3 = 2;
        public static final int CLIMB = 3;
        public static final int DEATH = 4;
        public static final int DOUBLE_JUMP = 5;
        public static final int HURT = 6;
        public static final int IDLE = 7;
        public static final int JUMP = 8;
        public static final int PUNCH = 9;
        public static final int RUN = 10;
        public static final int RUN_ATTACK = 11;

        private static final Map<Integer, Integer> spriteAmounts = new HashMap<>();

        static {
            spriteAmounts.put(ATTACK_1, 6);
            spriteAmounts.put(ATTACK_2, 8);
            spriteAmounts.put(ATTACK_3, 8);
            spriteAmounts.put(CLIMB, 6);
            spriteAmounts.put(DEATH, 6);
            spriteAmounts.put(DOUBLE_JUMP, 6);
            spriteAmounts.put(HURT, 2);
            spriteAmounts.put(IDLE, 4);
            spriteAmounts.put(JUMP, 4);
            spriteAmounts.put(PUNCH, 6);
            spriteAmounts.put(RUN, 6);
            spriteAmounts.put(RUN_ATTACK, 6);
        }

        public static int getSpriteAmount(int player_action) {
            return spriteAmounts.getOrDefault(player_action, 1);
        }
    }
}
