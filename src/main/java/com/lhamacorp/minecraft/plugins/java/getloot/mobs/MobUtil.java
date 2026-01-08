package com.lhamacorp.minecraft.plugins.java.getloot.mobs;

import com.lhamacorp.minecraft.plugins.java.getloot.mobs.animals.*;
import com.lhamacorp.minecraft.plugins.java.getloot.mobs.foe.*;

import java.util.List;

public class MobUtil {

    public static final List<Class<? extends Mob>> MOB_LIST = List.of(
            Axolotl.class,
            Bat.class,
            Bee.class,
            Camel.class,
            Cat.class,
            Chicken.class,
            Cow.class,
            Donkey.class,
            Fox.class,
            Goat.class,
            Horse.class,
            Llama.class,
            Mule.class,
            Pig.class,
            Rabbit.class,
            Sheep.class,
            Spider.class,
            Wolf.class,

            Creeper.class,
            Dragon.class,
            ElderGuardian.class,
            Enderman.class,
            Guardian.class,
            Pillager.class,
            Ravager.class,
            Slime.class,
            Skeleton.class,
            Villager.class,
            Witch.class,
            Wither.class,
            Warden.class,
            Zombie.class,
            ZombiePiglin.class
    );

}
